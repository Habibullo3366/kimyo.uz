package com.company.kimyouz.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;
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

    //todo: JDBC Connectivity

    //todo: Authentication
    //todo: Authorization
    //todo: Identification


}
