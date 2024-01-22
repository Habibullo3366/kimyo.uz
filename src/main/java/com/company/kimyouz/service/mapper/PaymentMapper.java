package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestOrdersDto;
import com.company.kimyouz.dto.request.RequestPaymentDto;
import com.company.kimyouz.dto.response.ResponseOrdersDto;
import com.company.kimyouz.dto.response.ResponsePaymentDto;
import com.company.kimyouz.entity.Orders;
import com.company.kimyouz.entity.Payment;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring" ,  imports = Collectors.class)
public  abstract class PaymentMapper {

    @Lazy
    @Autowired
    protected OrdersMapper ordersMapper;

    @Mapping(target = "orders" , ignore = true)
    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Payment toEntity(RequestPaymentDto dto);

    @Mapping(target = "orders" , ignore = true)
    public abstract ResponsePaymentDto toDto(Payment payment);

    @Mapping(target = "orders" , expression = "java(this.ordersMapper.toDto(payment.getOrders()))")
    public abstract ResponsePaymentDto toDtoWithOrder(Payment payment);


    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "orders" , ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Payment.class)
    public abstract Payment updatePayment(@MappingTarget Payment payment, RequestPaymentDto dto);
}
