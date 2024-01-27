package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestOrdersDto;
import com.company.kimyouz.dto.response.ResponseOrdersDto;
import com.company.kimyouz.dto.response.ResponseOrdersItemDto;
import com.company.kimyouz.entity.Orders;
import com.company.kimyouz.entity.OrdersItem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-25T15:04:25+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class OrdersMapperImpl extends OrdersMapper {

    @Override
    public Orders toEntity(RequestOrdersDto dto) {
        if ( dto == null ) {
            return null;
        }

        Orders.OrdersBuilder orders = Orders.builder();

        orders.orderDate( dto.getOrderDate() );
        orders.totalPrice( dto.getTotalPrice() );

        return orders.build();
    }

    @Override
    public ResponseOrdersDto toDto(Orders orders) {
        if ( orders == null ) {
            return null;
        }

        ResponseOrdersDto.ResponseOrdersDtoBuilder responseOrdersDto = ResponseOrdersDto.builder();

        responseOrdersDto.orderId( orders.getOrderId() );
        responseOrdersDto.orderDate( orders.getOrderDate() );
        responseOrdersDto.totalPrice( orders.getTotalPrice() );
        responseOrdersDto.userId( orders.getUserId() );
        responseOrdersDto.ordersItems( ordersItemListToResponseOrdersItemDtoList( orders.getOrdersItems() ) );
        responseOrdersDto.createdAt( orders.getCreatedAt() );
        responseOrdersDto.updatedAt( orders.getUpdatedAt() );
        responseOrdersDto.deletedAt( orders.getDeletedAt() );

        return responseOrdersDto.build();
    }

    @Override
    public Orders updateOrders(Orders orders, RequestOrdersDto dto) {
        if ( dto == null ) {
            return orders;
        }

        if ( dto.getOrderDate() != null ) {
            orders.setOrderDate( dto.getOrderDate() );
        }
        if ( dto.getTotalPrice() != null ) {
            orders.setTotalPrice( dto.getTotalPrice() );
        }

        return orders;
    }

    protected ResponseOrdersItemDto ordersItemToResponseOrdersItemDto(OrdersItem ordersItem) {
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

    protected List<ResponseOrdersItemDto> ordersItemListToResponseOrdersItemDtoList(List<OrdersItem> list) {
        if ( list == null ) {
            return null;
        }

        List<ResponseOrdersItemDto> list1 = new ArrayList<ResponseOrdersItemDto>( list.size() );
        for ( OrdersItem ordersItem : list ) {
            list1.add( ordersItemToResponseOrdersItemDto( ordersItem ) );
        }

        return list1;
    }
}
