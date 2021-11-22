package com.Springboot.aha.Exception.BindingResultException;

import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class BindingResultException {
    public static String getErrorMessage(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(" ,")).toString();
    }
}
