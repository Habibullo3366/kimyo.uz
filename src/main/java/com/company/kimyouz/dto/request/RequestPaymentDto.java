package com.company.kimyouz.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestPaymentDto {


    private Integer paymentId;

    private LocalDate paymentDate;

    private Integer totalPrice;

    private Integer orderId;

    private Integer userId;
}
