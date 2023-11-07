package com.company.kimyouz.service.mapper;

import com.company.kimyouz.dto.request.RequestCardDto;
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.entity.Card;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class CardMapper {

    @Mapping(target = "cardId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "cardName", source = "cardName")
    @Mapping(target = "cardCode", expression = "java(\"0000\")")
    public abstract Card toEntity(RequestCardDto dto);

    @Mapping(target = "users", ignore = true)
    public abstract ResponseCardDto toDto(Card card);

    @Mapping(target = "cardId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = Card.class)
    public abstract Card updateCard(RequestCardDto dto, @MappingTarget Card card);




}
