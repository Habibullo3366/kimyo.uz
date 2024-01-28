package com.company.kimyouz.security;


import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value(value = "${spring.jwt.key}")
    private String securityKey;

    public String generateAccessToken(String value) {

        return Jwts.builder()
                .setSubject(value)
                .signWith(Keys.hmacShaKeyFor(securityKey.getBytes()))
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 30))
                .compact();
    }

    public String generateRefreshToken(String value) {

        return Jwts.builder()
                .setSubject(value)
                .signWith(Keys.hmacShaKeyFor(securityKey.getBytes()))
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 30))
                .compact();
    }


    public Boolean isValid(String token) {
        if (!parser().isSigned(token)) {
            return false;
        }
        try {
            return !StringUtils.isBlank(
                    parser()
                            .parseClaimsJwt(token)
                            .getBody().getSubject());
        } catch (Exception e) {
            return false;
        }
    }

    public <T> T getClaim(String name,String token,Class<T> type){
        return parser().parseClaimsJwt(token).getBody().get(name,type);
    }


    public JwtParser parser() {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(securityKey.getBytes()))
                .build();
    }
}
