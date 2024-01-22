package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestProductDto;
import com.company.kimyouz.dto.response.ResponseProductDto;
import com.company.kimyouz.entity.Product;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring" ,  imports = Collectors.class)
public abstract class ProductMapper {

    @Lazy
    @Autowired
    protected OrdersItemMapper ordersItemMapper;

    @Mapping(target = "prodId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
   // @Mapping(target = "basket" , ignore = true)
    @Mapping(target = "ordersItems" , ignore = true)
    public abstract Product toEntity(RequestProductDto dto);



   // @Mapping(target = "basket" , ignore = true)
    @Mapping(target = "ordersItems" , ignore = true)
    public abstract ResponseProductDto toDto(Product product);

   // @Mapping(target = "basket" , ignore = true)
    @Mapping(target = "ordersItems" , expression = "java(product.getOrdersItems().stream().map(this.ordersItemMapper::toDto).collect(Collectors.toList()))")
    public abstract ResponseProductDto toDtoWithOrderItems(Product product);

    @Mapping(target = "prodId", ignore = true)
   // @Mapping(target = "basket" , ignore = true)
    @Mapping(target = "ordersItems" , ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = Product.class)
    public abstract Product updateProduct(RequestProductDto dto, @MappingTarget Product product);

}
