package com.youcode.pigeonskyracev2.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}