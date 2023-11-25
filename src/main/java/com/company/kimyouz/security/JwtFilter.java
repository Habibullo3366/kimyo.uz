package com.company.kimyouz.security;

import com.company.kimyouz.repository.UserAccessSessionRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserAccessSessionRepository userAccessSessionRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isBlank(authorization) && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            if (this.jwtUtils.isValid(token)) {
                userAccessSessionRepository.findById(this.jwtUtils.getClaim("sub", token, String.class))
                        .ifPresent(userAccessSession -> {
                                    SecurityContextHolder.getContext().setAuthentication(
                                            new UsernamePasswordAuthenticationToken(
                                                    userAccessSession.getUserDto(),
                                                    userAccessSession.getUserDto().getPassword(),
                                                    userAccessSession.getUserDto().getAuthorities()
                                            )
                                    );
                                }
                        );
            }
        }
        filterChain.doFilter(request, response);

    }
}
