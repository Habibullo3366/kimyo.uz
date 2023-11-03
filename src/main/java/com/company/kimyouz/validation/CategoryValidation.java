package com.company.kimyouz.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryValidation {
    public List<ErrorDto> categoryValid(ResponseCategoryDto dto) {
        List<ErrorDto> errorsList = new ArrayList<>();
        if (StringUtils.isBlank(dto.getCategoryName())) {
            errorsList.add(new ErrorDto("categoryName", "CategoryName cannot be null or empty."));
        }
        return errorsList;
    }
}
