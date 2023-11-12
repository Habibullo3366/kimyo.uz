package com.company.kimyouz.config;

import com.company.kimyouz.entity.Authorities;
import com.company.kimyouz.entity.User;
import com.company.kimyouz.repository.AuthorityRepository;
import com.company.kimyouz.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class AppConfig {


    @Value(value = "${spring.datasource.url}")
    private String url;
    @Value(value = "${spring.datasource.username}")
    private String username;
    @Value(value = "${spring.datasource.password}")
    private String password;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource source = new SimpleDriverDataSource();
        source.setUrl(url);
        source.setUsername(username);
        source.setPassword(password);
        source.setDriverClass(org.postgresql.Driver.class);
        return source;
    }




}
