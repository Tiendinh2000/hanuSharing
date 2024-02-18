package com.Springboot.aha.Exception.User;

public class UsernameOrPasswordIsInvalidException extends RuntimeException {
    public UsernameOrPasswordIsInvalidException(String message) {
        super(message);
    }

    public UsernameOrPasswordIsInvalidException(String message , Throwable cause) {
        super(message, cause);
    }
}
