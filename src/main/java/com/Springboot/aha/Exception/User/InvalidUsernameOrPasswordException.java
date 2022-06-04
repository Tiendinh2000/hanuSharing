package com.Springboot.aha.Exception.User;

public class InvalidUsernameOrPasswordException extends RuntimeException {
    public InvalidUsernameOrPasswordException(String message) {
        super(message);
    }
}
