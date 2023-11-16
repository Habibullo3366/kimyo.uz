package com.company.kimyouz.service;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestOrdersItemDto;
import com.company.kimyouz.dto.response.ResponseOrdersItemDto;
import com.company.kimyouz.entity.OrdersItem;
import com.company.kimyouz.service.mapper.OrdersItemMapper;
import com.company.kimyouz.repository.OrdersItemRepository;
import com.company.kimyouz.validation.OrdersItemValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderedItemService {

    private final OrdersItemMapper ordersItemMapper;
    private final OrdersItemValidation ordersItemValidation;
    private final OrdersItemRepository ordersItemRepository;


    public ResponseDto<ResponseOrdersItemDto> createEntity(RequestOrdersItemDto dto) {
        List<ErrorDto> errors = this.ordersItemValidation.ordersItemValid(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ResponseOrdersItemDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errors)
                    .build();
        }

        try {
            OrdersItem ordersItem = this.ordersItemMapper.toEntity(dto);
            ordersItem.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseOrdersItemDto>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.ordersItemMapper.toDto(
                                    this.ordersItemRepository.save(ordersItem)
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseOrdersItemDto>builder()
                    .code(-2)
                    .message(String.format("Ordered item while saving error message :: %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<ResponseOrdersItemDto> getEntity(Integer entityId) {
        Optional<OrdersItem> optional = this.ordersItemRepository
                .findByOrderItemIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseOrdersItemDto>builder()
                    .code(-1)
                    .message(String.format("Ordered item with %d:id is not found!", entityId))
                    .build();
        }
        return ResponseDto.<ResponseOrdersItemDto>builder()
                .success(true)
                .message("OK")
                .content(
                        this.ordersItemMapper.toDto(optional.get())
                )
                .build();
    }


    public ResponseDto<ResponseOrdersItemDto> updateEntity(Integer entityId, RequestOrdersItemDto dto) {
        try {
            Optional<OrdersItem> optional = this.ordersItemRepository.findByOrderItemIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()) {
                return ResponseDto.<ResponseOrdersItemDto>builder()
                        .code(-1)
                        .message(String.format("Ordered item with %d:id is not found!", entityId))
                        .build();
            }
            OrdersItem orderedItem = optional.get();
            orderedItem.setUpdatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseOrdersItemDto>builder()
                    .success(true)
                    .message("OK")
                    .content(this.ordersItemMapper.toDto(
                                    this.ordersItemRepository.save(
                                            this.ordersItemMapper.updateOrdersItem(orderedItem, dto)
                                    )
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseOrdersItemDto>builder()
                    .code(-1)
                    .message(String.format("Ordered item with %d:id is not found!", entityId))
                    .build();
        }
    }


    public ResponseDto<ResponseOrdersItemDto> deleteEntity(Integer entityId) {
        Optional<OrdersItem> optional = this.ordersItemRepository.findByOrderItemIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseOrdersItemDto>builder()
                    .code(-1)
                    .message(String.format("Ordered item with %d:id is not found!", entityId))
                    .build();
        }
        OrdersItem ordersItem = optional.get();
        ordersItem.setDeletedAt(LocalDateTime.now());
        this.ordersItemRepository.save(ordersItem);
        return ResponseDto.<ResponseOrdersItemDto>builder()
                .success(true)
                .message("OK")
                .content(this.ordersItemMapper.toDto(ordersItem))
                .build();
    }



}
