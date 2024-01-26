package com.company.kimyouz.dto.request;

import com.company.kimyouz.entity.OrdersItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrdersDto {

    @NotNull(message = "OrderDate should not be null")
    private LocalDate orderDate;
    @NotNull(message = "TotalPrice should not be null ")
    private Double totalPrice;

    @NotNull(message = "UserId should not be null")
    private Integer userId;
;
}
