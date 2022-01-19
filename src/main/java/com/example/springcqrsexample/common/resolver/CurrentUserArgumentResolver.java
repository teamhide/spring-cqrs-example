package com.example.springcqrsexample.common.resolver;

import com.example.springcqrsexample.common.util.JwtTokenPayload;
import com.example.springcqrsexample.common.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        UserInfo userInfo = new UserInfo();
        String authorization = webRequest.getHeader("Authorization");

        if (authorization == null) {
            return userInfo;
        }

        try {
            String token = authorization.split(" ")[1];
            JwtTokenPayload payload = jwtTokenUtil.getPayload(token);
            userInfo.setId(payload.getUserId());
        } catch (Exception e) {
            return userInfo;
        }

        return userInfo;
    }
}
