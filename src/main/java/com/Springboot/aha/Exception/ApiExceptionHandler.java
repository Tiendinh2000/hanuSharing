package com.Springboot.aha.Exception;

import com.Springboot.aha.Exception.User.PermissionDeniedException;
import com.Springboot.aha.Exception.User.UsernameOrPasswordIsInvalidException;
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
                ZonedDateTime.now(ZoneId.of("Z")));
        // return response
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {UsernameIsNotUniqueException.class})
    public ResponseEntity<Object> handleUsernameIsNotUniqueExceptionn(UsernameIsNotUniqueException e) {

        // create payload exception
        ApiException apiException = new ApiException(e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        // return response
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ResponseStatus
    @ExceptionHandler(value = {UsernameOrPasswordIsInvalidException.class})
    public ResponseEntity<Object> handleUsernameIsInvalidExceptionn(UsernameOrPasswordIsInvalidException e) {

        // create payload exception
        ApiException apiException = new ApiException(e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("")));
        // return response
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> globalException(RuntimeException e) {

        // create payload exception
        ApiException apiException = new ApiException(e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        // return response
        return new ResponseEntity<>(apiException, badRequest);
    }


    @ExceptionHandler(value = {PermissionDeniedException.class})
    public ResponseEntity<Object> globalException(PermissionDeniedException e) {

        // create payload exception
        ApiException apiException = new ApiException(e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        // return response
        return new ResponseEntity<>(apiException, badRequest);
    }
}
