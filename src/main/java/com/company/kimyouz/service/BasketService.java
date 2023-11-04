package com.company.kimyouz.service;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestBasketDto;
import com.company.kimyouz.dto.response.ResponseBasketDto;
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
        List<ErrorDto> errors = this.basketValidation.basketValid(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ResponseBasketDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errors)
                    .build();
        }

        try {
            Basket basket = this.basketMapper.toEntity(dto);
            basket.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseBasketDto>builder()
                    .success(true)
                    .message("OK")
                    .data(
                            this.basketMapper.toDto(
                                    this.basketRepository.save(basket)
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseBasketDto>builder()
                    .code(-2)
                    .message(String.format("Basket while saving error message :: %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<ResponseBasketDto> getEntity(Integer entityId) {
        Optional<Basket> optionalUser = this.basketRepository
                .findByBasketIdAndDeletedAtIsNull(entityId);
        if (optionalUser.isEmpty()) {
            return ResponseDto.<ResponseBasketDto>builder()
                    .code(-1)
                    .message(String.format("User with %d:id is not found!", entityId))
                    .build();
        }
        return ResponseDto.<ResponseBasketDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.basketMapper.toDto(optionalUser.get())
                )
                .build();
    }


    public ResponseDto<ResponseBasketDto> updateEntity(Integer entityId, RequestBasketDto dto) {
        try {
            Optional<Basket> optional = this.basketRepository.findByBasketIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()) {
                return ResponseDto.<ResponseBasketDto>builder()
                        .code(-1)
                        .message(String.format("Basket with %d:id is not found!", entityId))
                        .build();
            }
            Basket basket = optional.get();
            basket.setUpdatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseBasketDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.basketMapper.toDto(
                                    this.basketRepository.save(
                                            basket
                                    )
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseBasketDto>builder()
                    .code(-1)
                    .message(String.format("Basket with %d:id is not found!", entityId))
                    .build();
        }
    }


    public ResponseDto<ResponseBasketDto> deleteEntity(Integer entityId) {
        Optional<Basket> optional = this.basketRepository.findByBasketIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseBasketDto>builder()
                    .code(-1)
                    .message(String.format("Basket with %d:id is not found!", entityId))
                    .build();
        }
        Basket basket = optional.get();
        basket.setDeletedAt(LocalDateTime.now());
        this.basketRepository.save(basket);
        return ResponseDto.<ResponseBasketDto>builder()
                .success(true)
                .message("OK")
                .data(this.basketMapper.toDto(basket))
                .build();
    }



}
