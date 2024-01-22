package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestOrdersDto;
import com.company.kimyouz.dto.request.RequestOrdersItemDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.dto.response.ResponseOrdersItemDto;
import com.company.kimyouz.entity.Category;
import com.company.kimyouz.entity.Orders;
import com.company.kimyouz.entity.OrdersItem;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring" ,  imports = Collectors.class)
public abstract class OrdersItemMapper {

    @Lazy
    @Autowired
    protected ProductMapper productMapper;

    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "product" , ignore = true)
    public abstract OrdersItem toEntity(RequestOrdersItemDto dto);

    @Mapping(target = "product" , ignore = true)
    public abstract ResponseOrdersItemDto toDto(OrdersItem ordersItem);

    @Mapping(target = "product" , expression = "java(this.productMapper.toDto(ordersItem.getProduct()))")
    public abstract ResponseOrdersItemDto toDtoWithProduct(OrdersItem ordersItem);


    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "product" , ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = OrdersItem.class)
    public abstract OrdersItem updateOrdersItem(@MappingTarget OrdersItem ordersItem, RequestOrdersItemDto dto);

}
