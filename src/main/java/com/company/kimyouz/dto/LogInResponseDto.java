package com.company.kimyouz.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogInResponseDto {
    private String username;
    private String password;
}
