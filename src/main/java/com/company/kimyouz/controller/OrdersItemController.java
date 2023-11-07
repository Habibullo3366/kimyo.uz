package com.company.kimyouz.controller;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestOrdersItemDto;
import com.company.kimyouz.dto.response.ResponseOrdersItemDto;
import com.company.kimyouz.service.OrderedItemService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "ordersItem")
public class OrdersItemController implements SimpleRequestCrud<Integer, RequestOrdersItemDto, ResponseOrdersItemDto> {
    private final OrderedItemService orderedItemService;
    @Override
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "OrdersItem API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject
                            )
                    ),@ApiResponse(description = "OrdersItem API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject
                    )
            )
            })
    @Operation(summary = "This is ordersItem Post Method")
    public ResponseEntity<ResponseDto<ResponseOrdersItemDto>> createEntity(RequestOrdersItemDto entity) {
        return convertStatusCodeByData(this.orderedItemService.createEntity(entity));
    }

    @Override
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "OrdersItem API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject
                            )
                    ),@ApiResponse(description = "OrdersItem API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject
                    )
            )
            })
    @Operation(summary = "This is ordersItem Get Method")
    public ResponseEntity<ResponseDto<ResponseOrdersItemDto>> getEntity(Integer entityId) {
        return convertStatusCodeByData(this.orderedItemService.getEntity(entityId));
    }

    @Override
    @PutMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "OrdersItem API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject
                            )
                    ),@ApiResponse(description = "OrdersItem API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject
                    )
            )
            })
    @Operation(summary = "This is ordersItem Put Method")
    public ResponseEntity<ResponseDto<ResponseOrdersItemDto>> updateEntity(Integer entityId, RequestOrdersItemDto entity) {
        return convertStatusCodeByData(this.orderedItemService.updateEntity(entityId,entity));
    }

    @Override
    @ApiResponses(
            value = {
                    @ApiResponse(description = "OrdersItem API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject
                            )
                    ),@ApiResponse(description = "OrdersItem API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject
                    )
            )
            })
    @Operation(summary = "This is ordersItem Delete Method")
    public ResponseEntity<ResponseDto<ResponseOrdersItemDto>> deleteEntity(Integer entityId) {
        return convertStatusCodeByData(this.orderedItemService.deleteEntity(entityId));
    }
}
