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
    @NotBlank(message = "Card full name cannot be null or empty")
    private String cardFullName;
    @NotBlank(message = "Card name cannot be null or empty")
    private String cardName;
    @NotNull(message = "User id cannot be null or empty")
    private Integer userId; //todo: userId: 1080
    private String cardCode;
}
