package com.company.kimyouz.unit.product;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.response.ResponseProductDto;
import com.company.kimyouz.entity.Product;
import com.company.kimyouz.repository.ProductRepository;
import com.company.kimyouz.service.ProductService;
import com.company.kimyouz.service.mapper.ProductMapper;
import com.company.kimyouz.service.validation.ProductValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestProductService {

    private ProductMapper productMapper;
    private ProductRepository productRepository;
    private ProductValidation productValidation;
    private ProductService productService;

    @BeforeEach
    public void initMethod() {

        productMapper = mock(ProductMapper.class);
        productRepository = mock(ProductRepository.class);
        productValidation = mock(ProductValidation.class);
        productService = new ProductService(productMapper, productRepository, productValidation);

    }

    @Test
    public void testCreateProductPositive() {
        when(productValidation.productValid(any()))
                .thenReturn(new ArrayList<>());

        when(productMapper.toDto(any()))
                .thenReturn(ResponseProductDto.builder()
                        .prodId(1)
                        .prodName("name")
                        .prodColor("Blue")
                        .prodPrice(25.0)
                        .prodAmount(23)
                        .prodType("type")
                        .build());


        Product product = Product.builder()
                .prodId(1)
                .prodName("name")
                .prodColor("Blue")
                .prodPrice(25.0)
                .prodAmount(23)
                .prodType("type")
                .build();

        when(productMapper.toEntity(any()))
                .thenReturn(product);

        when(productRepository.save(any()))
                .thenReturn(product);

        ResponseDto<ResponseProductDto> response = this.productService.createEntity(any());
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");

        verify(this.productMapper, times(1)).toDto(any());
        verify(this.productMapper, times(1)).toEntity(any());
        verify(this.productRepository, times(1)).save(any());
        verify(this.productValidation, times(1)).productValid(any());


    }

    @Test
    public void testCreateProductException() {

        when(this.productValidation.productValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.productRepository.save(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseProductDto> response = this.productService.createEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -2, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");

    }

    @Test
    public void testCreateProductValidation() {
        when(this.productValidation.productValid(any()))
                .thenReturn(List.of(
                                new ErrorDto("categoryId", String.format("Category with this %d is not found", 1))

                        )
                );

        ResponseDto<ResponseProductDto> response = this.productService.createEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testGetProductPositive() {
        ResponseProductDto responseProductDto = ResponseProductDto.builder()
                .prodId(1)
                .prodName("name")
                .prodColor("Blue")
                .prodPrice(25.0)
                .prodAmount(23)
                .prodType("type")
                .build();

        when(this.productMapper.toDto(any()))
                .thenReturn(responseProductDto);

        when(this.productRepository.findByProdIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                                Product.builder()
                                        .prodId(1)
                                        .prodName("name")
                                        .prodColor("Blue")
                                        .prodPrice(25.0)
                                        .prodAmount(23)
                                        .prodType("type")
                                        .build()

                        )
                );

        ResponseDto<ResponseProductDto> response = this.productService.getEntity(any());
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testGetProductNegative() {
        when(this.productRepository.findByProdIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseProductDto> response = this.productService.getEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testUpdateProductPositive() {

        when(this.productRepository.findByProdIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        Product.builder()
                                .prodId(1)
                                .prodName("name")
                                .prodColor("Blue")
                                .prodPrice(25.0)
                                .prodAmount(23)
                                .prodType("type")
                                .build()));


        when(this.productMapper.toDto(any()))
                .thenReturn(ResponseProductDto.builder()
                        .prodId(1)
                        .prodName("name")
                        .prodColor("Blue")
                        .prodPrice(25.0)
                        .prodAmount(23)
                        .prodType("type")
                        .build());

        when(this.productMapper.updateProduct(any(), any()))
                .thenReturn(Product.builder()
                        .prodId(1)
                        .prodName("name")
                        .prodColor("Blue")
                        .prodPrice(25.0)
                        .prodAmount(23)
                        .prodType("type")
                        .build());

        ResponseDto<ResponseProductDto> response = this.productService.updateEntity(1, any());
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testUpdateProductNegative() {
        when(this.productRepository.findByProdIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseProductDto> response = this.productService.updateEntity(1, any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testUpdateProductException() {

        when(this.productValidation.productValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.productRepository.save(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseProductDto> response = this.productService.updateEntity(1, any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testUpdateProductValidation() {

        when(this.productValidation.productValid(any()))
                .thenReturn(List.of(
                                new ErrorDto("categoryId", String.format("Category with this %d is not found", 1))

                        )
                );

        ResponseDto<ResponseProductDto> response = this.productService.updateEntity(1, any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testDeleteProductPositive() {

        when(this.productRepository.findByProdIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        Product.builder()
                                .prodId(1)
                                .prodName("name")
                                .prodColor("Blue")
                                .prodPrice(25.0)
                                .prodAmount(23)
                                .prodType("type")
                                .build()));


        when(this.productMapper.toDto(any()))
                .thenReturn(ResponseProductDto.builder()
                        .prodId(1)
                        .prodName("name")
                        .prodColor("Blue")
                        .prodPrice(25.0)
                        .prodAmount(23)
                        .prodType("type")
                        .build());

        when(this.productMapper.toEntity(any()))
                .thenReturn(Product.builder()
                        .prodId(1)
                        .prodName("name")
                        .prodColor("Blue")
                        .prodPrice(25.0)
                        .prodAmount(23)
                        .prodType("type")
                        .build());

        ResponseDto<ResponseProductDto> response = this.productService.deleteEntity(any());
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");


    }

    @Test
    public void testDeleteProductNegative() {

        when(this.productRepository.findByProdIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseProductDto> response = this.productService.deleteEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testDeleteProductException() {

        when(this.productValidation.productValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.productRepository.save(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseProductDto> response = this.productService.deleteEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");



    }
}
