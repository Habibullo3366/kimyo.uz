package com.company.kimyouz.service;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestCategoryDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.entity.Category;
import com.company.kimyouz.service.mapper.CategoryMapper;
import com.company.kimyouz.repository.CategoryRepository;
import com.company.kimyouz.service.validation.CategoryValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryValidation categoryValidation;
    private final CategoryRepository categoryRepository;


    public ResponseDto<ResponseCategoryDto> createEntity(RequestCategoryDto dto) {
        List<ErrorDto> errors = this.categoryValidation.categoryValid(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ResponseCategoryDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errors)
                    .build();
        }

        try {
            Category category = this.categoryMapper.toEntity(dto);
            category.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseCategoryDto>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.categoryMapper.toDto(
                                    this.categoryRepository.save(category)
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseCategoryDto>builder()
                    .code(-2)
                    .message(String.format("Category while saving error message :: %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<ResponseCategoryDto> getEntity(Integer entityId) {
        Optional<Category> optional = this.categoryRepository
                .findByCategoryIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseCategoryDto>builder()
                    .code(-1)
                    .message(String.format("Category with %d:id is not found!", entityId))
                    .build();
        }
        return ResponseDto.<ResponseCategoryDto>builder()
                .success(true)
                .message("OK")
                .content(
                        this.categoryMapper.toDto(optional.get())
                )
                .build();
    }


    public ResponseDto<ResponseCategoryDto> updateEntity(Integer entityId, RequestCategoryDto dto) {
        try {
            Optional<Category> optional = this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()) {
                return ResponseDto.<ResponseCategoryDto>builder()
                        .code(-1)
                        .message(String.format("Category with %d:id is not found!", entityId))
                        .build();
            }
            Category category = optional.get();
            category.setUpdatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseCategoryDto>builder()
                    .success(true)
                    .message("OK")
                    .content(this.categoryMapper.toDto(
                                    this.categoryRepository.save(
                                            category
                                    )
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseCategoryDto>builder()
                    .code(-1)
                    .message(String.format("Category with %d:id is not found!", entityId))
                    .build();
        }
    }


    public ResponseDto<ResponseCategoryDto> deleteEntity(Integer entityId) {
        Optional<Category> optional = this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseCategoryDto>builder()
                    .code(-1)
                    .message(String.format("Category with %d:id is not found!", entityId))
                    .build();
        }
        Category category = optional.get();
        category.setDeletedAt(LocalDateTime.now());
        this.categoryRepository.save(category);
        return ResponseDto.<ResponseCategoryDto>builder()
                .success(true)
                .message("OK")
                .content(this.categoryMapper.toDto(category))
                .build();
    }



}
