package com.company.kimyouz.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Integer age;
}
