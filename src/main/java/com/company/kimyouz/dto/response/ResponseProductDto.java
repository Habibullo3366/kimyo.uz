package com.company.kimyouz.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseProductDto {

    private Integer prodId;

    @NotBlank(message = "Product name cannot be null or empty")
    private String prodName;

//    @NotBlank(message = "Product's description cannot be null or empty")
//    private String description;
//
//    @NotBlank(message = "Product's stock cannot be null or empty")
//    private String stock;

    private String prodColor;
    private Double prodPrice;
    private Integer prodAmount;
    private String prodType;

    @NotNull(message = "Product's category id cannot be null or empty")
    private Integer categoryId;

    private ResponseCategoryDto responseCategoryDto;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
