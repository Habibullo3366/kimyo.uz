package com.company.kimyouz.Unit.card;

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
    private CardRepository cardRepository;
    private CardValidation cardValidation;
    private CardService cardService;

    @BeforeEach
    public void initMethod() {
        cardMapper = mock(CardMapper.class);
        cardValidation = mock(CardValidation.class);
        cardRepository = mock(CardRepository.class);
        this.cardService = new CardService(cardMapper, cardValidation, cardRepository);
    }

    // Create
    @Test
    public void testCreateCardPositive() {
        when(cardValidation.cardValid(any()))
                .thenReturn(new ArrayList<>());

        when(cardMapper.toDto(any()))
                .thenReturn(ResponseCardDto.builder()
                        .cardId(1)
                        .cardName("Humo")
                        .cardCode("0000111122223333")
                        .cardFullName("Abduali Abdumutalibov")
                        .build());

        Card card = Card.builder()
                .cardId(1)
                .cardName("Humo")
                .cardCode("0000111122223333")
                .cardFullName("Abduali Abdumutalibov")
                .build();


        when(cardMapper.toEntity(any()))
                .thenReturn(card);

        when(cardRepository.save(any()))
                .thenReturn(card);

        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");

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
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -2, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");

    }

    @Test
    public void testCreateCardValidation() {
        when(this.cardValidation.cardValid(any()))
                .thenReturn(List.of(
                        new ErrorDto("userId", String.format("User with this %d is not found", 1))
                ));

        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown error list value returned!");

    }

    // Get
    @Test
    public void testGetCardPositive() {
        ResponseCardDto responseCardDto = ResponseCardDto.builder()
                .cardId(1)
                .cardName("Humo")
                .cardCode("0000111122223333")
                .cardFullName("Abduali Abdumutalibov")
                .build();

        when(this.cardMapper.toDto(any()))
                .thenReturn(responseCardDto);

        when(this.cardRepository.findByCardIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        Card.builder()
                                .cardId(1)
                                .cardName("Humo")
                                .cardCode("0000111122223333")
                                .cardFullName("Abduali Abdumutalibov")
                                .build()
                ));

        ResponseDto<ResponseCardDto> response = this.cardService.getEntity(any());
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");
    }

    @Test
    public void testGetCardNegative() {
        when(this.cardRepository.findByCardIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseCardDto> response = this.cardService.getEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    // Update
    @Test
    public void testUpdateCardPositive() {

        when(this.cardRepository.findByCardIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        Card.builder()
                                .cardId(3)
                                .cardName("Humo")
                                .cardCode("0000111122223333")
                                .cardFullName("Abduali Abdumutalibov")
                                .build()));


        when(this.cardMapper.toDto(any()))
                .thenReturn(ResponseCardDto.builder()
                        .cardId(2)
                        .cardName("Humo")
                        .cardCode("0000111122223333")
                        .cardFullName("Abduali Abdumutalibov")
                        .build());


        when(this.cardMapper.updateCard(any(), any()))
                .thenReturn(Card.builder()
                        .cardId(1)
                        .cardName("Humo")
                        .cardCode("0000111122223333")
                        .cardFullName("Abduali Abdumutalibov")
                        .build());

        ResponseDto<ResponseCardDto> response = this.cardService.updateEntity(1, any());
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testUpdateCardNegative() {
        when(this.cardRepository.findByCardIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseCardDto> response = this.cardService.updateEntity(1, any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testUpdateCardException() {

        when(this.cardValidation.cardValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.cardRepository.save(any()))
                .thenThrow(RuntimeException.class);


        ResponseDto<ResponseCardDto> response = this.cardService.updateEntity(1, any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testUpdateCardValidation() {

        when(this.cardValidation.cardValid(any()))
                .thenReturn(List.of(
                        new ErrorDto("userId", String.format("User with this %d is not found", 1))
                ));

        ResponseDto<ResponseCardDto> response = this.cardService.updateEntity(1, any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNotNull(response.getErrorList(), "Unknown error list value returned!");

    }

    // Delete
    @Test
    public void testDeleteCardPositive() {

        when(this.cardRepository.findByCardIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        Card.builder()
                                .cardId(3)
                                .cardName("Humo")
                                .cardCode("0000111122223333")
                                .cardFullName("Abduali Abdumutalibov")
                                .build()));


        when(this.cardMapper.toDto(any()))
                .thenReturn(ResponseCardDto.builder()
                        .cardId(2)
                        .cardName("Humo")
                        .cardCode("0000111122223333")
                        .cardFullName("Abduali Abdumutalibov")
                        .build());

        when(this.cardMapper.toEntity(any()))
                .thenReturn(Card.builder()
                        .cardId(1)
                        .cardName("Humo")
                        .cardCode("0000111122223333")
                        .cardFullName("Abduali Abdumutalibov")
                        .build());

        ResponseDto<ResponseCardDto> response = this.cardService.deleteEntity(any());
        Assertions.assertTrue(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNotNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");


    }

    @Test
    public void testDeleteCardNegative() {

        when(this.cardRepository.findByCardIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseCardDto> response = this.cardService.deleteEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");

    }

    @Test
    public void testDeleteCardException() {

        when(this.cardValidation.cardValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.cardRepository.save(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseCardDto> response = this.cardService.deleteEntity(any());
        Assertions.assertFalse(response.isSuccess(), "Unknown success code value returned!");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned!");
        Assertions.assertNotNull(response.getMessage(), "Unknown message value returned!");
        Assertions.assertNull(response.getContent(), "Unknown content value returned!");
        Assertions.assertNull(response.getErrorList(), "Unknown error list value returned!");
    }

}
