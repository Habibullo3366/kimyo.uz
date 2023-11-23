package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestCategoryDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.entity.Category;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class CategoryMapper {

    @Autowired
    protected ProductMapper productMapper;

    public abstract Category toEntity(RequestCategoryDto dto);


    public abstract ResponseCategoryDto toDto(Category category);


//    @Mapping(target = "products", expression = "java(category.getProducts().stream().map(this.productMapper::toDto).collect(Collectors.toSet()))")
    public abstract ResponseCategoryDto toDtoWithProducts(Category category);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = Category.class)
    public abstract Category updateCategory(RequestCategoryDto dto, @MappingTarget Category category);


}
