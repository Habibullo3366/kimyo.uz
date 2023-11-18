package com.company.kimyouz.dto.response;


import com.company.kimyouz.entity.Orders;
import com.company.kimyouz.entity.Payment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = "password", allowSetters = true)
public class ResponseUserDto {
    private Integer userId;

    @NotNull(message = "Firstname cannot be null")
    @NotEmpty(message = "Firstname cannot be empty")
    private String firstname;

    @NotBlank(message = "Lastname cannot be null or empty")
    private String lastname;

    //@Email(message = "Username is not valid")
    @NotBlank(message = "Username cannot be null or empty")
    private String username;

    @NotBlank(message = "Password cannot be null or empty")
    //@Size(min = 8, max = 16, message = "Incorrect password size")
    //@Min(value = 8, message = "Incorrect password value!")
    private String password;



    @Max(value = 150, message = "Age must be less than 150")
    @Min(value = 1, message = "Age must be more than 1")
    @NotNull(message = "Age cannot be null")
    private Integer age;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private Set<ResponseCardDto> cards;
    private Set<ResponsePaymentDto> payments;
    private Set<ResponseOrdersDto> orders;


}
