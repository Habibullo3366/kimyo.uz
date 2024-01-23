package com.company.kimyouz.service;


import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestProductDto;
import com.company.kimyouz.dto.response.ResponseProductDto;
import com.company.kimyouz.dto.response.ResponseUserDto;
import com.company.kimyouz.entity.Product;
import com.company.kimyouz.service.mapper.ProductMapper;
import com.company.kimyouz.repository.ProductRepository;
import com.company.kimyouz.repository.impl.ProductRepositoryImpl;
import com.company.kimyouz.service.validation.ProductValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductRepositoryImpl productRepositoryImpl;
    private final ProductValidation productValidation;


    public ResponseDto<ResponseProductDto> createEntity(RequestProductDto dto) {
        List<ErrorDto> errors = this.productValidation.productValid(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ResponseProductDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errors)
                    .build();
        }
        try {
            Product product = this.productMapper.toEntity(dto);
            product.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseProductDto>builder()
                    .success(true)
                    .message("Product successful created!")
                    .content(
                            this.productMapper.toDto(
                                    this.productRepository.save(
                                            product
                                    )
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseProductDto>builder()
                    .code(-2)
                    .message("Product while saving error message: " + e.getMessage())
                    .build();
        }
    }


    public ResponseDto<ResponseProductDto> getEntity(Integer prodId) {
        try {
            Optional<Product> optional = this.productRepository
                    .findByProductId(prodId);
            if (optional.isEmpty()) {
                return ResponseDto.<ResponseProductDto>builder()
                        .code(-1)
                        .message(String.format("Product with %d: id is not found!", prodId))
                        .build();
            }

            return ResponseDto.<ResponseProductDto>builder()
                    .success(true)
                    .message("OK")
                    .content(this.productMapper.toDto(optional.get()))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseProductDto>builder()
                    .code(-3)
                    .message(String.format("Error while getting product: %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<ResponseProductDto> updateEntity(Integer entityId, RequestProductDto dto) {
        try {
            Optional<Product> optional = this.productRepository.findByProdIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()) {
                return ResponseDto.<ResponseProductDto>builder()
                        .code(-1)
                        .message(String.format("Product with %d: id is not found!", entityId))
                        .build();
            }
            optional.get().setUpdatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseProductDto>builder()
                    .success(true)
                    .message("OK")
                    .content(this.productMapper.toDto(
                                    this.productRepository.save(this.productMapper.updateProduct(dto, optional.get()))
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseProductDto>builder()
                    .code(-2)
                    .message("Product while saving error message: " + e.getMessage())
                    .build();
        }


    }


    public ResponseDto<ResponseProductDto> deleteEntity(Integer prodId) {
        try {
            Optional<Product> optional = this.productRepository.findByProdIdAndDeletedAtIsNull(prodId);
            if (optional.isEmpty()) {
                return ResponseDto.<ResponseProductDto>builder()
                        .code(-1)
                        .message(String.format("Product with %d: id is not found!", prodId))
                        .build();
            }
            optional.get().setDeletedAt(LocalDateTime.now());
            return ResponseDto.<ResponseProductDto>builder()
                    .success(true)
                    .message("OK")
                    .content(this.productMapper.toDto(
                                    this.productRepository.save(optional.get())
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseProductDto>builder()
                    .code(-2)
                    .message("Error while deleting product: " + e.getMessage())
                    .build();
        }
    }

    public ResponseDto<List<ResponseProductDto>> getAllProduct(Integer prodId) {
        try {
            List<Product> products = this.productRepository.findAll();
            if (products.isEmpty()) {
                return ResponseDto.<List<ResponseProductDto>>builder()
                        .code(-1)
                        .message("Products are not found!")
                        .build();
            }

            return ResponseDto.<List<ResponseProductDto>>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            products.stream()
                                    .map(this.productMapper::toDto)
                                    .toList()
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<List<ResponseProductDto>>builder()
                    .code(-3)
                    .message(String.format("Error while getting all products: %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<Page<ResponseProductDto>> getAllProductByPage(Integer size, Integer page) {
        return getPageResponseDto(
                this.productRepository.findAllByPage(
                        PageRequest.of(page, size)
                )
        );
    }

    public ResponseDto<Page<ResponseProductDto>> getAllProductSortByColumn(Integer size, Integer page, String column) {
        return getPageResponseDto(this.productRepository
                .findAll(PageRequest.of(
                                page, size, Sort.by(column)
                        )
                )
        );
    }

    private ResponseDto<Page<ResponseProductDto>> getPageResponseDto(Page<Product> productPage) {
        if (productPage.isEmpty()) {
            return ResponseDto.<Page<ResponseProductDto>>builder()
                    .code(-1)
                    .message("Products are not found!")
                    .build();
        }
        return ResponseDto.<Page<ResponseProductDto>>builder()
                .success(true)
                .message("OK")
                .content(
                        productPage.map(this.productMapper::toDto)
                )
                .build();
    }

    public ResponseDto<Map<String, List<ResponseProductDto>>> getAllProdByCategory(Integer categoryId) {
        return ResponseDto.<Map<String, List<ResponseProductDto>>>builder()
                .success(true)
                .message("OK")
                .content(
                        this.productRepository.findByCategoryIdAndDeletedAtIsNull(categoryId)
                                .stream()
                                .map(this.productMapper::toDto)
                                .collect(Collectors.groupingBy(ResponseProductDto::getProdColor))
                )
                .build();
    }


    public ResponseDto<Page<ResponseProductDto>> productUniversalSearch(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }

        return ResponseDto.<Page<ResponseProductDto>>builder()
                .success(true)
                .message("OK")
                .content(
                        this.productRepository.searchAllProductWithMoreParams(
                                params.get("id") == null ? null : Integer.parseInt(params.get("id")),
                                params.get("name"), params.get("color"), params.get("type"),
                                params.get("price") == null ? null : Double.parseDouble(params.get("price")),
                                params.get("amount") == null ? null : Integer.parseInt(params.get("amount")),
                                PageRequest.of(page, size)
                        ).map(this.productMapper::toDto)
                )
                .build();
    }

    public ResponseDto<Page<ResponseProductDto>> productAdvancedSearch(Map<String, String> params) {
        Page<Product> productPage = this.productRepositoryImpl.searchAllProductWithMoreParams(params);

        if (productPage.isEmpty()) {
            return ResponseDto.<Page<ResponseProductDto>>builder()
                    .code(-1)
                    .message("Products are not found!!")
                    .build();
        }

        return ResponseDto.<Page<ResponseProductDto>>builder()
                .success(true)
                .message("OK")
                .content(
                        productPage.map(this.productMapper::toDto)
                )
                .build();
    }
}
