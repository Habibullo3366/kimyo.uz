package com.company.kimyouz.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrdersDto {
    private Integer orderId;

    @NotNull(message = "Order date cannot be null or empty")
    private LocalDate orderDate;

    @NotNull(message = "Order total price cannot be null or empty")
    private Double totalPrice;

    @NotNull(message = "Order's user id cannot be null or empty")
    private Integer userId;

//    private List<OrdersItemDto> ordersItemDto;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
