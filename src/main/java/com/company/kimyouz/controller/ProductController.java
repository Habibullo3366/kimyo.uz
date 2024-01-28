package com.company.kimyouz.controller;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestProductDto;
import com.company.kimyouz.dto.response.ResponseProductDto;
import com.company.kimyouz.service.ProductService;
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


import static com.company.kimyouz.constants.SwaggerConstans.EXAMPLE_PRODUCT_NOT_FOUND;
import static com.company.kimyouz.dto.SimpleResponseDto.convertStatusCodeByData;
import static com.company.kimyouz.constants.SwaggerConstans.EXAMPLE_PRODUCT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "product")
public class ProductController implements SimpleRequestCrud<Integer, RequestProductDto, ResponseProductDto> {
    private final ProductService productService;
    @Override
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Product API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),@ApiResponse(description = "Product API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is product Post Method")
    public ResponseEntity<ResponseDto<ResponseProductDto>> createEntity(@RequestBody @Valid RequestProductDto entity) {
        return convertStatusCodeByData(this.productService.createEntity(entity));
    }

    @Override
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Product API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),@ApiResponse(description = "Product API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is product Get Method")
    public ResponseEntity<ResponseDto<ResponseProductDto>> getEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.productService.getEntity(entityId));
    }

    @Override
    @PutMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Product API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),@ApiResponse(description = "Product API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is product Put Method")
    public ResponseEntity<ResponseDto<ResponseProductDto>> updateEntity(@RequestParam(value = "id")Integer entityId,
                                                                        @RequestBody @Valid RequestProductDto entity) {
        return convertStatusCodeByData(this.productService.updateEntity(entityId,entity));
    }

    @Override
    @DeleteMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Product API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),@ApiResponse(description = "Product API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is product Delete Method")
    public ResponseEntity<ResponseDto<ResponseProductDto>> deleteEntity(@RequestParam(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.productService.deleteEntity(entityId));
    }
   /* @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Product API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),@ApiResponse(description = "Product API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is product get-all-product-by-page Method")
    public ResponseEntity<ResponseDto<Page<ResponseProductDto>>> getAllProductByPage(Integer size, Integer page){
        return convertStatusCodeByData(this.productService.getAllProductByPage(size, page));
    }*/

   /* @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Product API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),@ApiResponse(description = "Product API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is product getAllProductSortByColumn Method")
    public ResponseEntity<ResponseDto<Page<ResponseProductDto>>> getAllProductSortByColumn(Integer size, Integer page, String column){
        return convertStatusCodeByData(this.productService.getAllProductSortByColumn(size, page, column));
    }*/

  /*  @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Product API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),@ApiResponse(description = "Product API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is product getAllProdByCategory Method")
    public ResponseEntity<ResponseDto<Map<String, List<ResponseProductDto>>>> getAllProdByCategory(Integer categoryId){
        return convertStatusCodeByData(this.productService.getAllProdByCategory(categoryId));
    }*/
  /*  @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Product API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                            )
                    ),@ApiResponse(description = "Product API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is product productUniversalSearch Method")
     public ResponseEntity<ResponseDto<Page<ResponseProductDto>>> productUniversalSearch(Map<String, String> params){
        return convertStatusCodeByData(this.productService.productUniversalSearch(params));
     }*/

   /*  @GetMapping
     @ApiResponses(
             value = {
                     @ApiResponse(description = "Product API Success Post Method",
                             responseCode = "200",
                             content = @Content(
                                     mediaType = MediaType.APPLICATION_JSON_VALUE,
                                     schema = @Schema(
                                             implementation = ResponseDto.class
                                     ),
                                     examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                             )
                     ),@ApiResponse(description = "Product API Success Post Method",
                     responseCode = "404",
                     content = @Content(
                             mediaType = MediaType.APPLICATION_JSON_VALUE,
                             schema = @Schema(
                                     implementation = ResponseDto.class
                             ),
                             examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                     )
             )
             })
     @Operation(summary = "This is product productAdvancedSearch Method")
     public ResponseEntity<ResponseDto<Page<ResponseProductDto>>> productAdvancedSearch(Map<String, String> params){
        return convertStatusCodeByData(this.productService.productAdvancedSearch(params));
     }*/

   /*  @GetMapping
     @ApiResponses(
             value = {
                     @ApiResponse(description = "Product API Success Post Method",
                             responseCode = "200",
                             content = @Content(
                                     mediaType = MediaType.APPLICATION_JSON_VALUE,
                                     schema = @Schema(
                                             implementation = ResponseDto.class
                                     ),
                                     examples = @ExampleObject(value = EXAMPLE_PRODUCT_SUCCESS)
                             )
                     ),@ApiResponse(description = "Product API Success Post Method",
                     responseCode = "404",
                     content = @Content(
                             mediaType = MediaType.APPLICATION_JSON_VALUE,
                             schema = @Schema(
                                     implementation = ResponseDto.class
                             ),
                             examples = @ExampleObject(value = EXAMPLE_PRODUCT_NOT_FOUND)
                     )
             )
             })
     @Operation(summary = "This is product getAllProduct Method")
     public ResponseEntity<ResponseDto<List<ResponseProductDto>>> getAllProduct(Integer prodId){
        return convertStatusCodeByData(this.productService.getAllProduct(prodId));
     }*/
}
