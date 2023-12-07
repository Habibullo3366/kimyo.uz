package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrdersDto {
    @NotBlank(message = "ordersDate cannot be null or empty")
    private LocalDate orderDate;
    @NotNull(message = "totalPrice cannot be null")
    private Double totalPrice;
}
