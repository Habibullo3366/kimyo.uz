package com.company.kimyouz.mapper;

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

    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract OrdersItem toEntity(RequestOrdersItemDto dto);


    public abstract ResponseOrdersItemDto toDto(OrdersItem ordersItem);


    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = OrdersItem.class)
    public abstract Orders updateOrdersItem(@MappingTarget OrdersItem ordersItem, RequestOrdersItemDto dto);

}
