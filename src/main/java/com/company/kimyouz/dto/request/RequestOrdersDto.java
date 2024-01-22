package com.company.kimyouz.dto.request;

import com.company.kimyouz.entity.OrdersItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
    private Integer orderId;
    private LocalDate orderDate;
    private Double totalPrice;

    private Integer userId;


    private List<RequestOrdersItemDto> ordersItems;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
