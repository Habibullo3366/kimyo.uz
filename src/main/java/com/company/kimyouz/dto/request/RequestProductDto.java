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
    @NotBlank(message="prodName is not null or empty")
    private String prodName;
    @NotBlank(message="prodColor is not null or empty")
    private String prodColor;
    @NotBlank(message = "prodType is not null or empty")
    private String prodType;
    @NotNull(message = "prodPrice is not null")
    private Double prodPrice;
    @NotNull(message = "prodAmount is not null")
    private Integer prodAmount;
}
