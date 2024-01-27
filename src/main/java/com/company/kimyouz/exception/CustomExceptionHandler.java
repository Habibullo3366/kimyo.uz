package com.company.kimyouz.exception;


import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseDto<List<ErrorDto>>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(ResponseDto.<List<ErrorDto>>builder()
                .code(-3)
                .message("Validation error")
                .errorList(
                        e.getFieldErrors().stream()
                                .map(fieldError -> {
                                    String field = fieldError.getField();
                                    String rejectedValue = String.valueOf(fieldError.getRejectedValue());
                                    String message = fieldError.getDefaultMessage();
                                    return new ErrorDto(field, String.format("Message: %s, Rejection value: %s", message, rejectedValue));
                                }).toList()
                )
                .build());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ResponseDto<Void>> runtimeException(RuntimeException e) {
        return ResponseEntity.badRequest().body(ResponseDto.<Void>builder()
                .code(-3)
                .message(e.getMessage())
                .build()
        );
    }

}
