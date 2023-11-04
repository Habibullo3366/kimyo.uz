package com.company.kimyouz.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCardDto {
    private Integer cardId;

    @NotBlank(message = "Card user's full name cannot be null or empty")
    private String cardFullName;

    @NotBlank(message = "Card name cannot be null or empty")
    private String cardName;

    @NotNull(message = "User id of card owner cannot be null or empty")
    private Integer userId;

    @NotBlank(message = "Card code cannot be null or empty")
    private String cardCode;

    private ResponseUserDto users;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
