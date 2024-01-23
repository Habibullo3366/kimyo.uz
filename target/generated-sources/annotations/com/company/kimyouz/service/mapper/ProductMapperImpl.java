package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestProductDto;
import com.company.kimyouz.dto.response.ResponseProductDto;
import com.company.kimyouz.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-22T13:12:08+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl extends ProductMapper {

    @Override
    public Product toEntity(RequestProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.prodName( dto.getProdName() );
        product.prodColor( dto.getProdColor() );
        product.prodPrice( dto.getProdPrice() );
        product.prodAmount( dto.getProdAmount() );
        product.prodType( dto.getProdType() );

        return product.build();
    }

    @Override
    public ResponseProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ResponseProductDto.ResponseProductDtoBuilder responseProductDto = ResponseProductDto.builder();

        responseProductDto.prodId( product.getProdId() );
        responseProductDto.prodName( product.getProdName() );
        responseProductDto.prodColor( product.getProdColor() );
        responseProductDto.prodPrice( product.getProdPrice() );
        responseProductDto.prodAmount( product.getProdAmount() );
        responseProductDto.prodType( product.getProdType() );
        responseProductDto.categoryId( product.getCategoryId() );
        responseProductDto.createdAt( product.getCreatedAt() );
        responseProductDto.updatedAt( product.getUpdatedAt() );
        responseProductDto.deletedAt( product.getDeletedAt() );

        return responseProductDto.build();
    }

    @Override
    public ResponseProductDto toDtoWithCategories(Product product) {
        if ( product == null ) {
            return null;
        }

        ResponseProductDto.ResponseProductDtoBuilder responseProductDto = ResponseProductDto.builder();

        responseProductDto.prodId( product.getProdId() );
        responseProductDto.prodName( product.getProdName() );
        responseProductDto.prodColor( product.getProdColor() );
        responseProductDto.prodPrice( product.getProdPrice() );
        responseProductDto.prodAmount( product.getProdAmount() );
        responseProductDto.prodType( product.getProdType() );
        responseProductDto.categoryId( product.getCategoryId() );
        responseProductDto.createdAt( product.getCreatedAt() );
        responseProductDto.updatedAt( product.getUpdatedAt() );
        responseProductDto.deletedAt( product.getDeletedAt() );

        return responseProductDto.build();
    }

    @Override
    public Product updateProduct(RequestProductDto dto, Product product) {
        if ( dto == null ) {
            return product;
        }

        if ( dto.getProdName() != null ) {
            product.setProdName( dto.getProdName() );
        }
        if ( dto.getProdColor() != null ) {
            product.setProdColor( dto.getProdColor() );
        }
        if ( dto.getProdPrice() != null ) {
            product.setProdPrice( dto.getProdPrice() );
        }
        if ( dto.getProdAmount() != null ) {
            product.setProdAmount( dto.getProdAmount() );
        }
        if ( dto.getProdType() != null ) {
            product.setProdType( dto.getProdType() );
        }

        return product;
    }
}
