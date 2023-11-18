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


    private Integer paymentId;


    private Integer totalPrice;

    @NotNull(message = "Order Id cannot be null or empty")
    private Integer orderId;

    private Integer userId;
}
