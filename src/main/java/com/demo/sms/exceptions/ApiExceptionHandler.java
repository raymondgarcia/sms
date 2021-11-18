package com.demo.sms.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    @ExceptionHandler({ApiRequestException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<ApiException> handleApiRequestException(ApiRequestException e) {
        final ApiException exception = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, badRequest);
    }

    //TODO: customize
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = MessageFormat.format("Validation Failed {0}", ex.getBindingResult().toString());
        ApiException exception = new ApiException(message ,badRequest, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
