package com.esteban.ms.common.advicer;

import com.esteban.ms.common.dto.Error;
import com.esteban.ms.common.dto.MSResponse;
import com.esteban.ms.common.exception.ErrorCode;
import com.esteban.ms.common.exception.Location;
import com.esteban.ms.common.exception.MSException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@RestControllerAdvice
public class GlobalAdvicer {

    @Value("${spring.application.name:ms-common}")
    private String applicationName;

    private String getBodyErrors(List<FieldError> fieldErrors) {
        StringBuilder errors = new StringBuilder();
        for (FieldError fieldError: fieldErrors) {
            errors.append("Attribute [").append(fieldError.getField()).append("] : [").append(fieldError.getDefaultMessage()).append("]. ");
        }
        return errors.toString();
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<MSResponse<Void>> globalHandler(Throwable e) {
        return ResponseEntity
            .internalServerError()
            .body(
                new MSResponse<>(new Error(e.getMessage(), Location.getByMicroserviceName(applicationName).name(), ErrorCode.U001.name()))
            );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MSResponse<Void>> handle(MethodArgumentNotValidException e) {
        return ResponseEntity
            .badRequest()
            .body(
                new MSResponse<>(new Error(
                    getBodyErrors(e.getBindingResult().getFieldErrors()), Location.getByMicroserviceName(applicationName).name(), ErrorCode.A001.name()
                ))
            );
    }

    @ExceptionHandler(MSException.class)
    public ResponseEntity<MSResponse<Void>> handle(MSException e) {
        return new ResponseEntity<>(
            new MSResponse<>(new Error(e.getMessage(), e.getLocation().name(), e.getErrorCode().name())),
            e.getErrorCode().getStatus()
        );
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<MSResponse<Void>> handle(NoResourceFoundException e) {
        return new ResponseEntity<>(
            new MSResponse<>(
                new Error(
                    String.format("%s::%s::%s", e.getHttpMethod(), e.getResourcePath(), e.getMessage()),
                    Location.getByMicroserviceName(applicationName).name(),
                    ErrorCode.N404.name()
                )
            ),
            HttpStatus.NOT_FOUND
        );
    }

}
