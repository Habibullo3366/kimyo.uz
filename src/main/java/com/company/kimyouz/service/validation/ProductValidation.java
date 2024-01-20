package com.company.kimyouz.service.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.request.RequestProductDto;
import com.company.kimyouz.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductValidation {

    private final CategoryRepository categoryRepository;

    public List<ErrorDto> productValid(RequestProductDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(dto.getCategoryId()).isEmpty()) {
            errorList.add(new ErrorDto("categoryId", "Category id cannot be null or empty"));
        }
        return errorList;
    }
}
