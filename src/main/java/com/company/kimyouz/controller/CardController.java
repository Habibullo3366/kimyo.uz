package com.company.kimyouz.controller;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestCardDto;
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.service.CardService;
import com.company.kimyouz.util.SimpleRequestCrud;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.kimyouz.config.SimpleResponseDto.convertStatusCodeByData;
import static com.company.kimyouz.constans.SwaggerConstans.EXAMPLE_CARD_NOT_FOUND;
import static com.company.kimyouz.constans.SwaggerConstans.EXAMPLE_CARD_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "card")
public class CardController implements SimpleRequestCrud<Integer, RequestCardDto, ResponseCardDto> {
    private final CardService cardService;
    @Override
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Card API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_CARD_SUCCESS )
                            )
                    ),@ApiResponse(description = "Card API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_CARD_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is card Post Method")
    public ResponseEntity<ResponseDto<ResponseCardDto>> createEntity(RequestCardDto entity) {
        return convertStatusCodeByData(this.cardService.createEntity(entity));
    }

    @Override
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Card API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_CARD_SUCCESS )
                            )
                    ),@ApiResponse(description = "Card API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_CARD_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is card Get Method")
    public ResponseEntity<ResponseDto<ResponseCardDto>> getEntity(Integer entityId) {
        return convertStatusCodeByData(this.cardService.getEntity(entityId));
    }

    @Override
    @PutMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Card API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_CARD_SUCCESS )
                            )
                    ),@ApiResponse(description = "Card API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_CARD_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is card Put Method")
    public ResponseEntity<ResponseDto<ResponseCardDto>> updateEntity(Integer entityId, RequestCardDto entity) {
        return convertStatusCodeByData(this.cardService.updateEntity(entityId,entity));
    }

    @Override
    @DeleteMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Card API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_CARD_SUCCESS )
                            )
                    ),@ApiResponse(description = "Card API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_CARD_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is card Delete Method")
    public ResponseEntity<ResponseDto<ResponseCardDto>> deleteEntity(Integer entityId) {
        return convertStatusCodeByData(this.cardService.deleteEntity(entityId));
    }
}
