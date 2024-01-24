package com.company.kimyouz.unit.category;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.entity.Category;
import com.company.kimyouz.repository.CategoryRepository;
import com.company.kimyouz.service.CategoryService;
import com.company.kimyouz.service.mapper.CategoryMapper;
import com.company.kimyouz.service.validation.CategoryValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestCategoryService {

    private CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;
    private CategoryValidation categoryValidation;
    private CategoryService categoryService;

    @BeforeEach
    public void initMethod() {
        categoryMapper = mock(CategoryMapper.class);
        categoryRepository = mock(CategoryRepository.class);
        categoryValidation = mock(CategoryValidation.class);
        categoryService = new CategoryService(categoryMapper, categoryValidation, categoryRepository);

    }

    @Test
    public void testCreateCategoryPositive() {

        when(categoryValidation.categoryValid(any()))
                .thenReturn(new ArrayList<>());

        when(categoryMapper.toDto(any()))
                .thenReturn(ResponseCategoryDto.builder()
                        .categoryId(1)
                        .categoryName("Category")
                        .build());

        Category category = Category.builder()
                .categoryId(1)
                .categoryName("Category")
                .build();

        when(categoryMapper.toEntity(any()))
                .thenReturn(category);

        when(categoryRepository.save(any()))
                .thenReturn(category);

        ResponseDto<ResponseCategoryDto> response = this.categoryService.createEntity(any());
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");

        verify(this.categoryMapper, times(1)).toDto(any());
        verify(this.categoryMapper, times(1)).toEntity(any());
        verify(this.categoryRepository, times(1)).save(any());
        verify(this.categoryValidation, times(1)).categoryValid(any());

    }

    @Test
    public void testCreateCategoryException() {

        when(this.categoryValidation.categoryValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.categoryRepository.save(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseCategoryDto> response = this.categoryService.createEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -2, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");

    }

    @Test
    public void testCreateCategoryValidation() {

        when(this.categoryValidation.categoryValid(any()))
                .thenReturn(List.of(
                        new ErrorDto("categoryId", String.format("Category with this %d is not found", 1))

                        )
                );

        ResponseDto<ResponseCategoryDto> response = this.categoryService.createEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testGetCategoryPositive() {

        ResponseCategoryDto responseCategoryDto = ResponseCategoryDto.builder()
                .categoryId(1)
                .categoryName("Category")
                .build();

        when(this.categoryMapper.toDto(any()))
                .thenReturn(responseCategoryDto);

        when(this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        Category.builder()
                                .categoryId(1)
                                .categoryName("Category")
                                .build()
                        )
                );

        ResponseDto<ResponseCategoryDto> response = this.categoryService.getEntity(any());
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testGetCategoryNegative() {

        when(this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseCategoryDto> response = this.categoryService.getEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testUpdateCategoryPositive() {

        when(this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        Category.builder()
                                .categoryId(1)
                                .categoryName("Category")
                                .build()

                        )
                );

        when(this.categoryMapper.toDto(any()))
                .thenReturn(ResponseCategoryDto.builder()
                        .categoryId(1)
                        .categoryName("category")
                        .build());

        when(this.categoryMapper.updateCategory(any(), any()))
                .thenReturn(Category.builder()
                        .categoryId(1)
                        .categoryName("Category")
                        .build());

        ResponseDto<ResponseCategoryDto> response = this.categoryService.updateEntity(1, any());
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testUpdateCategoryNegative() {

        when(this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseCategoryDto> response = this.categoryService.updateEntity(1, any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testUpdateCategoryException() {

        when(this.categoryValidation.categoryValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.categoryRepository.save(any()))
                .thenThrow(RuntimeException.class);


        ResponseDto<ResponseCategoryDto> response = this.categoryService.updateEntity(1, any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }


    @Test
    public void testDeleteCategoryPositive() {

        when(this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        Category.builder()
                                .categoryId(1)
                                .categoryName("Category")
                                .build()

                        )
                );

        when(this.categoryMapper.toDto(any()))
                .thenReturn(ResponseCategoryDto.builder()
                        .categoryId(1)
                        .categoryName("Category")
                        .build());

        when(this.categoryMapper.toEntity(any()))
                .thenReturn(Category.builder()
                        .categoryId(1)
                        .categoryName("Category")
                        .build());

        ResponseDto<ResponseCategoryDto> response = this.categoryService.deleteEntity(any());
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");


    }

    @Test
    public void testDeleteCategoryNegative() {

        when(this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseCategoryDto> response = this.categoryService.deleteEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testDeleteCategoryException() {

        when(this.categoryValidation.categoryValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.categoryRepository.save(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseCategoryDto> response = this.categoryService.deleteEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");


    }
}
