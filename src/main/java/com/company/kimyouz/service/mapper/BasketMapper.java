package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestBasketDto;
import com.company.kimyouz.dto.response.ResponseBasketDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.entity.Basket;
import com.company.kimyouz.entity.Category;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring" ,  imports = Collectors.class)
public abstract class BasketMapper {

    @Lazy
    @Autowired
    protected ProductMapper productMapper;


    @Mapping(target = "basketId",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "products" , ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract Basket toEntity(RequestBasketDto dto);


    @Mapping(target = "products" , ignore = true)
    public abstract ResponseBasketDto toDto(Basket basket);


    @Mapping(target = "products" , expression = "java(basket.getProducts().stream().map(this.productMapper::toDto).collect(Collectors.toList()))")
    public abstract ResponseBasketDto toDtoWithProducts(Basket basket);


    @Mapping(target = "basketId",ignore = true)
    @Mapping(target = "products" , ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Basket.class)
    public abstract Basket updateBasket(RequestBasketDto dto, @MappingTarget Basket basket);


}
