package com.company.kimyouz.integration.mock;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestCardDto;
import com.company.kimyouz.entity.Card;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MockContent {


    public static <T> ResponseDto<T> getFullContent(

            String json,
            ObjectMapper objectMapper,
            Class<T> clazz

    ) throws JsonProcessingException {
        var response = objectMapper.readValue(json, ResponseDto.class);
        return ResponseDto.<T>builder()
                .success(response.isSuccess())
                .code(response.getCode())
                .message(response.getMessage())
                .content(objectMapper.readValue(objectMapper.writeValueAsString(response.getContent()), clazz))
                .build();
    }


    public static RequestCardDto getRequestCard() {
        return RequestCardDto.builder()
                .cardName("Humo")
                .cardCode("0000")
                .cardFullName("Hasanboy Xalilov")
                .build();
    }

    public static Card getCardEntity() {
        return Card.builder()
                .cardName("Humo")
                .cardCode("0000")
                .cardFullName("Hasanboy Xalilov")
                .build();
    }

    public static RequestCardDto getRequestCardWithUserId() {
        return RequestCardDto.builder()
                .cardName("Humo")
                .userId(1)
                .cardCode("0000")
                .cardFullName("Hasanboy Xalilov")
                .build();
    }


}
