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
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Integer age;

    @NotBlank(message = "ProductName should not be empty or null")
    private String prodName;

    @NotBlank(message = "Description should not be empty or null")
    private String description;

    @NotBlank(message = "Stock should not be empty or null")
    private String stock;

    @NotNull(message = "BasketId should not be null")
    private Integer basketId;

    @NotNull(message = "CategoryId should not be null")
    private Integer categoryId;
}
