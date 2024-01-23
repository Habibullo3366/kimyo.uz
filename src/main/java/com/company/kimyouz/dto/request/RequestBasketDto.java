package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestBasketDto {
    @NotNull(message = "Basket Id is cannot be null or empty")
    private Integer basketId;
    private Double totalPrice;
}
