package com.company.kimyouz.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestPaymentDto {


    @NotNull(message = "Payment id must not be null")
    private Integer paymentId;

    @NotBlank(message = "PaymentDate must not be null or empty")
    private LocalDate paymentDate;

    @NotNull(message = "TotalPrice must not be null")
    private Integer totalPrice;

    @NotNull(message = "OrderId must not be null")
    private Integer orderId;

    @NotNull(message = "UserId must not be null")
    private Integer userId;

}
