package com.company.kimyouz.controller;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestOrdersDto;
import com.company.kimyouz.dto.response.ResponseOrdersDto;
import com.company.kimyouz.service.OrderService;
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
import org.springframework.web.bind.annotation.*;

import static com.company.kimyouz.constans.SwaggerConstans.EXAMPLE_ORDERS_NOT_FOUND;
import static com.company.kimyouz.constans.SwaggerConstans.EXAMPLE_ORDERS_SUCCESS;
import static com.company.kimyouz.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "orders")
public class OrdersController implements SimpleRequestCrud<Integer, RequestOrdersDto, ResponseOrdersDto> {

    private final OrderService orderService;

    @Override
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Order API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),@ApiResponse(description = "Order API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is order Post Method")
    public ResponseEntity<ResponseDto<ResponseOrdersDto>> createEntity( @RequestBody @Valid RequestOrdersDto entity) {
        return convertStatusCodeByData(this.orderService.createEntity(entity));
    }

    @Override
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Order API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),@ApiResponse(description = "Order API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is order Get Method")
    public ResponseEntity<ResponseDto<ResponseOrdersDto>> getEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.orderService.getEntity(entityId));
    }

    @Override
    @PutMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Order API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),@ApiResponse(description = "Order API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is order Put Method")
    public ResponseEntity<ResponseDto<ResponseOrdersDto>> updateEntity(@RequestParam(value = "id")Integer entityId,
                                                                       @RequestBody @Valid RequestOrdersDto entity) {
        return convertStatusCodeByData(this.orderService.updateEntity(entityId,entity));
    }

    @Override
    @DeleteMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Order API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_ORDERS_SUCCESS)
                            )
                    ),@ApiResponse(description = "Order API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_ORDERS_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is order Delete Method")
    public ResponseEntity<ResponseDto<ResponseOrdersDto>> deleteEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.orderService.deleteEntity(entityId));
    }
}
