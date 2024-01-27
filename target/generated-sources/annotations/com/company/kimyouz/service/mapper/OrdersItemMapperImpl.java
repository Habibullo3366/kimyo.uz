package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestOrdersItemDto;
import com.company.kimyouz.dto.response.ResponseOrdersItemDto;
import com.company.kimyouz.entity.OrdersItem;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-25T15:04:25+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class OrdersItemMapperImpl extends OrdersItemMapper {

    @Override
    public OrdersItem toEntity(RequestOrdersItemDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrdersItem.OrdersItemBuilder ordersItem = OrdersItem.builder();

        ordersItem.orderItemId( dto.getOrderItemId() );
        ordersItem.quantity( dto.getQuantity() );
        ordersItem.totalPrice( dto.getTotalPrice() );
        ordersItem.orderId( dto.getOrderId() );
        ordersItem.productId( dto.getProductId() );

        return ordersItem.build();
    }

    @Override
    public ResponseOrdersItemDto toDto(OrdersItem ordersItem) {
        if ( ordersItem == null ) {
            return null;
        }

        ResponseOrdersItemDto.ResponseOrdersItemDtoBuilder responseOrdersItemDto = ResponseOrdersItemDto.builder();

        responseOrdersItemDto.orderItemId( ordersItem.getOrderItemId() );
        responseOrdersItemDto.quantity( ordersItem.getQuantity() );
        responseOrdersItemDto.totalPrice( ordersItem.getTotalPrice() );
        responseOrdersItemDto.orderId( ordersItem.getOrderId() );
        responseOrdersItemDto.productId( ordersItem.getProductId() );
        responseOrdersItemDto.product( ordersItem.getProduct() );
        responseOrdersItemDto.createdAt( ordersItem.getCreatedAt() );
        responseOrdersItemDto.updatedAt( ordersItem.getUpdatedAt() );
        responseOrdersItemDto.deletedAt( ordersItem.getDeletedAt() );

        return responseOrdersItemDto.build();
    }

    @Override
    public OrdersItem updateOrdersItem(OrdersItem ordersItem, RequestOrdersItemDto dto) {
        if ( dto == null ) {
            return ordersItem;
        }

        if ( dto.getOrderItemId() != null ) {
            ordersItem.setOrderItemId( dto.getOrderItemId() );
        }
        if ( dto.getQuantity() != null ) {
            ordersItem.setQuantity( dto.getQuantity() );
        }
        if ( dto.getTotalPrice() != null ) {
            ordersItem.setTotalPrice( dto.getTotalPrice() );
        }
        if ( dto.getOrderId() != null ) {
            ordersItem.setOrderId( dto.getOrderId() );
        }
        if ( dto.getProductId() != null ) {
            ordersItem.setProductId( dto.getProductId() );
        }

        return ordersItem;
    }
}
