package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestOrdersDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.dto.response.ResponseOrdersDto;
import com.company.kimyouz.entity.Category;
import com.company.kimyouz.entity.Orders;
import org.aspectj.weaver.ast.Or;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class OrdersMapper {

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Orders toEntity(RequestOrdersDto dto);

    @Mapping(target = "ordersItems", ignore = true)
    public abstract ResponseOrdersDto toDto(Orders orders);

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "ordersItems", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = Orders.class)
    public abstract Orders updateOrders(@MappingTarget Orders orders, RequestOrdersDto dto);



}
