package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestCardDto;
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.entity.Card;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-28T12:23:25+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class CardMapperImpl extends CardMapper {

    @Override
    public Card toEntity(RequestCardDto dto) {
        if ( dto == null ) {
            return null;
        }

        Card.CardBuilder card = Card.builder();

        card.cardName( dto.getCardName() );
        card.cardFullName( dto.getCardFullName() );
        card.userId( dto.getUserId() );

        card.cardCode( "0000" );

        return card.build();
    }

    @Override
    public ResponseCardDto toDto(Card card) {
        if ( card == null ) {
            return null;
        }

        ResponseCardDto.ResponseCardDtoBuilder responseCardDto = ResponseCardDto.builder();

        responseCardDto.cardId( card.getCardId() );
        responseCardDto.cardFullName( card.getCardFullName() );
        responseCardDto.cardName( card.getCardName() );
        responseCardDto.userId( card.getUserId() );
        responseCardDto.cardCode( card.getCardCode() );
        responseCardDto.createdAt( card.getCreatedAt() );
        responseCardDto.updatedAt( card.getUpdatedAt() );
        responseCardDto.deletedAt( card.getDeletedAt() );

        return responseCardDto.build();
    }

    @Override
    public Card updateCard(RequestCardDto dto, Card card) {
        if ( dto == null ) {
            return card;
        }

        if ( dto.getCardName() != null ) {
            card.setCardName( dto.getCardName() );
        }
        if ( dto.getCardFullName() != null ) {
            card.setCardFullName( dto.getCardFullName() );
        }
        if ( dto.getUserId() != null ) {
            card.setUserId( dto.getUserId() );
        }
        if ( dto.getCardCode() != null ) {
            card.setCardCode( dto.getCardCode() );
        }

        return card;
    }
}
