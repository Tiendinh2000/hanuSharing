package com.Springboot.aha.Exception.User;

public class UsernameIsNotUniqueException extends RuntimeException {
    public UsernameIsNotUniqueException(String message) {
        super(message);
    }

    public UsernameIsNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }
}
