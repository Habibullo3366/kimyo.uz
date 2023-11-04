package com.company.kimyouz.dto.response;

import com.company.kimyouz.entity.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOrdersItemDto {
    private Integer orderItemId;
    private Double quantity;
    private Double totalPrice;


    private Integer orderId;
    private Integer productId;
    private Product product;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
