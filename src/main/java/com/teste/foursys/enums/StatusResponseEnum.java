package com.teste.foursys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusResponseEnum {

    SUCCESS_RESPONSE(200, "SUCESSO"),
    NOT_FOUND(404, "NOT FOUND"),
    BAD_REQUEST(400, "Bad Request");

    private final int code;
    private final String message;
}
