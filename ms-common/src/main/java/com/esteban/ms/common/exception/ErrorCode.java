package com.esteban.ms.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    U001("Unexpected error.", 500),
    A001("Illegal argument.", 400),
    N404("Not found.", 404),
    ;

    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, int status) {
        this.message = message;
        this.status = HttpStatus.valueOf(status);
    }

}
