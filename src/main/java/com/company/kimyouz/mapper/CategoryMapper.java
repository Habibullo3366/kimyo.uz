package com.company.kimyouz.mapper;

import com.company.kimyouz.dto.request.RequestCategoryDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.entity.Category;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class CategoryMapper {

    @Lazy
    @Autowired
    private final ProductMapper productMapper;

    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "Products", ignore = true)
    public abstract Category toEntity(RequestCategoryDto dto);


    @Mapping(target = "Products", ignore = true)
    public abstract ResponseCategoryDto toDto(Category category);


    @Mapping(target = "products", expression = "java(category.getProducts().stream().map(this.ProductMapper::toDto).collect(Collectors.toSett()))")
    public abstract ResponseCategoryDto toDtoWithProducts(Category category);

    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "Products", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = ResponseCategoryDto.class)
    public abstract Category updateCategory(RequestCategoryDto dto, @MappingTarget Category category);


}
