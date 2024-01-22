package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestBasketDto;
import com.company.kimyouz.dto.response.ResponseBasketDto;
import com.company.kimyouz.entity.Basket;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class BasketMapper {


    @Mapping(target = "basketId",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract Basket toEntity(RequestBasketDto dto);


    public abstract ResponseBasketDto toDto(Basket basket);




    @Mapping(target = "basketId",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Basket.class)
    public abstract Basket updateBasket(RequestBasketDto dto, @MappingTarget Basket basket);


}
