package com.company.kimyouz.controller;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestPaymentDto;
import com.company.kimyouz.dto.response.ResponsePaymentDto;
import com.company.kimyouz.service.PaymentService;
import com.company.kimyouz.util.SimpleRequestCrud;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.company.kimyouz.config.SimpleResponseDto.convertStatusCodeByData;
import static com.company.kimyouz.constans.SwaggerConstans.EXAMPLE_PAYMENT_NOT_FOUND;
import static com.company.kimyouz.constans.SwaggerConstans.EXAMPLE_PAYMENT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "payment")
public class PaymentController implements SimpleRequestCrud<Integer, RequestPaymentDto, ResponsePaymentDto> {
    private final PaymentService paymentService;
    @Override
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Payment API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_PAYMENT_SUCCESS)
                            )
                    ),@ApiResponse(description = "Payment API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_PAYMENT_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is payment Post Method")
    public ResponseEntity<ResponseDto<ResponsePaymentDto>> createEntity(@RequestBody @Valid RequestPaymentDto entity) {
        return convertStatusCodeByData(this.paymentService.createEntity(entity));
    }

    @Override
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Payment API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_PAYMENT_SUCCESS)
                            )
                    ),@ApiResponse(description = "Payment API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_PAYMENT_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is payment Get Method")
    public ResponseEntity<ResponseDto<ResponsePaymentDto>> getEntity(Integer entityId) {
        return convertStatusCodeByData(this.paymentService.getEntity(entityId));
    }

    @Override
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Payment API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_PAYMENT_SUCCESS)
                            )
                    ),@ApiResponse(description = "Payment API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_PAYMENT_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is payment Put Method")
    public ResponseEntity<ResponseDto<ResponsePaymentDto>> updateEntity(Integer entityId,
                                                                        @RequestBody @Valid RequestPaymentDto entity) {
        return convertStatusCodeByData(this.paymentService.updateEntity(entityId,entity));
    }

    @Override
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Payment API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_PAYMENT_SUCCESS)
                            )
                    ),@ApiResponse(description = "Payment API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_PAYMENT_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is payment Delete Method")
    public ResponseEntity<ResponseDto<ResponsePaymentDto>> deleteEntity(Integer entityId) {
        return convertStatusCodeByData(this.paymentService.deleteEntity(entityId));
    }
}
