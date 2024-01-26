package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrdersItemDto {
    @NotNull(message ="Quantity should not be null")
    private Double quantity;
    @NotNull(message = "TotalPrice should not be null")
    private Double totalPrice;
    @NotNull(message = "ProductId should not be null")
    private Integer productId;
    @NotNull(message = "OrderId should not be null")
    private Integer orderId;
}
