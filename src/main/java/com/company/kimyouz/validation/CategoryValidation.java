package com.company.kimyouz.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.request.RequestCategoryDto;
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.repository.CategoryRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryValidation {

    private final CategoryRepository categoryRepository;

    public List<ErrorDto> categoryValid(RequestCategoryDto dto) {
        List<ErrorDto> errorsList = new ArrayList<>();
        if (StringUtils.isBlank(dto.getCategoryName())) {
            errorsList.add(new ErrorDto("categoryName", "CategoryName cannot be null or empty."));
        }

        return errorsList;
    }
}
