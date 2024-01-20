package com.company.kimyouz.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDto {
    private String prodName;
    private String prodColor;
    private String prodType;
    private Double prodPrice;
    private Integer prodAmount;
    private Integer categoryId;
}
