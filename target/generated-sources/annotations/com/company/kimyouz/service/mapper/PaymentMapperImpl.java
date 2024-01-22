package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestPaymentDto;
import com.company.kimyouz.dto.response.ResponsePaymentDto;
import com.company.kimyouz.entity.Payment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-20T17:05:15+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl extends PaymentMapper {

    @Override
    public Payment toEntity(RequestPaymentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Payment.PaymentBuilder payment = Payment.builder();

        payment.paymentDate( dto.getPaymentDate() );
        payment.totalPrice( dto.getTotalPrice() );
        payment.orderId( dto.getOrderId() );
        payment.userId( dto.getUserId() );

        return payment.build();
    }

    @Override
    public ResponsePaymentDto toDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        ResponsePaymentDto.ResponsePaymentDtoBuilder responsePaymentDto = ResponsePaymentDto.builder();

        responsePaymentDto.paymentId( payment.getPaymentId() );
        responsePaymentDto.paymentDate( payment.getPaymentDate() );
        responsePaymentDto.totalPrice( payment.getTotalPrice() );
        responsePaymentDto.orderId( payment.getOrderId() );
        responsePaymentDto.userId( payment.getUserId() );
        responsePaymentDto.orders( payment.getOrders() );
        responsePaymentDto.createdAt( payment.getCreatedAt() );
        responsePaymentDto.updatedAt( payment.getUpdatedAt() );
        responsePaymentDto.deletedAt( payment.getDeletedAt() );

        return responsePaymentDto.build();
    }

    @Override
    public Payment updatePayment(Payment payment, RequestPaymentDto dto) {
        if ( dto == null ) {
            return payment;
        }

        if ( dto.getPaymentDate() != null ) {
            payment.setPaymentDate( dto.getPaymentDate() );
        }
        if ( dto.getTotalPrice() != null ) {
            payment.setTotalPrice( dto.getTotalPrice() );
        }
        if ( dto.getOrderId() != null ) {
            payment.setOrderId( dto.getOrderId() );
        }
        if ( dto.getUserId() != null ) {
            payment.setUserId( dto.getUserId() );
        }

        return payment;
    }
}
