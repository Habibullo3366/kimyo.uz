package com.company.kimyouz.service;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestCardDto;
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.entity.Card;
import com.company.kimyouz.mapper.CardMapper;
import com.company.kimyouz.repository.CardRepository;
import com.company.kimyouz.validation.CardValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardMapper cardMapper;
    private final CardValidation cardValidation;
    private final CardRepository cardRepository;

    public ResponseDto<ResponseCardDto> createEntity(RequestCardDto dto) {
        List<ErrorDto> errors = this.cardValidation.cardValid(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errors)
                    .build();
        }
        try {
            Card card = cardMapper.toEntity(dto);
            card.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseCardDto>builder()
                    .success(true)
                    .message("OK")
                    .data(
                            this.cardMapper.toDto(
                                    this.cardRepository.save(card)
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-2)
                    .message(String.format("Card while saving error message :: %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<ResponseCardDto> getEntity(Integer entityId) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-1)
                    .message(String.format("Card with %d:id is not found", entityId))
                    .build();
        }
        return ResponseDto.<ResponseCardDto>builder()
                .success(true)
                .message("OK")
                .data(this.cardMapper.toDto(
                                optional.get()
                        )
                )
                .build();
    }


    public ResponseDto<ResponseCardDto> updateEntity(Integer entityId, RequestCardDto dto) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-1)
                    .message(String.format("Card with %d:id is not found", entityId))
                    .build();
        }
        Card card = optional.get();
        card.setUpdatedAt(LocalDateTime.now());
        return ResponseDto.<ResponseCardDto>builder()
                .success(true)
                .message("OK")
                .data(this.cardMapper.toDto(
                                this.cardRepository.save(
                                        this.cardMapper.updateCard(dto, optional.get())
                                )
                        )
                )
                .build();
    }


    public ResponseDto<ResponseCardDto> deleteEntity(Integer entityId) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-1)
                    .message(String.format("Card with %d:id is not found", entityId))
                    .build();
        }
        Card card = optional.get();
        card.setDeletedAt(LocalDateTime.now());
        return ResponseDto.<ResponseCardDto>builder()
                .success(true)
                .message("OK")
                .data(
                        this.cardMapper.toDto(
                                this.cardRepository.save(card)
                        )
                )
                .build();
    }

    public ResponseDto<Set<ResponseCardDto>> getAllByDeletedAtIsNull() {
        Set<Card> cardList = this.cardRepository.findAllByDeletedAtIsNotNull();
        if (cardList.isEmpty()) {
            return ResponseDto.<Set<ResponseCardDto>>builder()
                    .code(-1)
                    .message("Cards are not found!")
                    .build();
        }
        return ResponseDto.<Set<ResponseCardDto>>builder()
                .success(true)
                .message("OK")
                .build();
    }

}
