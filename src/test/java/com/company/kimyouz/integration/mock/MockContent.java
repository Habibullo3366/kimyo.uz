package com.company.kimyouz.integration.mock;

import com.company.kimyouz.dto.request.RequestProductDto;
import com.company.kimyouz.dto.response.ResponseProductDto;
import com.company.kimyouz.entity.Product;

public class MockContent {

    public static RequestProductDto getProductDtoRequest(){
        return RequestProductDto.builder()
                .prodType("Car")
                .prodPrice(12000.0)
                .prodName("BMW")
                .prodColor("Dark Blue")
                .prodAmount(1000)
                .categoryId(1)
                .build();
    }

    private static Product getProduct(){
        return Product.builder()
                .prodId(1)
                .prodType("Car")
                .prodPrice(12000.0)
                .prodName("BMW")
                .prodColor("Dark Blue")
                .prodAmount(1000)
                .categoryId(1)
                .build();
    }

}
