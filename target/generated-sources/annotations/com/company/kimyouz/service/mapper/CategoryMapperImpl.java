package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestCategoryDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.dto.response.ResponseProductDto;
import com.company.kimyouz.entity.Category;
import com.company.kimyouz.entity.Product;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-21T19:47:16+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl extends CategoryMapper {

    @Override
    public Category toEntity(RequestCategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.categoryName( dto.getCategoryName() );

        return category.build();
    }

    @Override
    public ResponseCategoryDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        ResponseCategoryDto.ResponseCategoryDtoBuilder responseCategoryDto = ResponseCategoryDto.builder();

        responseCategoryDto.categoryId( category.getCategoryId() );
        responseCategoryDto.categoryName( category.getCategoryName() );
        responseCategoryDto.products( productSetToResponseProductDtoSet( category.getProducts() ) );
        responseCategoryDto.createdAt( category.getCreatedAt() );
        responseCategoryDto.updatedAt( category.getUpdatedAt() );
        responseCategoryDto.deletedAt( category.getDeletedAt() );

        return responseCategoryDto.build();
    }

    @Override
    public ResponseCategoryDto toDtoWithProducts(Category category) {
        if ( category == null ) {
            return null;
        }

        ResponseCategoryDto.ResponseCategoryDtoBuilder responseCategoryDto = ResponseCategoryDto.builder();

        responseCategoryDto.categoryId( category.getCategoryId() );
        responseCategoryDto.categoryName( category.getCategoryName() );
        responseCategoryDto.products( productSetToResponseProductDtoSet( category.getProducts() ) );
        responseCategoryDto.createdAt( category.getCreatedAt() );
        responseCategoryDto.updatedAt( category.getUpdatedAt() );
        responseCategoryDto.deletedAt( category.getDeletedAt() );

        return responseCategoryDto.build();
    }

    @Override
    public Category updateCategory(RequestCategoryDto dto, Category category) {
        if ( dto == null ) {
            return category;
        }

        if ( dto.getCategoryName() != null ) {
            category.setCategoryName( dto.getCategoryName() );
        }

        return category;
    }

    protected ResponseProductDto productToResponseProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ResponseProductDto.ResponseProductDtoBuilder responseProductDto = ResponseProductDto.builder();

        responseProductDto.prodId( product.getProdId() );
        responseProductDto.prodName( product.getProdName() );
        responseProductDto.description( product.getDescription() );
        responseProductDto.stock( product.getStock() );
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

    protected Set<ResponseProductDto> productSetToResponseProductDtoSet(Set<Product> set) {
        if ( set == null ) {
            return null;
        }

        Set<ResponseProductDto> set1 = new LinkedHashSet<ResponseProductDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Product product : set ) {
            set1.add( productToResponseProductDto( product ) );
        }

        return set1;
    }
}
