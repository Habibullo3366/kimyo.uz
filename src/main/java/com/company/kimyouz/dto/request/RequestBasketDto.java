package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestBasketDto {
    @NotNull(message = "basketId must not be null")
    private Integer basketId;
    @NotNull(message = "totalPrice must not be null")
    private Double totalPrice;
}
