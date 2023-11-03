package com.company.kimyouz.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestBasketDto {
    private Integer basketId;
    private Double totalPrice;
}
