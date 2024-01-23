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
    @NotNull(message = "Age cannot be null or empty")
    private Integer age;
    @NotBlank(message = "Lastname cannot be null or empty")
    private String lastname;
    @NotBlank(message = "Username cannot be null or empty")
    private String username;
    private String password;
    @NotBlank(message = "Firstname cannot be null or empty")
    private String firstname;

}
