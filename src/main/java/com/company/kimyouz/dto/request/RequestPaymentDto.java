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

    @NotNull(message = "PaymentId cannot be null or empty")
    private Integer paymentId;

    @NotBlank(message = "paymentDate cannot be null or empty")
    private LocalDate paymentDate;

    @NotNull(message = "totalPrice cannot be null or empty")
    private Integer totalPrice;

    @NotNull(message = "orderId cannot be null or empty")
    private Integer orderId;

    @NotNull(message = "userId cannot be null or empty")
    private Integer userId;
}
