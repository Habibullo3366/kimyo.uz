package com.company.kimyouz.service;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestBasketDto;
import com.company.kimyouz.dto.response.ResponseBasketDto;
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.entity.Basket;
import com.company.kimyouz.service.mapper.BasketMapper;
import com.company.kimyouz.repository.BasketRepository;
import com.company.kimyouz.validation.BasketValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketMapper basketMapper;
    private final BasketValidation basketValidation;
    private final BasketRepository basketRepository;


    public ResponseDto<ResponseBasketDto> createEntity(RequestBasketDto dto) {
        List<ErrorDto> errorList = this.basketValidation.basketValid(dto);
        if (!errorList.isEmpty()) {
            return ResponseDto.<ResponseBasketDto>builder()
                    .code(-3)
                    .message("Validation error!")
                    .build();
        }

        try {
            return ResponseDto.<ResponseBasketDto>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.basketMapper.toDto(
                                    this.basketRepository.save(
                                            this.basketMapper.toEntity(dto)
                                    )
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseBasketDto>builder()
                    .code(-2)
                    .message(String.format("Basket while saving error! Message :: %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<ResponseBasketDto> getEntity(Integer entityId) {
        return this.basketRepository.findByBasketIdAndDeletedAtIsNull(entityId)
                .map(basket -> ResponseDto.<ResponseBasketDto>builder()
                        .success(true)
                        .message("OK")
                        .content(this.basketMapper.toDto(basket))
                        .build())
                .orElse(ResponseDto.<ResponseBasketDto>builder()
                        .message(String.format("Basket with %d id is not found!", entityId))
                        .build());
    }


    public ResponseDto<ResponseBasketDto> updateEntity(Integer entityId, RequestBasketDto dto) {
        List<ErrorDto> errorList = this.basketValidation.basketValid(dto);
        if (!errorList.isEmpty()) {
            return ResponseDto.<ResponseBasketDto>builder()
                    .code(-3)
                    .message("Validation error!")
                    .build();
        }
        try {
            return this.basketRepository.findByBasketIdAndDeletedAtIsNull(entityId)
                    .map(basket -> ResponseDto.<ResponseBasketDto>builder()
                            .success(true)
                            .message("OK")
                            .content(this.basketMapper.toDto(
                                            this.basketRepository.save(
                                                    this.basketMapper.updateBasket(dto, basket)
                                            )
                                    )
                            )
                            .build()
                    )
                    .orElse(ResponseDto.<ResponseBasketDto>builder()
                            .message(String.format("Basket with %d id is not found!", entityId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<ResponseBasketDto>builder()
                    .code(-2)
                    .message(String.format("Basket while updating error! Message :: %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<ResponseBasketDto> deleteEntity(Integer entityId) {
        try {
            return this.basketRepository.findByBasketIdAndDeletedAtIsNull(entityId)
                    .map(basket -> {
                        basket.setDeletedAt(LocalDateTime.now());
                        return ResponseDto.<ResponseBasketDto>builder()
                                .success(true)
                                .message("OK")
                                .content(this.basketMapper.toDto(
                                                this.basketRepository.save(basket)
                                        )
                                )
                                .build();
                    })
                    .orElse(ResponseDto.<ResponseBasketDto>builder()
                            .message(String.format("Basket with %d id is not found!", entityId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<ResponseBasketDto>builder()
                    .code(-2)
                    .message(String.format("Basket while deleting error! Message :: %s", e.getMessage()))
                    .build();
        }
    }



}
