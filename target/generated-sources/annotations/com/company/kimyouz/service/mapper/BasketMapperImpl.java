package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestBasketDto;
import com.company.kimyouz.dto.response.ResponseBasketDto;
import com.company.kimyouz.entity.Basket;
import com.company.kimyouz.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-20T17:05:13+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class BasketMapperImpl extends BasketMapper {

    @Override
    public Basket toEntity(RequestBasketDto dto) {
        if ( dto == null ) {
            return null;
        }

        Basket.BasketBuilder basket = Basket.builder();

        basket.totalPrice( dto.getTotalPrice() );

        return basket.build();
    }

    @Override
    public ResponseBasketDto toDto(Basket basket) {
        if ( basket == null ) {
            return null;
        }

        ResponseBasketDto.ResponseBasketDtoBuilder responseBasketDto = ResponseBasketDto.builder();

        responseBasketDto.basketId( basket.getBasketId() );
        responseBasketDto.totalPrice( basket.getTotalPrice() );
        List<Product> list = basket.getProducts();
        if ( list != null ) {
            responseBasketDto.products( new ArrayList<Product>( list ) );
        }
        responseBasketDto.createdAt( basket.getCreatedAt() );
        responseBasketDto.updatedAt( basket.getUpdatedAt() );
        responseBasketDto.deletedAt( basket.getDeletedAt() );

        return responseBasketDto.build();
    }

    @Override
    public Basket updateBasket(RequestBasketDto dto, Basket basket) {
        if ( dto == null ) {
            return basket;
        }

        if ( dto.getTotalPrice() != null ) {
            basket.setTotalPrice( dto.getTotalPrice() );
        }

        return basket;
    }
}