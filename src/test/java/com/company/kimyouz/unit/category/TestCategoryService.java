package com.company.kimyouz.unit.category;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.entity.Category;
import com.company.kimyouz.repository.CategoryRepository;
import com.company.kimyouz.service.CategoryService;
import com.company.kimyouz.service.mapper.CategoryMapper;
import com.company.kimyouz.validation.CategoryValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class TestCategoryService {

    private CategoryService categoryService;

    private CategoryMapper categoryMapper;

    private CategoryRepository categoryRepository;

    private CategoryValidation categoryValidation;

    @BeforeEach
    public void initMethod() {
        categoryService = Mockito.mock(CategoryService.class);

        categoryMapper = Mockito.mock(CategoryMapper.class);

        categoryRepository = Mockito.mock(CategoryRepository.class);

        categoryValidation = Mockito.mock(CategoryValidation.class);

        this.categoryService = new CategoryService(categoryMapper, categoryValidation, categoryRepository);
    }


    @Test
    public void TestCreateCategoryPositive() {

        Mockito.when(this.categoryMapper.toDto(Mockito.any())).thenReturn(
                ResponseCategoryDto.builder()
                        .categoryId(1)
                        .categoryName("Cars")
                        .build()
        );

        Mockito.when(this.categoryRepository.save(Mockito.any())).thenReturn(
                Category.builder()
                        .categoryName("Cars")
                        .categoryId(1)
                        .build()
        );

        Mockito.when(this.categoryValidation.categoryValid(Mockito.any())).thenReturn(
                new ArrayList<>()
        );

        Mockito.when(this.categoryMapper.toEntity(Mockito.any())).thenReturn(
                Category.builder()
                        .categoryName("Cars")
                        .categoryId(1)
                        .build()
        );

        ResponseDto<ResponseCategoryDto> dto = this.categoryService.createEntity(Mockito.any());


        Assertions.assertEquals(dto.getCode(), 0, "Unknown get code value returned");
        Assertions.assertTrue(dto.isSuccess(), "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNotNull(dto.getContent(), "Unknown content value returned");


        Mockito.verify(this.categoryMapper, Mockito.times(1)).toDto(any());


    }

    @Test
    public void TestCreateCategoryException() {
        Mockito.when(this.categoryMapper.toDto(Mockito.any())).thenReturn(
                ResponseCategoryDto.builder()
                        .categoryId(1)
                        .categoryName("Cars")
                        .build()
        );

        Mockito.when(this.categoryRepository.save(Mockito.any())).thenThrow(RuntimeException.class);

        Mockito.when(this.categoryValidation.categoryValid(Mockito.any())).thenReturn(
                new ArrayList<>()
        );

        Mockito.when(this.categoryMapper.toEntity(Mockito.any())).thenReturn(
                Category.builder()
                        .categoryName("Cars")
                        .categoryId(1)
                        .build()
        );

        ResponseDto<ResponseCategoryDto> dto = this.categoryService.createEntity(Mockito.any());


        Assertions.assertEquals(dto.getCode(), -2, "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess(), "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent(), "Unknown content value returned");
    }

    @Test
    public void TestCreateCategoryValidation() {
        Mockito.when(this.categoryMapper.toDto(Mockito.any())).thenReturn(
                ResponseCategoryDto.builder()
                        .categoryId(1)
                        .categoryName("Cars")
                        .build()
        );

        Mockito.when(this.categoryRepository.save(Mockito.any())).thenReturn(
                Category.builder()
                        .categoryName("Cars")
                        .categoryId(1)
                        .build()
        );

        Mockito.when(this.categoryValidation.categoryValid(Mockito.any())).thenReturn(
                List.of(new ErrorDto("categoryName", "CategoryName cannot be null or empty."))
        );

        Mockito.when(this.categoryMapper.toEntity(Mockito.any())).thenReturn(
                Category.builder()
                        .categoryName("Cars")
                        .categoryId(1)
                        .build()
        );

        ResponseDto<ResponseCategoryDto> dto = this.categoryService.createEntity(Mockito.any());


        Assertions.assertEquals(dto.getCode(), -3, "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess(), "Unknown success value returned");
        Assertions.assertNotNull(dto.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent(), "Unknown content value returned");
    }

    @Test
    public void TestGetCategoryPositive() {


        Mockito.when(this.categoryMapper.toDto(Mockito.any())).thenReturn(
                ResponseCategoryDto.builder()
                        .categoryId(1)
                        .categoryName("Cars")
                        .build()
        );

        Mockito.when(this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.of(
                        Category.builder()
                                .categoryId(1)
                                .categoryName("Cars")
                                .build()
                )
        );


        ResponseDto<ResponseCategoryDto> dto = this.categoryService.getEntity(Mockito.any());


        Assertions.assertEquals(dto.getCode(), 0, "Unknown get code value returned");
        Assertions.assertTrue(dto.isSuccess(), "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNotNull(dto.getContent(), "Unknown content value returned");

    }

    @Test
    public void TestGetCategoryNegative() {
        Mockito.when(this.categoryMapper.toDto(Mockito.any())).thenReturn(
                ResponseCategoryDto.builder()
                        .categoryId(1)
                        .categoryName("Cars")
                        .build()
        );

        Mockito.when(this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.empty()
        );


        ResponseDto<ResponseCategoryDto> dto = this.categoryService.getEntity(Mockito.any());


        Assertions.assertEquals(dto.getCode(), -1, "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess(), "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent(), "Unknown content value returned");
    }

    @Test
    public void TestDeleteCategoryPositive() {


        Mockito.when(this.categoryMapper.toDto(Mockito.any())).thenReturn(
                ResponseCategoryDto.builder()
                        .categoryId(1)
                        .categoryName("Cars")
                        .build()
        );

        Mockito.when(this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.of(
                        Category.builder()
                                .categoryId(1)
                                .categoryName("Cars")
                                .build()
                )
        );

        Mockito.when(this.categoryRepository.save(any())).thenReturn(
                Category.builder()
                        .categoryId(1)
                        .categoryName("Cars")
                        .build()
        );

        ResponseDto<ResponseCategoryDto> dto = this.categoryService.deleteEntity(Mockito.any());


        Assertions.assertEquals(dto.getCode(), 0, "Unknown get code value returned");
        Assertions.assertTrue(dto.isSuccess(), "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNotNull(dto.getContent(), "Unknown content value returned");
    }

    @Test
    public void TestDeleteCategoryNegative() {

        Mockito.when(this.categoryMapper.toDto(Mockito.any())).thenReturn(
                ResponseCategoryDto.builder()
                        .categoryId(1)
                        .categoryName("Cars")
                        .build()
        );

        Mockito.when(this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.empty()
        );

        Mockito.when(this.categoryRepository.save(any())).thenReturn(
                Category.builder()
                        .categoryId(1)
                        .categoryName("Cars")
                        .build()
        );


        ResponseDto<ResponseCategoryDto> dto = this.categoryService.deleteEntity(Mockito.any());

        Assertions.assertEquals(dto.getCode(), -1, "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess(), "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent(), "Unknown content value returned");
    }

    @Test
    public void TestUpdateCategoryPositive() {

        Mockito.when(this.categoryMapper.toDto(Mockito.any())).thenReturn(
                ResponseCategoryDto.builder()
                        .categoryId(1)
                        .categoryName("Cars")
                        .build()
        );

        Mockito.when(this.categoryRepository.save(Mockito.any())).thenReturn(
                Category.builder()
                        .categoryName("Cars")
                        .categoryId(1)
                        .build()
        );


        Mockito.when(this.categoryMapper.toEntity(Mockito.any())).thenReturn(
                Category.builder()
                        .categoryName("Cars")
                        .categoryId(1)
                        .build()
        );

        Mockito.when(this.categoryMapper.updateCategory(any(), any())).thenReturn(
                Category.builder()
                        .categoryName("Cars")
                        .categoryId(1)
                        .build()
        );

        Mockito.when(this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.of(
                        Category.builder()
                                .categoryName("Cars")
                                .categoryId(1)
                                .build()
                )
        );

        ResponseDto<ResponseCategoryDto> dto = this.categoryService.updateEntity(1, Mockito.any());

        Assertions.assertEquals(dto.getCode(), 0, "Unknown get code value returned");
        Assertions.assertTrue(dto.isSuccess(), "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNotNull(dto.getContent(), "Unknown content value returned");
    }

    @Test
    public void TestUpdateCategoryNegative() {

        Mockito.when(this.categoryMapper.toDto(Mockito.any())).thenReturn(
                ResponseCategoryDto.builder()
                        .categoryId(1)
                        .categoryName("Cars")
                        .build()
        );

        Mockito.when(this.categoryRepository.save(Mockito.any())).thenReturn(
                Category.builder()
                        .categoryName("Cars")
                        .categoryId(1)
                        .build()
        );


        Mockito.when(this.categoryMapper.toEntity(Mockito.any())).thenReturn(
                Category.builder()
                        .categoryName("Cars")
                        .categoryId(1)
                        .build()
        );

        Mockito.when(this.categoryMapper.updateCategory(any(), any())).thenReturn(
                Category.builder()
                        .categoryName("Cars")
                        .categoryId(1)
                        .build()
        );

        Mockito.when(this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.empty()
        );

        ResponseDto<ResponseCategoryDto> dto = this.categoryService.updateEntity(1, Mockito.any());

        Assertions.assertEquals(dto.getCode(), -1, "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess(), "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent(), "Unknown content value returned");
    }

    @Test
    public void TestUpdateCategoryException() {

        Mockito.when(this.categoryMapper.toDto(Mockito.any())).thenReturn(
                ResponseCategoryDto.builder()
                        .categoryId(1)
                        .categoryName("Cars")
                        .build()
        );

        Mockito.when(this.categoryRepository.save(Mockito.any())).thenThrow(RuntimeException.class);

        Mockito.when(this.categoryMapper.toEntity(Mockito.any())).thenReturn(
                Category.builder()
                        .categoryName("Cars")
                        .categoryId(1)
                        .build()
        );

        Mockito.when(this.categoryMapper.updateCategory(any(), any())).thenReturn(
                Category.builder()
                        .categoryName("Cars")
                        .categoryId(1)
                        .build()
        );

        Mockito.when(this.categoryRepository.findByCategoryIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.of(
                        Category.builder()
                                .categoryName("Cars")
                                .categoryId(1)
                                .build()
                )
        );

        ResponseDto<ResponseCategoryDto> dto = this.categoryService.updateEntity(1, Mockito.any());

        Assertions.assertEquals(dto.getCode(), -2, "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess(), "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent(), "Unknown content value returned");

    }
}
