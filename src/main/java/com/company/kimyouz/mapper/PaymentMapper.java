package com.company.kimyouz.mapper;

import com.company.kimyouz.dto.request.RequestOrdersDto;
import com.company.kimyouz.dto.request.RequestPaymentDto;
import com.company.kimyouz.dto.response.ResponseOrdersDto;
import com.company.kimyouz.dto.response.ResponsePaymentDto;
import com.company.kimyouz.entity.Orders;
import com.company.kimyouz.entity.Payment;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public  abstract class PaymentMapper {

    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Orders toEntity(RequestPaymentDto dto);


    public abstract ResponsePaymentDto toDto(Payment payment);


    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Payment.class)
    public abstract Orders updatePayment(@MappingTarget Payment payment, RequestPaymentDto dto);
}
