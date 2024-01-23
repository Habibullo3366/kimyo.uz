package com.company.kimyouz.card;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.entity.Card;
import com.company.kimyouz.repository.CardRepository;
import com.company.kimyouz.service.CardService;
import com.company.kimyouz.service.mapper.CardMapper;
import com.company.kimyouz.service.validation.CardValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCardService {

    private CardService cardService;
    private CardValidation cardValidation;
    private CardRepository cardRepository;
    private CardMapper cardMapper;

    @BeforeEach
    public void initObject() {
        cardMapper = mock(CardMapper.class);
        cardValidation = mock(CardValidation.class);
        cardRepository = mock(CardRepository.class);
        this.cardService = new CardService(cardMapper, cardValidation, cardRepository);
    }

    @Test
    public void testCreateCardPositive() {
        when(cardValidation.cardValid(any()))
                .thenReturn(new ArrayList<>());

        when(cardMapper.toDto(any()))
                .thenReturn(ResponseCardDto.builder()
                        .cardId(1)
                        .cardName("cardName")
                        .cardCode("cardCode")
                        .cardFullName("cardFullName")
                        .build());

        when(cardMapper.toEntity(any()))
                .thenReturn(Card.builder()
                        .cardId(1)
                        .cardName("cardName")
                        .cardCode("cardCode")
                        .cardFullName("cardFullName")
                        .build());

        when(cardRepository.save(any())).thenReturn(Card.builder()
                .cardId(1)
                .cardName("cardName")
                .cardCode("cardCode")
                .cardFullName("cardFullName")
                .build());

        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());
        Assertions.assertEquals(response.getCode(),0, "Unexpected response code");
        Assertions.assertNull(response.getErrorList(),"Error list is not null");
        Assertions.assertTrue(response.isSuccess(), "unsuccessful");
        Assertions.assertNotNull(response.getContent(), "Unexpected content");
    }

    @Test
    public void testCreateCardNegative() {

    }

    @Test
    public void testCreateCardException() {

    }

    @Test
    public void testCreateCardValidation() {

    }

    @Test
    public void testGetCardPositive() {

    }

    @Test
    public void testGetCardNegative() {

    }

    @Test
    public void testUpdateCardPositive() {

    }

    @Test
    public void testUpdateCardNegative() {

    }

    @Test
    public void testUpdateCardException() {

    }

    @Test
    public void testUpdateCardValidation() {

    }

    @Test
    public void testDeleteCardPositive() {

    }

    @Test
    public void testDeleteCardNegative() {

    }

    @Test
    public void testDeleteCardException() {

    }

}
