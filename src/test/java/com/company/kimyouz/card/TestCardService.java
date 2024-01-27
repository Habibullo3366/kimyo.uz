package com.company.kimyouz.card;

import com.company.kimyouz.dto.ErrorDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestCardService {


    private CardMapper cardMapper;
    private CardValidation cardValidation;
    private CardRepository cardRepository;
    private CardService cardService;

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
                .thenReturn(
                        ResponseCardDto.builder()
                                .cardId(1)
                                .cardName("Humo")
                                .cardCode("0000111122223333")
                                .cardFullName("Hasanboy Xalilov")
                                .build()
                );

        Card card = Card.builder()
                .cardId(1)
                .cardName("Humo")
                .cardCode("0000111122223333")
                .cardFullName("Hasanboy Xalilov")
                .build();

        when(cardMapper.toEntity(any()))
                .thenReturn(card);

        when(this.cardRepository.save(any()))
                .thenReturn(card);

        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown get error list value returned!");
        Assertions.assertTrue(response.isSuccess(), "Unknown success value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");


        verify(this.cardMapper, times(1)).toDto(any());
        verify(this.cardMapper, times(1)).toEntity(any());
        verify(this.cardRepository, times(1)).save(any());
        verify(this.cardValidation, times(1)).cardValid(any());

    }

    @Test
    public void testCreateCardException() {

        when(this.cardValidation.cardValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.cardRepository.save(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());
        Assertions.assertEquals(response.getCode(), -2, "Unknown get code value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown get error list value returned!");
        Assertions.assertFalse(response.isSuccess(), "Unknown success value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");

    }

    @Test
    public void testCreateCardValidation() {

        when(this.cardValidation.cardValid(any()))
                .thenReturn(List.of(
                                new ErrorDto("userId", String.format("User with %d id is not found!", 1))
                        )
                );


        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());

        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown get error list value returned!");
        Assertions.assertFalse(response.isSuccess(), "Unknown success value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");

        verify(this.cardValidation, times(1)).cardValid(any());


    }


    @Test
    public void testGetCardPositive() {

        ResponseCardDto responseCardDto = ResponseCardDto.builder()
                .cardId(1)
                .cardName("Humo")
                .cardCode("0000111122223333")
                .cardFullName("Hasanboy Xalilov")
                .build();

        when(this.cardMapper.toDto(any()))
                .thenReturn(responseCardDto);

        when(this.cardRepository.findByCardIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                                Card.builder()
                                        .cardId(1)
                                        .cardName("Humo")
                                        .cardCode("0000111122223333")
                                        .cardFullName("Hasanboy Xalilov")
                                        .build()
                        )
                );

        ResponseDto<ResponseCardDto> response = this.cardService.getEntity(any());

        Assertions.assertTrue(response.isSuccess(), "Unknown success value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown get error list value returned!");

        Assertions.assertEquals(response.getContent().getCardName(), responseCardDto.getCardName());


    }

    @Test
    public void testGetCardNegative() {

        when(this.cardRepository.findByCardIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseCardDto> response = this.cardService.getEntity(any());

        Assertions.assertFalse(response.isSuccess(), "Unknown success value returned!");
        Assertions.assertEquals(response.getMessage(), "Card with null id is not found!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown get error list value returned!");
    }

/*
    @Test
    public void testUpdateCardPositive() {

        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());

        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown get error list value returned!");
        Assertions.assertFalse(response.isSuccess(), "Unknown success value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
    }

    @Test
    public void testUpdateCardNegative() {

        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());

        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown get error list value returned!");
        Assertions.assertFalse(response.isSuccess(), "Unknown success value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
    }

    @Test
    public void testUpdateCardException() {

        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());

        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown get error list value returned!");
        Assertions.assertFalse(response.isSuccess(), "Unknown success value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
    }

    @Test
    public void testUpdateCardValidation() {

        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());

        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown get error list value returned!");
        Assertions.assertFalse(response.isSuccess(), "Unknown success value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
    }

    @Test
    public void testDeleteCardPositive() {

        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());

        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown get error list value returned!");
        Assertions.assertFalse(response.isSuccess(), "Unknown success value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
    }

    @Test
    public void testDeleteCardNegative() {

        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());

        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown get error list value returned!");
        Assertions.assertFalse(response.isSuccess(), "Unknown success value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
    }

    @Test
    public void testDeleteCardException() {


        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());

        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown get error list value returned!");
        Assertions.assertFalse(response.isSuccess(), "Unknown success value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
    }*/

}
