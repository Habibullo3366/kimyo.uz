package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestOrdersDto;
import com.company.kimyouz.dto.request.RequestOrdersItemDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.dto.response.ResponseOrdersItemDto;
import com.company.kimyouz.entity.Category;
import com.company.kimyouz.entity.Orders;
import com.company.kimyouz.entity.OrdersItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class OrdersItemMapper {


    public abstract OrdersItem toEntity(RequestOrdersItemDto dto);


    public abstract ResponseOrdersItemDto toDto(OrdersItem ordersItem);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = OrdersItem.class)
    public abstract OrdersItem updateOrdersItem(@MappingTarget OrdersItem ordersItem, RequestOrdersItemDto dto);

}
