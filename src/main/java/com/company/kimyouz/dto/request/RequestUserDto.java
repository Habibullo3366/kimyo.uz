package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserDto {

    @NotBlank(message = "Firstname cannot be null or empty!")
    private String firstname;
    @NotBlank(message = "Lastname cannot be null or empty!")
    private String lastname;
    @NotBlank(message = "Username cannot be null or empty!")
    private String username;
    @NotBlank(message = "Password cannot be null or empty!")
    private String password;
    @NotNull(message = "Age cannot be null!")
    private Integer age;
}
