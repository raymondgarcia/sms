package com.demo.sms.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({ApiRequestException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<ApiException> handleApiRequestException(ApiRequestException e) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final ApiException exception = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, badRequest);
    }
}
