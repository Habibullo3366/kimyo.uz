package com.company.kimyouz.service;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestOrdersDto;
import com.company.kimyouz.dto.response.ResponseOrdersDto;
import com.company.kimyouz.entity.Orders;
import com.company.kimyouz.service.mapper.OrdersMapper;
import com.company.kimyouz.repository.OrdersRepository;
import com.company.kimyouz.service.validation.OrdersValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrdersMapper ordersMapper;
    private final OrdersValidation ordersValidation;
    private final OrdersRepository ordersRepository;


    public ResponseDto<ResponseOrdersDto> createEntity(RequestOrdersDto dto) {
        List<ErrorDto> errors = this.ordersValidation.ordersValid(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ResponseOrdersDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errors)
                    .build();
        }

        try {
            Orders order = this.ordersMapper.toEntity(dto);
            order.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseOrdersDto>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.ordersMapper.toDto(
                                    this.ordersRepository.save(order)
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseOrdersDto>builder()
                    .code(-2)
                    .message(String.format("Order while saving error message :: %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<ResponseOrdersDto> getEntity(Integer entityId) {
        Optional<Orders> optional = this.ordersRepository
                .findByOrderIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseOrdersDto>builder()
                    .code(-1)
                    .message(String.format("Order with %d:id is not found!", entityId))
                    .build();
        }
        return ResponseDto.<ResponseOrdersDto>builder()
                .success(true)
                .message("OK")
                .content(
                        this.ordersMapper.toDto(optional.get())
                )
                .build();
    }


    public ResponseDto<ResponseOrdersDto> updateEntity(Integer entityId, RequestOrdersDto dto) {
        try {
            Optional<Orders> optional = this.ordersRepository.findByOrderIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()) {
                return ResponseDto.<ResponseOrdersDto>builder()
                        .code(-1)
                        .message(String.format("Order with %d:id is not found!", entityId))
                        .build();
            }
            Orders order = optional.get();
            order.setUpdatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseOrdersDto>builder()
                    .success(true)
                    .message("OK")
                    .content(this.ordersMapper.toDto(
                                    this.ordersRepository.save(
                                            this.ordersMapper.updateOrders(order, dto)
                                    )
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseOrdersDto>builder()
                    .code(-4)
                    .message(e.getMessage())
                    .build();
        }
    }


    public ResponseDto<ResponseOrdersDto> deleteEntity(Integer entityId) {
        try {
            Optional<Orders> optional = this.ordersRepository.findByOrderIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()) {
                return ResponseDto.<ResponseOrdersDto>builder()
                        .code(-1)
                        .message(String.format("Order with %d:id is not found!", entityId))
                        .build();
            }
            Orders orders = optional.get();
            orders.setDeletedAt(LocalDateTime.now());
            this.ordersRepository.save(orders);
            return ResponseDto.<ResponseOrdersDto>builder()
                    .success(true)
                    .message("OK")
                    .content(this.ordersMapper.toDto(orders))
                    .build();

        }catch (Exception e) {
            return ResponseDto.<ResponseOrdersDto>builder()
                    .code(-4)
                    .message(e.getMessage())
                    .build();
        }
        }



}
