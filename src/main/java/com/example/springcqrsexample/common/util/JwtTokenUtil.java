package com.example.springcqrsexample.common.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenUtil {
    @Value("${spring.jwt.algorithm}")
    private String algorithm = "HS256";

    @Value("${spring.jwt.secret-key}")
    private String secretKey = "daangn";

    public String encode(HashMap<String, Object> payload, int seconds) {
        return Jwts.builder()
                .setHeader(makeHeader())
                .setClaims(convertMapToClaims(payload))
                .setIssuedAt(new Date())
                .setExpiration(makeExpiration(seconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String encode(HashMap<String, Object> payload) {
        return Jwts.builder()
                .setHeader(makeHeader())
                .setClaims(convertMapToClaims(payload))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims decode(String token) {
        verify(token);
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token).getBody();
    }

    public JwtTokenPayload getPayload(String token) {
        Claims claims = decode(token);
        JwtTokenPayload payload = new JwtTokenPayload();
        Integer userId = (Integer) claims.get(JwtTokenPayloadKeySet.USER_ID.toString());

        if (userId != null) {
            payload.setUserId(Long.valueOf(userId));
        }

        return payload;
    }

    public void verify(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                    .parseClaimsJws(token).getBody();
        } catch (SignatureException e) {
            throw new JwtSignatureException();
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException();
        } catch (JwtException e) {
            throw new JwtTokenDecodeException();
        }
    }

    private HashMap<String, Object> makeHeader() {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", algorithm);
        return headers;
    }

    private Date makeExpiration(int expirePeriod) {
        Date now = new Date();
        long seconds = now.getTime();
        seconds = seconds + (expirePeriod * 1000L);
        return new Date(seconds);
    }

    private Claims convertMapToClaims(HashMap<String, Object> payload) {
        Claims claims = Jwts.claims();
        for (String key : payload.keySet()) {
            claims.put(key, payload.get(key));
        }
        return claims;
    }
}
