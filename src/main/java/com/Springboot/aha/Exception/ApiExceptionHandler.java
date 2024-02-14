package com.Springboot.aha.Exception;

import com.Springboot.aha.Exception.User.UsernameIsInvalidException;
import com.Springboot.aha.Exception.User.UsernameIsNotUniqueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiResquestExceptionn(ApiRequestException e) {
        // create payload exception
        ApiException apiException = new ApiException(e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        // return response
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {UsernameIsNotUniqueException.class})
    public ResponseEntity<Object> handleUsernameIsNotUniqueExceptionn(UsernameIsNotUniqueException e) {

        // create payload exception
        ApiException apiException = new ApiException(e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        // return response
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ResponseStatus
    @ExceptionHandler(value = {UsernameIsInvalidException.class})
    public ResponseEntity<Object> handleUsernameIsInvalidExceptionn(UsernameIsInvalidException e) {

        // create payload exception
        ApiException apiException = new ApiException(e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        // return response
        return new ResponseEntity<>(apiException, badRequest);
    }
}
