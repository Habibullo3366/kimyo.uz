package com.company.kimyouz.controller;

import com.company.kimyouz.dto.LogInResponseDto;
import com.company.kimyouz.dto.LogOutResponseDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.TokenResponseDto;
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
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.kimyouz.config.SimpleResponseDto.convertStatusCodeByData;
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
                    ),@ApiResponse(description = "User API Success Post Method",
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
    public ResponseEntity<ResponseDto<ResponseUserDto>> createEntity(@RequestBody RequestUserDto entity) {
        return convertStatusCodeByData(this.userService.createEntity(entity));
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
                    ),@ApiResponse(description = "User API Success Post Method",
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
                    ),@ApiResponse(description = "User API Success Post Method",
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
    public ResponseEntity<ResponseDto<ResponseUserDto>> updateEntity(Integer entityId, RequestUserDto entity) {
        return convertStatusCodeByData(this.userService.updateEntity(entityId,entity));
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
                    ),@ApiResponse(description = "User API Success Post Method",
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
    public ResponseEntity<ResponseDto<ResponseUserDto>> deleteEntity(Integer entityId) {
        return convertStatusCodeByData(this.userService.deleteEntity(entityId));
    }




    @PostMapping(value = "/login")
    public ResponseDto<TokenResponseDto> logIn(@RequestBody LogInResponseDto dto){
        return this.userService.logIn(dto);
    }

    @PostMapping(value = "/refresh")
    public ResponseDto<TokenResponseDto> refresh(@RequestParam String token){
        return this.userService.refresh(token);
    }


    @PostMapping(value = "/logout")
    public ResponseDto<Void> logOut(@RequestBody LogOutResponseDto dto){
        return this.userService.logOut(dto);
    }

}
