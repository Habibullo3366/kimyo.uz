package com.company.kimyouz.mapper;

import com.company.kimyo.uz.dto.CardDto;
import com.company.kimyo.uz.model.Card;
import org.mapstruct.*;

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Mapper(componentModel = "spring")
public abstract class CardMapper {

    //todo: MapStruct
    @Mapping(target = "cardId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "cardName", source = "name")
    @Mapping(target = "cardCode", expression = "java(\"0000\")")
    public abstract Card toEntity(CardDto dto);

    @Mapping(target = "users", ignore = true)
    public abstract CardDto toDto(Card card);

    @Mapping(target = "cardId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = Card.class)
    public abstract Card updateCard(CardDto dto, @MappingTarget Card card);

    //public abstract CardDto toDtoWithUser(Card card);

    //public abstract Set<CardDto> toEntityFromDto(Set<Card> cardList);


}
