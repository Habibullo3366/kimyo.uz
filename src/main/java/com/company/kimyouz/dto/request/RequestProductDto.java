package com.company.kimyouz.dto.request;

import com.company.kimyouz.entity.Basket;
import com.company.kimyouz.entity.OrdersItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDto {
//    @NotBlank(message = "Product name should not be null or empty")
    private String prodName;
//    @NotBlank(message = "Product color should not be null or empty")
    private String prodColor;
//    @NotBlank(message = "Product type should not be null or empty")
    private String prodType;
//    @NotNull(message = "Product price should not be null")
    private Double prodPrice;
//    @NotNull(message = "Product amount should not be null")
    private Integer prodAmount;

//    @NotNull(message = "Category id should not be null")
    private Integer categoryId;

//    @NotNull(message = "Basket id should not be null")
    private Integer basketId;
//    @NotNull(message = "OrderItem id should not be null")
    private Integer orderItemId;

//    @NotBlank(message = "ProductName should not be empty or null")
//    private String prodName;
//
//    @NotBlank(message = "Description should not be empty or null")
//    private String description;
//
//    @NotBlank(message = "Stock should not be empty or null")
//    private String stock;
//
//    @NotNull(message = "BasketId should not be null")
//    private Integer basketId;
//
//    @NotNull(message = "CategoryId should not be null")
//    private Integer categoryId;
//
//    @NotNull(message = "OrderItemId should not be null")
//    private Integer orderItemId;

}
