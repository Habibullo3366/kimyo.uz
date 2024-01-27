package com.company.kimyouz.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RequestUserDto {

    private Integer age;
    private String lastname;
    private String username;
    private String password;
    private String firstname;

}
