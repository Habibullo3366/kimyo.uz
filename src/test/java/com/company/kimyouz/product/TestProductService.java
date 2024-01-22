package com.company.kimyouz.product;

import com.company.kimyouz.controller.ProductController;
import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestProductDto;
import com.company.kimyouz.dto.response.ResponseProductDto;
import com.company.kimyouz.entity.Product;
import com.company.kimyouz.repository.ProductRepository;
import com.company.kimyouz.repository.impl.ProductRepositoryImpl;
import com.company.kimyouz.service.ProductService;
import com.company.kimyouz.service.mapper.ProductMapper;
import com.company.kimyouz.validation.ProductValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

public class TestProductService {
    private ProductService productService;

    private ProductValidation productValidation;

    private ProductMapper productMapper;

    private ProductRepository productRepository;

    private ProductRepositoryImpl productRepositoryImpl;

    @BeforeEach
    public void initMethod(){
        productService = Mockito.mock(ProductService.class);
        productMapper = Mockito.mock(ProductMapper.class);
        productValidation = Mockito.mock(ProductValidation.class);
        productRepository = Mockito.mock(ProductRepository.class);

        this.productService = new ProductService(productMapper , productRepository , productRepositoryImpl ,  productValidation);
    }


    @Test
    public void TestCreateProductPositive(){
        Mockito.when(this.productMapper.toDto(any())).thenReturn(
                ResponseProductDto.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build());

        Mockito.when(this.productMapper.toEntity(any()))
                        .thenReturn(Product.builder()
                                .prodId(1)
                                .categoryId(1)
                                .prodAmount(10)
                                .prodColor("Red")
                                .prodName("Ferrari")
                                .prodPrice(12000.0)
                                .prodType("Car")
                                .build());

        Mockito.when(this.productRepository.save(any())).thenReturn(
                Product.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build());

        Mockito.when(this.productValidation.productValid(any()))
                .thenReturn(new ArrayList<>());


        ResponseDto<ResponseProductDto> dto = this.productService.createEntity(any());

        Assertions.assertEquals(dto.getCode() , 0 , "Unknown get code value returned");
        Assertions.assertTrue(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNotNull(dto.getContent() , "Unknown content value returned");


        Mockito.verify(this.productMapper , Mockito.times(1)).toDto(any());



    }

    @Test
    public void TestCreateProductException(){

        Mockito.when(this.productValidation.productValid(any())).thenReturn(new ArrayList<>());

        Mockito.when(this.productRepository.save(any())).thenThrow(RuntimeException.class);

        ResponseDto<ResponseProductDto> dto = this.productService.createEntity(any());

        Assertions.assertEquals(dto.getCode() , -2 , "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent() , "Unknown content value returned");
    }

    @Test
    public void TestCreateProductValidation(){

        Mockito.when(this.productValidation.productValid(any()))
                .thenReturn(List.of(
                        new ErrorDto("prodAmount","ProdAmount cannot be null or empty")
                ));

        ResponseDto<ResponseProductDto> dto = this.productService.createEntity(any());
        Assertions.assertEquals(dto.getCode() , -3 , "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNotNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent() , "Unknown content value returned");
    }




    @Test
    public void TestGetProductPositive(){
        Mockito.when(this.productMapper.toDto(any())).thenReturn(
                ResponseProductDto.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build());

        Mockito.when(this.productMapper.toEntity(any()))
                .thenReturn(Product.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build());

        Mockito.when(this.productRepository.save(any())).thenReturn(
                Product.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build());


        Mockito.when(this.productRepository.findByProductId(any())).thenReturn(Optional.of(
                Product.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build()));


        ResponseDto<ResponseProductDto> dto = this.productService.getEntity(any());
        Assertions.assertEquals(dto.getCode() , 0 , "Unknown get code value returned");
        Assertions.assertTrue(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNotNull(dto.getContent() , "Unknown content value returned");


    }

    @Test
    public void TestGetProductNegative(){

        Mockito.when(this.productRepository.findByProductId(any())).thenReturn(Optional.empty());


        ResponseDto<ResponseProductDto> dto = this.productService.getEntity(any());
        Assertions.assertEquals(dto.getCode() , -1 , "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent() , "Unknown content value returned");

    }







    @Test
    public void TestUpdateProductPositive() {
        // Mock the repository to return a product when findByProdIdAndDeletedAtIsNull is called
        Mockito.when(this.productRepository.findByProdIdAndDeletedAtIsNull(Mockito.any())).thenReturn(Optional.of(
                Product.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build()));

        // Mock the mapper to return a DTO when toDto is called
        Mockito.when(this.productMapper.toDto(Mockito.any())).thenReturn(
                ResponseProductDto.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build());

        // Mock the mapper to return a product when updateProduct is called
        Mockito.when(this.productMapper.updateProduct(Mockito.any(), Mockito.any())).thenReturn(
                Product.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build());

        // Mock the repository to return a product when save is called
        Mockito.when(this.productRepository.save(Mockito.any())).thenReturn(
                Product.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build());

        // Call the service method with specific arguments
        ResponseDto<ResponseProductDto> dto = this.productService.updateEntity(1 ,
                RequestProductDto.builder()
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build()

                );

        // Assertions
        Assertions.assertEquals(0, dto.getCode(), "Unknown get code value returned");
        Assertions.assertTrue(dto.isSuccess(), "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNotNull(dto.getContent(), "Unknown content value returned");
    }

    @Test
    public void TestUpdateProductNegative(){
        Mockito.when(this.productRepository.findByProdIdAndDeletedAtIsNull(any())).thenReturn(Optional.empty());


        ResponseDto<ResponseProductDto> dto = this.productService.updateEntity(1 , any());
        Assertions.assertEquals(dto.getCode() , -1, "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertEquals(dto.getMessage() , "Product with 1: id is not found!");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent() , "Unknown content value returned");

    }

    @Test
    public void TestUpdateProductException(){

        Mockito.when(this.productRepository.findByProdIdAndDeletedAtIsNull(any())).thenReturn(Optional.of(
                Product.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .basketId(1)
                        .categoryId(1)
                        .build()));


        Mockito.when(this.productRepository.save(any())).thenThrow(RuntimeException.class);


        Assertions.assertThrows(RuntimeException.class, () -> {
            this.productService.updateEntity(any() , any());
        }, "Expected RuntimeException to be thrown during updateEntity");


        }




    @Test
    public void TestDeleteProductPositive(){

        Mockito.when(this.productMapper.toDto(any())).thenReturn(
                ResponseProductDto.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build());

//        Mockito.when(this.productMapper.toEntity(any()))
//                .thenReturn(Product.builder()
//                        .prodId(1)
//                        .categoryId(1)
//                        .prodAmount(10)
//                        .prodColor("Red")
//                        .prodName("Ferrari")
//                        .prodPrice(12000.0)
//                        .prodType("Car")
//                        .build());

        Mockito.when(this.productRepository.save(any())).thenReturn(
                Product.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build());


        Mockito.when(this.productRepository.findByProdIdAndDeletedAtIsNull(any())).thenReturn(Optional.of(
                Product.builder()
                        .prodId(1)
                        .categoryId(1)
                        .prodAmount(10)
                        .prodColor("Red")
                        .prodName("Ferrari")
                        .prodPrice(12000.0)
                        .prodType("Car")
                        .build()));


        ResponseDto<ResponseProductDto> dto = this.productService.deleteEntity(any());
        Assertions.assertEquals(dto.getCode() , 0, "Unknown get code value returned");
        Assertions.assertTrue(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNotNull(dto.getContent() , "Unknown content value returned");

    }

    @Test
    public void TestDeleteProductNegative(){

        Mockito.when(this.productRepository.findByProdIdAndDeletedAtIsNull(any())).thenReturn(Optional.empty());


        ResponseDto<ResponseProductDto> dto = this.productService.deleteEntity(any());
        Assertions.assertEquals(dto.getCode() , -1 , "Unknown get code value returned");
        Assertions.assertFalse(dto.isSuccess() , "Unknown success value returned");
        Assertions.assertNull(dto.getErrorList() , "Unknown get error list value returned");
        Assertions.assertNull(dto.getContent() , "Unknown content value returned");

    }

}
