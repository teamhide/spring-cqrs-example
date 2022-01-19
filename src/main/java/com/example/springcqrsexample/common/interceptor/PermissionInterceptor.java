package com.example.springcqrsexample.common.interceptor;

import com.example.springcqrsexample.common.util.JwtTokenPayload;
import com.example.springcqrsexample.common.util.JwtTokenUtil;
import com.example.springcqrsexample.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class PermissionInterceptor implements HandlerInterceptor {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserQueryService userQueryService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
        if (permission == null) {
            return true;
        }

        String token = extractJwtTokenFromHeader(request);
        JwtTokenPayload payload = jwtTokenUtil.getPayload(token);
        userQueryService.getUserById(payload.getUserId());

        if (permission.role().equals(PermissionRole.MEMBER)) {
            return true;
        }

        // TODO: 관리자 권한관련 DB설계 후 수정 필요
        if (permission.role().equals(PermissionRole.ADMIN)) {
            return true;
        }

        throw new AuthenticationException();
    }

    private String extractJwtTokenFromHeader(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            throw new AuthenticationException();
        }

        try {
            return authorization.split(" ")[1];
        } catch (Exception e) {
            throw new AuthenticationException();
        }
    }
}