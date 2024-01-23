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

    @NotNull(message = "paymentId is not Null")
    private Integer paymentId;

    @NotNull(message = "paymentDate is not Null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @NotNull(message = "totalPrice is not Null")
    private Integer totalPrice;

    @NotNull(message = "orderId is not Null")
    private Integer orderId;

    @NotNull(message = "userId is not Null")
    private Integer userId;
}
