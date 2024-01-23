package com.company.kimyouz.service;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestCardDto;
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.service.mapper.CardMapper;
import com.company.kimyouz.repository.CardRepository;
import com.company.kimyouz.service.validation.CardValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
                    .message("Validation error!")
                    .errorList(errors)
                    .build();
        }

        try {
            return ResponseDto.<ResponseCardDto>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.cardMapper.toDto(
                                    this.cardRepository.save(
                                            this.cardMapper.toEntity(dto)
                                    )
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-2)
                    .message(String.format("Card while saving error! Message :: %s", e.getMessage()))
                    .build();
        }
    }



    public ResponseDto<ResponseCardDto> getEntity(Integer entityId) {
        return this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId)
                .map(card -> ResponseDto.<ResponseCardDto>builder()
                        .success(true)
                        .message("OK")
                        .content(this.cardMapper.toDto(card))
                        .build())
                .orElse(ResponseDto.<ResponseCardDto>builder()
                        .code(-1)
                        .message(String.format("Card with %d id is not found!", entityId))
                        .build());
    }



    public ResponseDto<ResponseCardDto> updateEntity(Integer entityId, RequestCardDto dto) {
        List<ErrorDto> errorList = this.cardValidation.cardValid(dto);
        if (!errorList.isEmpty()) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-3)
                    .message("Validation error!")
                    .errorList(errorList)
                    .build();
        }
        try {
            return this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId)
                    .map(card -> ResponseDto.<ResponseCardDto>builder()
                            .success(true)
                            .message("OK")
                            .content(this.cardMapper.toDto(
                                            this.cardRepository.save(
                                                    this.cardMapper.updateCard(dto, card)
                                            )
                                    )
                            )
                            .build()
                    )
                    .orElse(ResponseDto.<ResponseCardDto>builder()
                            .code(-1)
                            .message(String.format("Card with %d id is not found!", entityId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-2)
                    .message(String.format("Card while updating error! Message :: %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ResponseCardDto> deleteEntity(Integer entityId) {
        try {
            return this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId)
                    .map(card -> {
                        card.setDeletedAt(LocalDateTime.now());
                        return ResponseDto.<ResponseCardDto>builder()
                                .success(true)
                                .message("OK")
                                .content(this.cardMapper.toDto(
                                                this.cardRepository.save(card)
                                        )
                                )
                                .build();
                    })
                    .orElse(ResponseDto.<ResponseCardDto>builder()
                            .code(-1)
                            .message(String.format("Card with %d id is not found!", entityId))
                            .build());
        } catch (Exception e) {
            return ResponseDto.<ResponseCardDto>builder()
                    .code(-2)
                    .message(String.format("Card while deleting error! Message :: %s", e.getMessage()))
                    .build();
        }
    }
}
