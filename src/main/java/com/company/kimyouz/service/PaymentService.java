package com.company.kimyouz.service;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestPaymentDto;
import com.company.kimyouz.dto.response.ResponsePaymentDto;
import com.company.kimyouz.entity.Payment;
import com.company.kimyouz.service.mapper.PaymentMapper;
import com.company.kimyouz.repository.PaymentRepository;
import com.company.kimyouz.validation.PaymentValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentValidation paymentValidation;
    private final PaymentRepository paymentRepository;


    public ResponseDto<ResponsePaymentDto> createEntity(RequestPaymentDto dto) {
        List<ErrorDto> errors = this.paymentValidation.paymentValid(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ResponsePaymentDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errors)
                    .build();
        }

        try {
            Payment payment = this.paymentMapper.toEntity(dto);
            payment.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<ResponsePaymentDto>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.paymentMapper.toDto(
                                    this.paymentRepository.save(payment)
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponsePaymentDto>builder()
                    .code(-2)
                    .message(String.format("Payment while saving error message :: %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<ResponsePaymentDto> getEntity(Integer entityId) {
        Optional<Payment> optional = this.paymentRepository.findByPaymentIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponsePaymentDto>builder()
                    .code(-1)
                    .message(String.format("Payment with %d:id is not found!", entityId))
                    .build();
        }
        return ResponseDto.<ResponsePaymentDto>builder()
                .success(true)
                .message("OK")
                .content(
                        this.paymentMapper.toDto(optional.get())
                )
                .build();
    }


    public ResponseDto<ResponsePaymentDto> updateEntity(Integer entityId, RequestPaymentDto dto) {
        try {
            Optional<Payment> optional = this.paymentRepository.findByPaymentIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()) {
                return ResponseDto.<ResponsePaymentDto>builder()
                        .code(-1)
                        .message(String.format("Payment with %d:id is not found!", entityId))
                        .build();
            }
            Payment payment = optional.get();
            payment.setUpdatedAt(LocalDateTime.now());
            return ResponseDto.<ResponsePaymentDto>builder()
                    .success(true)
                    .message("OK")
                    .content(this.paymentMapper.toDto(
                                    this.paymentRepository.save(
                                            payment
                                    )
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponsePaymentDto>builder()
                    .code(-1)
                    .message(String.format("Payment with %d:id is not found!", entityId))
                    .build();
        }
    }


    public ResponseDto<ResponsePaymentDto> deleteEntity(Integer entityId) {
        Optional<Payment> optional = this.paymentRepository.findByPaymentIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponsePaymentDto>builder()
                    .code(-1)
                    .message(String.format("Payment with %d:id is not found!", entityId))
                    .build();
        }
        Payment payment = optional.get();
        payment.setDeletedAt(LocalDateTime.now());
        this.paymentRepository.save(payment);
        return ResponseDto.<ResponsePaymentDto>builder()
                .success(true)
                .message("OK")
                .content(this.paymentMapper.toDto(payment))
                .build();
    }


}
