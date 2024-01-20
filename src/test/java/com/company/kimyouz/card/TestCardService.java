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

import static org.mockito.Mockito.*;

public class TestCardService {
    private CardService cardService;
    private CardRepository cardRepository;
    private CardMapper cardMapper;
    private CardValidation cardValidation;

    @BeforeEach
    public void initObject() {
        cardMapper = mock(CardMapper.class);
        cardValidation = mock(CardValidation.class);
        cardRepository = mock(CardRepository.class);
        cardService = new CardService(cardMapper, cardValidation, cardRepository);
    }

    @Test
    public void testCreateCardPositive() {
        when(cardValidation.cardValid(any())).thenReturn(new ArrayList<>());
        when(cardMapper.toDto(any())).thenReturn(ResponseCardDto.builder()
                .cardId(1)
                .cardCode("4444555566667777")
                .cardFullName("Mr CardHolder")
                .cardName("Visa")
                .build());

        Card card = Card.builder()
                .cardId(1)
                .cardCode("4444555566667777")
                .cardFullName("Mr CardHolder")
                .cardName("Visa")
                .build();

        when(cardMapper.toEntity(any())).thenReturn(card);

        when(cardRepository.save(any())).thenReturn(card);
        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());

        Assertions.assertEquals(response.getCode(),0,"Unknown get code value returned");
        Assertions.assertNull(response.getErrorList(),"Unknown get error list value returned");
        Assertions.assertTrue(response.isSuccess(),"Unknown get success value returned");
        Assertions.assertNotNull(response.getContent(),"Unknown get content value returned ");


        verify(this.cardMapper,times(1)).toDto(any());
        verify(this.cardMapper,times(1)).toEntity(any());
        verify(this.cardRepository,times(1)).save(any());
        verify(this.cardValidation,times(1)).cardValid(any());


    }

    @Test
    public void testCreateCardException() {
        when(cardValidation.cardValid(any())).thenReturn(new ArrayList<>());
        when(this.cardRepository.save(any())).thenReturn(RuntimeException.class);

        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());

        Assertions.assertEquals(response.getCode(),-2,"Unknown get code value returned");
        Assertions.assertNull(response.getErrorList(),"Unknown get error list value returned");
        Assertions.assertFalse(response.isSuccess(),"Unknown get success value returned");
        Assertions.assertNull(response.getContent(),"Unknown get content value returned ");
    }

    @Test
    public void testCreateCardValidation(){
        when(cardValidation.cardValid(any())).thenReturn(
                List.of(new ErrorDto("userId",String.format("User with %d id is not found!",1)))
        );
        ResponseDto<ResponseCardDto> response = this.cardService.createEntity(any());

        Assertions.assertEquals(response.getCode(),-3,"Unknown get code value returned");
        Assertions.assertNotNull(response.getErrorList(),"Unknown get error list value returned");
        Assertions.assertFalse(response.isSuccess(),"Unknown get success value returned");
        Assertions.assertNull(response.getContent(),"Unknown get content value returned ");

        verify(cardValidation,times(1)).cardValid(any());
    }

}
