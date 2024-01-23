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
    @NotNull(message = "Category Id cannot be null or empty")
    private Integer categoryId;
    @NotBlank(message = "Prod name cannot be null or empty")
    private String prodName;
    private String prodColor;
    private String prodType;
    @NotNull(message = "Prod price cannot be null or empty")
    private Double prodPrice;
    @NotNull(message = "Prod amount cannot be null or empty")
    private Integer prodAmount;
}
