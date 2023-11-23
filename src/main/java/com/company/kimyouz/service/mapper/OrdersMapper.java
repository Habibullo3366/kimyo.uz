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


    public abstract Orders toEntity(RequestOrdersDto dto);

    public abstract ResponseOrdersDto toDto(Orders orders);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = Orders.class)
    public abstract Orders updateOrders(@MappingTarget Orders orders, RequestOrdersDto dto);



}
