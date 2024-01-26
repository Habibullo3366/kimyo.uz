package com.company.kimyouz.unit.basket;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestBasketDto;
import com.company.kimyouz.dto.response.ResponseBasketDto;
import com.company.kimyouz.entity.Basket;
import com.company.kimyouz.repository.BasketRepository;
import com.company.kimyouz.repository.ProductRepository;
import com.company.kimyouz.service.BasketService;
import com.company.kimyouz.service.ProductService;
import com.company.kimyouz.service.mapper.BasketMapper;
import com.company.kimyouz.service.mapper.ProductMapper;
import com.company.kimyouz.validation.BasketValidation;
import com.company.kimyouz.validation.ProductValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class TestBasketService {
    private BasketMapper basketMapper;

    private BasketRepository basketRepository;

    private BasketService basketService;

    private BasketValidation basketValidation;



    @BeforeEach
    public void initMethod(){
        basketService = Mockito.mock(BasketService.class);

        basketMapper = Mockito.mock(BasketMapper.class);
        basketValidation = Mockito.mock(BasketValidation.class);
        basketRepository = Mockito.mock(BasketRepository.class);

        this.basketService = new BasketService(basketMapper ,  basketValidation , basketRepository );
    }




    @Test
    public void TestCreateBasketPositive(){
        Mockito.when(this.basketMapper.toDto(Mockito.any())).thenReturn(
                ResponseBasketDto.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketMapper.toEntity(Mockito.any())).thenReturn(
                Basket.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketRepository.save(Mockito.any())).thenReturn(
                Basket.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketValidation.basketValid(Mockito.any())).thenReturn(new ArrayList<>());


        ResponseDto<ResponseBasketDto> dto = this.basketService.createEntity(Mockito.any());

        Assertions.assertEquals(dto.getCode() , 0 , "Unknown get code value returned");
        Assertions.assertTrue(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNotNull(dto.getContent() , "Unknown content value returned");


        Mockito.verify(this.basketMapper , Mockito.times(1)).toDto(any());
    }

    @Test
    public void TestCreateBasketValidation(){

//        Mockito.when(this.basketMapper.toDto(Mockito.any())).thenReturn(
//                ResponseBasketDto.builder()
//                        .basketId(1)
//                        .totalPrice(12000.0)
//                        .build()
//        );

        Mockito.when(this.basketMapper.toEntity(Mockito.any())).thenReturn(
                Basket.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketRepository.save(Mockito.any())).thenReturn(
                Basket.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketValidation.basketValid(Mockito.any())).thenReturn(List.of(new ErrorDto("totalPrice", "TotalPrice cannot be null or empty.")));


        ResponseDto<ResponseBasketDto> dto = this.basketService.createEntity(Mockito.any());

        Assertions.assertEquals(dto.getCode() , -3 , "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNotNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent() , "Unknown content value returned");


    }

    @Test
    public void TestCreateBasketException(){

        Mockito.when(this.basketMapper.toDto(Mockito.any())).thenReturn(
                ResponseBasketDto.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketMapper.toEntity(Mockito.any())).thenReturn(
                Basket.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketRepository.save(Mockito.any())).thenThrow(RuntimeException.class);

        Mockito.when(this.basketValidation.basketValid(Mockito.any())).thenReturn(new ArrayList<>());


        ResponseDto<ResponseBasketDto> dto = this.basketService.createEntity(Mockito.any());

        Assertions.assertEquals(dto.getCode() , -2 , "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent() , "Unknown content value returned");





    }



    @Test
    public void TestGetBasketPositive(){
        Mockito.when(this.basketMapper.toDto(Mockito.any())).thenReturn(
                ResponseBasketDto.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.of(
                        Basket.builder()
                                .basketId(1)
                                .totalPrice(12000.0)
                                .build()
                )
        );

        ResponseDto<ResponseBasketDto> dto = this.basketService.getEntity(Mockito.any());

        Assertions.assertEquals(dto.getCode() , 0 , "Unknown get code value returned");
        Assertions.assertTrue(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNotNull(dto.getContent() , "Unknown content value returned");
    }

    @Test
    public void TestGetBasketNegative(){
        Mockito.when(this.basketMapper.toDto(Mockito.any())).thenReturn(
                ResponseBasketDto.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.empty()
        );

        ResponseDto<ResponseBasketDto> dto = this.basketService.getEntity(Mockito.any());

        Assertions.assertEquals(dto.getCode() , -1 , "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent() , "Unknown content value returned");
    }

    @Test
    public void TestDeleteBasketPositive(){
        Mockito.when(this.basketMapper.toDto(Mockito.any())).thenReturn(
                ResponseBasketDto.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.of(
                        Basket.builder()
                                .basketId(1)
                                .totalPrice(12000.0)
                                .build()
                )
        );

        ResponseDto<ResponseBasketDto> dto = this.basketService.deleteEntity(Mockito.any());

        Assertions.assertEquals(dto.getCode() , 0 , "Unknown get code value returned");
        Assertions.assertTrue(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNotNull(dto.getContent() , "Unknown content value returned");
    }

    @Test
    public void TestDeleteBasketNegative(){
        Mockito.when(this.basketMapper.toDto(Mockito.any())).thenReturn(
                ResponseBasketDto.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.empty()
        );

        ResponseDto<ResponseBasketDto> dto = this.basketService.deleteEntity(Mockito.any());

        Assertions.assertEquals(dto.getCode() , -1 , "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent() , "Unknown content value returned");
    }

    @Test
    public void TestUpdateBasketPositive(){
        Mockito.when(this.basketMapper.toDto(Mockito.any())).thenReturn(
                ResponseBasketDto.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketMapper.toEntity(Mockito.any())).thenReturn(
                Basket.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketRepository.save(Mockito.any())).thenReturn(
                Basket.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );


        Mockito.when(this.basketMapper.updateBasket(any() , any())).thenReturn(
                Basket.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.of(
                        Basket.builder()
                                .basketId(1)
                                .totalPrice(12000.0)
                                .build()
                )
        );


        ResponseDto<ResponseBasketDto> dto = this.basketService.updateEntity(1 ,
                any());

        Assertions.assertEquals(dto.getCode() , 0 , "Unknown get code value returned");
        Assertions.assertTrue(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNotNull(dto.getContent() , "Unknown content value returned");
    }

    @Test
    public void TestUpdateBasketNegative(){
        Mockito.when(this.basketMapper.toDto(Mockito.any())).thenReturn(
                ResponseBasketDto.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketMapper.toEntity(Mockito.any())).thenReturn(
                Basket.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketRepository.save(Mockito.any())).thenReturn(
                Basket.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );


        Mockito.when(this.basketMapper.updateBasket(any() , any())).thenReturn(
                Basket.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.empty()
        );


        ResponseDto<ResponseBasketDto> dto = this.basketService.updateEntity(1 ,
                any());

        Assertions.assertEquals(dto.getCode() , -1 , "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent() , "Unknown content value returned");
    }

    @Test
    public void TestUpdateBasketException(){


        Mockito.when(this.basketRepository.findByBasketIdAndDeletedAtIsNull(any())).thenReturn(
                Optional.of(
                        Basket.builder()
                                .basketId(1)
                                .totalPrice(12000.0)
                                .build()
                )
        );

        Mockito.when(this.basketMapper.toDto(Mockito.any())).thenReturn(
                ResponseBasketDto.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketMapper.toEntity(Mockito.any())).thenReturn(
                Basket.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );

        Mockito.when(this.basketRepository.save(Mockito.any())).thenThrow(RuntimeException.class);


        Mockito.when(this.basketMapper.updateBasket(any() , any())).thenReturn(
                Basket.builder()
                        .basketId(1)
                        .totalPrice(12000.0)
                        .build()
        );




        ResponseDto<ResponseBasketDto> dto = this.basketService.updateEntity(1 ,
                any());

        Assertions.assertEquals(dto.getCode() , -2 , "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent() , "Unknown content value returned");
    }
}
