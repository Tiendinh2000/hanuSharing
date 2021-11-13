package com.Springboot.aha.Exception.User;

public class UsernameIsInvalidException extends RuntimeException {
    public UsernameIsInvalidException(String message) {
        super(message);
    }
}
