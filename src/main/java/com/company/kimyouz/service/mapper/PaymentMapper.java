package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestOrdersDto;
import com.company.kimyouz.dto.request.RequestPaymentDto;
import com.company.kimyouz.dto.response.ResponseOrdersDto;
import com.company.kimyouz.dto.response.ResponsePaymentDto;
import com.company.kimyouz.entity.Orders;
import com.company.kimyouz.entity.Payment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public  abstract class PaymentMapper {


    public abstract Payment toEntity(RequestPaymentDto dto);


    public abstract ResponsePaymentDto toDto(Payment payment);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Payment.class)
    public abstract Payment updatePayment(@MappingTarget Payment payment, RequestPaymentDto dto);
}
