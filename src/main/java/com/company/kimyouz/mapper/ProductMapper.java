package com.company.kimyouz.mapper;

import com.company.kimyouz.dto.request.RequestProductDto;
import com.company.kimyouz.dto.response.ResponseProductDto;
import com.company.kimyouz.entity.Product;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Mapping(target = "prodId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Product toEntity(RequestProductDto dto);


    public abstract ResponseProductDto toDto(Product product);



    @Mapping(target = "prodId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = Product.class)
    public abstract Product updateProduct(RequestProductDto dto, @MappingTarget Product product);

}
