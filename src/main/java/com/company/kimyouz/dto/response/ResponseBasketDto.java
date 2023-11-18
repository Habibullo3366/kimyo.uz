package com.company.kimyouz.dto.response;

import com.company.kimyouz.entity.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBasketDto {

    private Integer basketId;
    private  Double totalPrice;
    private List<Product>products;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
