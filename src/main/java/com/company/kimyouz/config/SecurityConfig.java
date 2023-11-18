package com.company.kimyouz.config;

import com.company.kimyouz.dto.response.ResponseUserDto;
import com.company.kimyouz.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Base64;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends OncePerRequestFilter {

    private final DataSource dataSource;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/user/**").permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .httpBasic().and()
//                .formLogin(Customizer.withDefaults())
                .build();
    }


    /*
    todo:@Autowired
    todo:public void authenticationManagerBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .inMemoryAuthentication()
                .withUser("User").password(this.appConfig.encoder().encode("root")).roles("ADMIN")
                .and()
                .withUser("Java").password(this.appConfig.encoder().encode("data")).roles("USER")
                .and()
                .passwordEncoder(this.appConfig.encoder());
    }
    */

    @Autowired
    public void authenticationManagerBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Basic ")) {

            String token = authorization.split(" ")[1];

            String usernameAndPasswordBase64 = new String(Base64.getDecoder().decode(token.getBytes()));

            String username = usernameAndPasswordBase64.split(":")[0];

            String password = usernameAndPasswordBase64.split(":")[1];

            ResponseUserDto users = this.userService.loadUserByUsername(username);

            if (passwordEncoder.matches(password, users.getPassword())) {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                users,
                                users.getPassword(),
                                users.getAuthorities()
                        ));
            }
        }

        filterChain.doFilter(request, response);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**");
    }

}
