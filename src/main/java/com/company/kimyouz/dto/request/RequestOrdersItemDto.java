package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrdersItemDto {
    @NotNull(message = "orderItemsId cannot be null")
    private Integer orderItemId;
    @NotNull(message = "quantity cannot be null")
    private Double quantity;
    @NotNull(message = "totalPrice cannot be null")
    private Double totalPrice;
    @NotNull(message = "productId cannot be null")
    private Integer productId;
    @NotNull(message = "orderId cannot be null")
    private Integer orderId;
}
