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
    @NotBlank(message="prodName must not be blank")
    private String prodName;
    @NotBlank(message="prodColor must not be blank")
    private String prodColor;
    @NotBlank(message="prodType must not be blank")
    private String prodType;
    @NotNull(message="prodPrice must not be null")
    private Double prodPrice;
    @NotNull(message="prodAmount must not be null")
    private Integer prodAmount;
    @NotNull(message="categoryId must not be null")
    private Integer categoryId;
}
