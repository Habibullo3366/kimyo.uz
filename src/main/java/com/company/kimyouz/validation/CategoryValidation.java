package com.company.kimyouz.validation;

import com.company.kimyouz.dto.ErrorDto;
<<<<<<< HEAD
import com.company.kimyouz.dto.request.RequestCategoryDto;
=======
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.repository.CategoryRepository;
>>>>>>> 53a017f012e6af853c75eaef64be739d1b0dd86a
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryValidation {
<<<<<<< HEAD
    public List<ErrorDto> categoryValid(RequestCategoryDto dto) {
=======

    private final CategoryRepository categoryRepository;

    public List<ErrorDto> categoryValid(ResponseCategoryDto dto) {
>>>>>>> 53a017f012e6af853c75eaef64be739d1b0dd86a
        List<ErrorDto> errorsList = new ArrayList<>();
        if (StringUtils.isBlank(dto.getCategoryName())) {
            errorsList.add(new ErrorDto("categoryName", "CategoryName cannot be null or empty."));
        }
        if (this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(dto.getCategoryId()).isPresent()){
            errorsList.add(new ErrorDto("categoryName","Category name is already exist"));
        }

        return errorsList;
    }
}
