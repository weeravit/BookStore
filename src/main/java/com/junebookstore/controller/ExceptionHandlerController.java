package com.junebookstore.controller;

import com.junebookstore.common.exception.BookNotFoundException;
import com.junebookstore.common.exception.UsernameIsAlreadyExistException;
import com.junebookstore.common.model.ErrorDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UsernameIsAlreadyExistException.class)
    public ResponseEntity<?> handlerUsernameIsAlreadyExist(UsernameIsAlreadyExistException ex, WebRequest request) {
        String message = String.format("%s is already exist", ex.getMessage());
        return handleResponse(ex, message, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handlerBookNotFound(BookNotFoundException ex, WebRequest request) {
        String message = String.format("Not Found Book id=%s", ex.getMessage());
        return handleResponse(ex, message, HttpStatus.NOT_FOUND, request);
    }

    private ResponseEntity<?> handleResponse(Exception ex, String message, HttpStatus httpStatus, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), message, request.getDescription(false));
        return handleExceptionInternal(ex, errorDetail, new HttpHeaders(), httpStatus, request);
    }
}
