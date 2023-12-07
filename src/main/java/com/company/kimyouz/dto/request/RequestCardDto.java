package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCardDto {
    @NotBlank(message="cardFullName cannot be null or empty")
    private String cardFullName;
    @NotBlank(message="cardName cannot be null or empty")
    private String cardName;
    @NotNull(message = "userId cannot be null")
    private Integer userId; //todo: userId: 1080
    @NotBlank(message="cardCode cannot be null or empty")
    private String cardCode;
}
