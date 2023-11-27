package com.company.kimyouz.controller;

import com.company.kimyouz.dto.LogInDto;
import com.company.kimyouz.dto.LogOutDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.ResponseTokenDto;
import com.company.kimyouz.dto.request.RequestUserDto;
import com.company.kimyouz.dto.response.ResponseUserDto;
import com.company.kimyouz.service.UserService;
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

import static com.company.kimyouz.dto.SimpleResponseDto.convertStatusCodeByData;
import static com.company.kimyouz.constans.SwaggerConstans.EXAMPLE_USER_NOT_FOUND;
import static com.company.kimyouz.constans.SwaggerConstans.EXAMPLE_USER_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "user")
public class UserController implements SimpleRequestCrud<Integer, RequestUserDto, ResponseUserDto> {
    private final UserService userService;

    @Override
    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "User API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_USER_SUCCESS)
                            )
                    ), @ApiResponse(description = "User API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_USER_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is user Post Method")
    public ResponseEntity<ResponseDto<ResponseUserDto>> createEntity(@RequestBody @Valid RequestUserDto entity) {
        return convertStatusCodeByData(this.userService.createEntity(entity));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDto<ResponseTokenDto>> logIn(@RequestBody @Valid LogInDto logIn) {
        return convertStatusCodeByData(this.userService.logIn(logIn));
    }


    @GetMapping(value = "/refresh-token")
    public ResponseEntity<ResponseDto<ResponseTokenDto>> refreshToken(@RequestParam String token) {
        return convertStatusCodeByData(this.userService.refreshToken(token));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<ResponseDto<Void>> logOut(@RequestBody @Valid LogOutDto logIn) {
        return convertStatusCodeByData(this.userService.logout(logIn));
    }


    @Override
    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "User API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_USER_SUCCESS)
                            )
                    ), @ApiResponse(description = "User API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_USER_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is user Get Method")
    public ResponseEntity<ResponseDto<ResponseUserDto>> getEntity(@RequestParam(value = "id") Integer entityId) {
        return convertStatusCodeByData(this.userService.getEntity(entityId));
    }

    @Override
    @PutMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "User API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_USER_SUCCESS)
                            )
                    ), @ApiResponse(description = "User API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(value = EXAMPLE_USER_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is user Put Method")
    public ResponseEntity<ResponseDto<ResponseUserDto>> updateEntity(@RequestParam(value = "id") Integer entityId,
                                                                     @RequestBody @Valid RequestUserDto entity) {
        return convertStatusCodeByData(this.userService.updateEntity(entityId, entity));
    }

    @Override
    @DeleteMapping
    @ApiResponses(
            value = {
                    @ApiResponse(description = "User API Success Post Method",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ResponseDto.class
                                    ),
                                    examples = @ExampleObject(value = EXAMPLE_USER_SUCCESS)
                            )
                    ), @ApiResponse(description = "User API Success Post Method",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    implementation = ResponseDto.class
                            ),
                            examples = @ExampleObject(EXAMPLE_USER_NOT_FOUND)
                    )
            )
            })
    @Operation(summary = "This is user Delete Method")
    public ResponseEntity<ResponseDto<ResponseUserDto>> deleteEntity(@RequestParam(value = "id") Integer entityId) {
        return convertStatusCodeByData(this.userService.deleteEntity(entityId));
    }
}
