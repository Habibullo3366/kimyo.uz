package com.company.kimyouz.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTokenDto {

    private String accessToken;
    private String refreshToken;

}
