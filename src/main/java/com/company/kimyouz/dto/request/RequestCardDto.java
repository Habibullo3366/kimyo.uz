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
    @NotBlank(message="cardFullName must not be blank")
    private String cardFullName;
    @NotBlank(message="cardName must not be blank")
    private String cardName;
    @NotNull(message="userId must not be null")
    private Integer userId; //todo: userId: 1080
    @NotNull(message="cardCode must not be null")
    private String cardCode;
}
