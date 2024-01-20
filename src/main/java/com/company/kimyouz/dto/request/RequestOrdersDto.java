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
    @NotBlank(message = "LocalDate is not null or empty")
    private LocalDate orderDate;
    @NotNull(message = "totalPrice is not null or empty")
    private Double totalPrice;
}
