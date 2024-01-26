package com.company.kimyouz.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestPaymentDto {


    @NotNull(message = "PaymentDate should not be null")
    private LocalDate paymentDate;

    @NotNull(message = "TotalPrice should not be null")
    private Integer totalPrice;

    @NotNull(message = "OrderId should not be null")
    private Integer orderId;

    @NotNull(message = "UserId should not be null")
    private Integer userId;
}
