package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrdersDto {
    @NotNull(message = "orderDate is not null")
    private LocalDate orderDate;
    @NotNull(message = "totalPrice is not null")
    private Double totalPrice;
}
