package com.company.kimyouz.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCardDto {
    private String cardFullName;
    private String cardName;
    private Integer userId; //todo: userId: 1080
    private String cardCode;
}
