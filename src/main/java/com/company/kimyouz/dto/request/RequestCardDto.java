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
    @NotBlank(message="CardFullName is not be null or empty")
    private String cardFullName;
    @NotBlank(message = "CardName is not be null or empty")
    private String cardName;
    @NotNull(message = "UserID is not null or empty")
    private Integer userId; //todo: userId: 1080
    @NotBlank(message = "CardCode is not be null or empty")
    private String cardCode;
}
