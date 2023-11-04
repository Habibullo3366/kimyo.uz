package com.company.kimyouz.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto <T>{
    private boolean success;
    /*
     *  1 already exists
     *  0 it is ok         -> 202
     * -1 is not found     -> 404
     * -2 db error         -> 400
     * -3 validation error -> 400
     * -4 any exception    -> 400
     * */
    private int code;

    private String message;

    private T content;

    private List<ErrorDto> errorList;
}
