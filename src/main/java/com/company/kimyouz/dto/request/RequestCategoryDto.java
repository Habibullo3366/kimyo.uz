package com.company.kimyouz.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCategoryDto {
    @NotBlank(message = "categoryName cannot be null or empty")
    private String categoryName;
}
