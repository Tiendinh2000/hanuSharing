package com.Springboot.aha.Exception.User;

public class PermissionDeniedException extends RuntimeException{
    public PermissionDeniedException(String message){
        super(message);
    }

    public PermissionDeniedException(String message, Throwable cause){
        super(message,cause);
    }
}
