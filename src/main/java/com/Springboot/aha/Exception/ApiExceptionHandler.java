package com.Springboot.aha.Exception;

import com.Springboot.aha.Exception.common.BadRequestException;
import com.Springboot.aha.Exception.User.InvalidUsernameOrPasswordException;
import com.Springboot.aha.Exception.User.UnauthorizedException;
import com.Springboot.aha.Exception.User.UsernameIsNotUniqueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(value = {InvalidUsernameOrPasswordException.class})
    public ResponseEntity<Object> handleUsernameIsInvalidExceptionn(InvalidUsernameOrPasswordException e) {
        // create payload exception
        ApiException apiException = new ApiException(e.getMessage(),
                HttpStatus.OK,
                ZonedDateTime.now(ZoneId.of("Z")));
        // return response
        return new ResponseEntity<>(apiException,HttpStatus.OK);
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<Object> handleUnAuthorizedException(UnauthorizedException e) {
        // create payload exception
        ApiException apiException = new ApiException(e.getMessage(),
                HttpStatus.UNAUTHORIZED,
                ZonedDateTime.now(ZoneId.of("Z")));
        // return response
        return new ResponseEntity<>(apiException,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
        // create payload exception
        ApiException apiException = new ApiException(e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")));
        // return response
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
}
