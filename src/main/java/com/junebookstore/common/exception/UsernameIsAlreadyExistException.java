package com.junebookstore.common.exception;

public class UsernameIsAlreadyExistException extends RuntimeException {
    public UsernameIsAlreadyExistException(String message) {
        super(message);
    }
}
