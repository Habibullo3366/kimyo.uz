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
    @NotBlank(message = "CardFullName should not be null or empty")
    private String cardFullName;
    @NotBlank(message = "CardName should not be null or empty")
    private String cardName;
    @NotNull(message = "UserId should not be null")
    private Integer userId;
    @NotBlank(message = "CardCode should not be null or empty")
    private String cardCode;
}
