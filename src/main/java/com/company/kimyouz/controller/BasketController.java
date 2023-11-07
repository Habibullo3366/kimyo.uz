package com.company.kimyouz.controller;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestBasketDto;
import com.company.kimyouz.dto.response.ResponseBasketDto;
import com.company.kimyouz.service.BasketService;
import com.company.kimyouz.util.SimpleRequestCrud;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.kimyouz.config.SimpleResponseDto.convertStatusCodeByData;
import static com.company.kimyouz.constans.SwaggerConstans.EXAMPLE_BASKET_NOT_FOUND;
import static com.company.kimyouz.constans.SwaggerConstans.EXAMPLE_BASKET_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "basket")
public class BasketController implements SimpleRequestCrud<Integer,RequestBasketDto,ResponseBasketDto>{
    private final BasketService basketService;


    @Override
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Basket API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_BASKET_SUCCESS)
                            )
                    ),@ApiResponse(description = "Basket API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_BASKET_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is basket Post Method")
    public ResponseEntity<ResponseDto<ResponseBasketDto>> createEntity(RequestBasketDto dto) {
        return convertStatusCodeByData(this.basketService.createEntity(dto));
    }

    @Override
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Basket API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_BASKET_SUCCESS)
                            )
                    ),@ApiResponse(description = "Basket API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_BASKET_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is basket Get Method")
    public ResponseEntity<ResponseDto<ResponseBasketDto>> getEntity(Integer entityId) {
        return convertStatusCodeByData(this.basketService.getEntity(entityId));
    }

    @Override
    @PutMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Basket API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_BASKET_SUCCESS)
                            )
                    ),@ApiResponse(description = "Basket API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_BASKET_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is basket Put Method")
    public ResponseEntity<ResponseDto<ResponseBasketDto>> updateEntity(Integer entityId, RequestBasketDto entity) {
        return convertStatusCodeByData(this.basketService.updateEntity(entityId,entity));
    }

    @Override
    @DeleteMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Basket API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_BASKET_SUCCESS)
                            )
                    ),@ApiResponse(description = "Basket API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_BASKET_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is basket Delete Method")
    public ResponseEntity<ResponseDto<ResponseBasketDto>> deleteEntity(Integer entityId) {
        return convertStatusCodeByData(this.basketService.deleteEntity(entityId));
    }
}
