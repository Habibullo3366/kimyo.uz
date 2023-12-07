package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestBasketDto {
    @NotNull(message = "basketId cannot be null or empty")
    private Integer basketId;
    @NotNull(message = "totalPrice cannot be null or empty")
    private Double totalPrice;
}
