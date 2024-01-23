package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestOrdersDto;
import com.company.kimyouz.dto.response.ResponseOrdersDto;
import com.company.kimyouz.entity.Orders;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-22T13:12:09+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
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
}
