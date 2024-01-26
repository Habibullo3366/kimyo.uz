package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestBasketDto {

    @NotNull(message = "Total price should not be null")
    private Double totalPrice;
}
