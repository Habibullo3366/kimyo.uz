package com.company.kimyouz.integration.mock;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestCardDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MocData {

    public static <T> ResponseDto<T> getResponse(
            String json,
            ObjectMapper objectMapper,
            Class<T> object
    ) throws JsonProcessingException {
        var response = objectMapper.readValue(json, ResponseDto.class);
        return ResponseDto.<T>builder()
                .success(response.isSuccess())
                .code(response.getCode())
                .message(response.getMessage())
                .content(objectMapper.readValue(objectMapper.writeValueAsString(response.getContent()), object))
                .build();
    }


    public static RequestCardDto getCardRequest() {
        return RequestCardDto.builder()
//                .userId(1)
                .cardName("Humo")
                .cardCode("0000")
                .cardFullName("Hasanboy Xalilov")
                .build();
    }

}
