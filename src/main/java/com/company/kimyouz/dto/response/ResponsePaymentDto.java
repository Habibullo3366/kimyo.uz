package com.company.kimyouz.dto.response;

import com.company.kimyouz.entity.Orders;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePaymentDto {

    private Integer paymentId;


    private LocalDate paymentDate;

    private Integer totalPrice;


    private Integer orderId;

    private Integer userId;
    private ResponseOrdersDto orders;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
