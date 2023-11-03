package com.company.kimyouz.dto.request;

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
    private Integer productId;
    private Integer orderId;
}
