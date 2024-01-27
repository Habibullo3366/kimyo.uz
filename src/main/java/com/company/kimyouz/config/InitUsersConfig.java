package com.company.kimyouz.config;

import com.company.kimyouz.entity.Authorities;
import com.company.kimyouz.entity.User;
import com.company.kimyouz.repository.AuthorityRepository;
import com.company.kimyouz.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class InitUsersConfig {


    /*private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    //todo: username: User
    //todo: password: root
    //todo: ROLE: ADMIN

    @PostConstruct
    public void addUserAndAuthority() {

        this.userRepository.findByUsernameAndDeletedAtIsNull("User")
                .ifPresent(this.userRepository::delete);

        this.authorityRepository.findByUsernameAndAuthority("User", "ADMIN")
                .ifPresent(this.authorityRepository::delete);


        User saveUser = this.userRepository.save(
                User.builder()
                        .username("User")
                        .password(passwordEncoder.encode("root"))
                        .build()
        );

        this.authorityRepository.save(
                Authorities.builder()
                        .username(saveUser.getUsername())
                        .userId(saveUser.getUserId())
                        .authority("ADMIN")
                        .build()
        );
    }*/



}
