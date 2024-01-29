package com.company.kimyouz.integration.mock;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestProductDto;
import com.company.kimyouz.dto.response.ResponseProductDto;
import com.company.kimyouz.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MockContent {

    public static  <T> ResponseDto<T> getFullContent(String json , ObjectMapper objectMapper , Class<T> clazz) throws JsonProcessingException {

        var responseDto = objectMapper.readValue(json , ResponseDto.class);

        return ResponseDto.<T>builder()

                .success(responseDto.isSuccess())
                .code(responseDto.getCode())
                .message(responseDto.getMessage())
                .content(objectMapper.readValue(objectMapper.writeValueAsString(responseDto.getContent()) , clazz))
                .build();
    }
    public static RequestProductDto getProductDtoRequest(){
        return RequestProductDto.builder()
                .prodType("Car")
                .prodPrice(12000.0)
                .prodName("BMW")
                .prodColor("Dark Blue")
                .prodAmount(1000)
                .build();
    }

    public static RequestProductDto getProductDtoRequestWithIds(){
        return RequestProductDto.builder()
                .prodType("Car")
                .prodPrice(12000.0)
                .prodName("BMW")
                .prodColor("Dark Blue")
                .prodAmount(1000)
                .categoryId(1)
                .basketId(1)
                .orderItemId(1)
                .build();
    }

    public static Product getProduct(){
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
