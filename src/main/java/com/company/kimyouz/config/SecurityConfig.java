package com.company.kimyouz.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        String[] permitPath = {
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-resources/**",
                ".html",
                "/api/v1/swagger.json",
                "/user/**"
        };
       return http.
                cors(Customizer.withDefaults())
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(permitPath).permitAll()
                        .anyRequest()
                        .authenticated()
                )
               // .addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class)
               .httpBasic(Customizer.withDefaults())
               .formLogin(Customizer.withDefaults())
               .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**");
    }
}
