package com.company.kimyouz.controller;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestCategoryDto;
import com.company.kimyouz.dto.response.ResponseCategoryDto;
import com.company.kimyouz.service.CategoryService;
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
import static com.company.kimyouz.constans.SwaggerConstans.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "category")
public class CategoryController implements SimpleRequestCrud<Integer, RequestCategoryDto, ResponseCategoryDto> {
    private final CategoryService categoryService;
    @Override
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Category API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    ),@ApiResponse(description = "Category API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is category Post Method")
    public ResponseEntity<ResponseDto<ResponseCategoryDto>> createEntity(RequestCategoryDto entity) {
        return convertStatusCodeByData(this.categoryService.createEntity(entity));
    }

    @Override
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Category API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    ),@ApiResponse(description = "Category API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is category Get Method")
    public ResponseEntity<ResponseDto<ResponseCategoryDto>> getEntity(Integer entityId) {
        return convertStatusCodeByData(this.categoryService.getEntity(entityId));
    }

    @Override
    @PutMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Category API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    ),@ApiResponse(description = "Category API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is category Put Method")
    public ResponseEntity<ResponseDto<ResponseCategoryDto>> updateEntity(Integer entityId, RequestCategoryDto entity) {
        return convertStatusCodeByData(this.categoryService.updateEntity(entityId,entity));
    }

    @Override
    @DeleteMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Category API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    ),@ApiResponse(description = "Category API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is category Delete  Method")
    public ResponseEntity<ResponseDto<ResponseCategoryDto>> deleteEntity(Integer entityId) {
        return convertStatusCodeByData(this.categoryService.deleteEntity(entityId));
    }
}
