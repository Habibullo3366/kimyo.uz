package com.company.kimyouz;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Kimyo.uz",
                version = "1 - version",
                description = "Java Backend 7 Students Project",
                license = @License(
                        name = "Kimyo.uz License"
                )
        ),
        servers = @Server(
                url = "http://localhost:8080"
        )
)
public class KimyoUzApplication {

    public static void main(String[] args) {
        SpringApplication.run(KimyoUzApplication.class, args);
    }

}

//todo: JWT -> Json Web Token
//todo: Redis -> Database

