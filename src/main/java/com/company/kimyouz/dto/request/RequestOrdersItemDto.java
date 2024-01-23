package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrdersItemDto {
    private Integer orderItemId;
    private Double quantity;
    private Double totalPrice;
    @NotNull(message = "Product Id cannot be null or empty")
    private Integer productId;
    @NotNull(message = "Order Id cannot be null or empty")
    private Integer orderId;
}
