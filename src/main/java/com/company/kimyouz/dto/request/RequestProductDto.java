package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDto {
    @NotBlank(message = "Product name cannot be null or empty")
    private String prodName;
    @NotBlank(message = "Product color cannot be null or empty")
    private String prodColor;
    @NotBlank(message = "Product type cannot be null or empty")
    private String prodType;
    @NotNull(message = "Product price cannot be null ")
    private Double prodPrice;
    @NotNull(message = "Product amount cannot be null ")
    private Integer prodAmount;
}
