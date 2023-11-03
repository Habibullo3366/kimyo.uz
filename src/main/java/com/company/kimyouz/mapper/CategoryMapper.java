package com.company.kimyouz.mapper;

import com.company.kimyouz.dto.request.RequestCategoryDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.entity.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Category toEntity(RequestCategoryDto dto);

    public abstract ResponseCategoryDto toDto(Category category);

    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = ResponseCategoryDto.class)
    public abstract Category updateCategory(RequestCategoryDto dto, @MappingTarget Category category);


}
