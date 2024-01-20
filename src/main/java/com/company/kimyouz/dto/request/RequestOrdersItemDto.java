package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrdersItemDto {
    @NotNull(message = "orderItemId must not be null")
    private Integer orderItemId;
    @NotNull(message = "quantity must not be null")
    private Double quantity;
    @NotNull(message = "totalPrice must not be null")
    private Double totalPrice;
    @NotNull(message = "productId must not be null")
    private Integer productId;
    @NotNull(message = "orderId must not be null")
    private Integer orderId;
}
