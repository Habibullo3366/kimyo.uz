package com.company.kimyouz.unit.basket;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.response.ResponseBasketDto;
import com.company.kimyouz.entity.Basket;
import com.company.kimyouz.repository.BasketRepository;
import com.company.kimyouz.service.BasketService;
import com.company.kimyouz.service.mapper.BasketMapper;
import com.company.kimyouz.service.validation.BasketValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestBasketService {

    private BasketMapper basketMapper;
    private BasketValidation basketValidation;
    private BasketRepository basketRepository;
    private BasketService basketService;


    @BeforeEach
    public void initMethod() {
        basketMapper = mock(BasketMapper.class);
        basketValidation = mock(BasketValidation.class);
        basketRepository = mock(BasketRepository.class);
        basketService = new BasketService(basketMapper, basketValidation, basketRepository);

    }

    @Test
    public void testCreateBasketPositive() {

        when(basketValidation.basketValid(any()))
                .thenReturn(new ArrayList<>());

        when(basketMapper.toDto(any()))
                .thenReturn(ResponseBasketDto.builder()
                        .basketId(1)
                        .totalPrice(30.0)
                        .build());

        Basket basket = Basket.builder()
                .basketId(1)
                .totalPrice(30.0)
                .build();

        when(basketMapper.toEntity(any()))
                .thenReturn(basket);

        when(basketRepository.save(any()))
                .thenReturn(basket);

        ResponseDto<ResponseBasketDto> response = this.basketService.createEntity(any());
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");

        verify(this.basketMapper, times(1)).toDto(any());
        verify(this.basketMapper, times(1)).toEntity(any());
        verify(this.basketRepository, times(1)).save(any());
        verify(this.basketValidation, times(1)).basketValid(any());


    }

    @Test
    public void testCreateBasketException() {

        when(this.basketValidation.basketValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.basketRepository.save(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseBasketDto> response = this.basketService.createEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -2, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");

    }

    @Test
    public void testCreateCategoryValidation() {

        when(this.basketValidation.basketValid(any()))
                .thenReturn(List.of(
                                new ErrorDto("basketId", String.format("Basket with this %d is not found", 1))

                        )
                );

        ResponseDto<ResponseBasketDto> response = this.basketService.createEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown error list value returned!");

    }


    @Test
    public void testGetBasketPositive() {

        ResponseBasketDto responseBasketDto = ResponseBasketDto.builder()
                .basketId(1)
                .totalPrice(30.0)
                .build();

        when(this.basketMapper.toDto(any()))
                .thenReturn(responseBasketDto);

        when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                                Basket.builder()
                                        .basketId(1)
                                        .totalPrice(30.0)
                                        .build()
                        )
                );

        ResponseDto<ResponseBasketDto> response = this.basketService.getEntity(any());
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");


    }

    @Test
    public void testGetBasketNegative() {

        when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseBasketDto> response = this.basketService.getEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testUpdateBasketPositive() {

        when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                                Basket.builder()
                                        .basketId(1)
                                        .totalPrice(30.0)
                                        .build()

                        )
                );

        when(this.basketMapper.toDto(any()))
                .thenReturn(ResponseBasketDto.builder()
                        .basketId(1)
                        .totalPrice(30.0)
                        .build());

        when(this.basketMapper.updateBasket(any(), any()))
                .thenReturn(Basket.builder()
                        .basketId(1)
                        .totalPrice(30.0)
                        .build());

        ResponseDto<ResponseBasketDto> response = this.basketService.updateEntity(1, any());
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testUpdateBasketNegative() {

        when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseBasketDto> response = this.basketService.updateEntity(1, any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testUpdateBasketException() {

        when(this.basketValidation.basketValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.basketRepository.save(any()))
                .thenThrow(RuntimeException.class);


        ResponseDto<ResponseBasketDto> response = this.basketService.updateEntity(1, any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testDeleteBasketPositive() {

        when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                                Basket.builder()
                                        .basketId(1)
                                        .totalPrice(30.0)
                                        .build()

                        )
                );

        when(this.basketMapper.toDto(any()))
                .thenReturn(ResponseBasketDto.builder()
                        .basketId(1)
                        .totalPrice(30.0)
                        .build());

        when(this.basketMapper.toEntity(any()))
                .thenReturn(Basket.builder()
                        .basketId(1)
                        .totalPrice(30.0)
                        .build());

        ResponseDto<ResponseBasketDto> response = this.basketService.deleteEntity(any());
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testDeleteBasketNegative() {

        when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseBasketDto> response = this.basketService.deleteEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testDeleteBasketException() {

        when(this.basketValidation.basketValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.basketRepository.save(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseBasketDto> response = this.basketService.deleteEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");


    }
}
