package com.company.kimyouz.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.coyote.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrdersDto {
    private Integer orderId;


    private LocalDate orderDate;


    private Double totalPrice;


    private Integer userId;

    private List<ResponseOrdersItemDto> ordersItems;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
