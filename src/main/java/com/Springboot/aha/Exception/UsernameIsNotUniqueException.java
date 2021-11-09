package com.Springboot.aha.Exception;

public class UsernameIsNotUniqueException extends Exception {
    public UsernameIsNotUniqueException(String message) {
        super(message);
    }
}
