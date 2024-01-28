package com.company.kimyouz.Mock;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestOrdersDto;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;

public class MockContent {

    public static <T>ResponseDto<T> getFullContent(
            String json,
            ObjectMapper objectMapper,
            Class<T> clazz
    ) throws IOException {
        ResponseDto response = objectMapper.readValue(json, ResponseDto.class);
        return ResponseDto.<T>builder()
                .success(response.isSuccess())
                .code(response.getCode())
                .message(response.getMessage())
                .content( objectMapper.readValue(response.getContent().toString(), clazz))
                .build();
    }


    public static RequestOrdersDto getRequestOrders(){
        return RequestOrdersDto.builder()
                .totalPrice(1.0)
                .build();
    }
}
