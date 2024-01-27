package com.company.kimyouz.integration.mock;

import com.company.kimyouz.dto.request.RequestCardDto;

public class MockContent {

    public static RequestCardDto getRequestCard() {
        return RequestCardDto.builder()
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
