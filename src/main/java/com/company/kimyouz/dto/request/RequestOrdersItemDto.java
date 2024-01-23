package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrdersItemDto {
    @NotNull(message = "orderItemId is not null")
    private Integer orderItemId;
    @NotNull(message = "quantity is not null")
    private Double quantity;
    @NotNull(message ="totalPrice is not null")
    private Double totalPrice;
    @NotNull(message = "productId is not null")
    private Integer productId;
    @NotNull(message = "orderId is not null")
    private Integer orderId;
}
