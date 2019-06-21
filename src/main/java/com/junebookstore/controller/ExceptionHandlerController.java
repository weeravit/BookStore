package com.junebookstore.controller;

import com.junebookstore.common.exception.BookNotFoundException;

import com.junebookstore.common.model.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDetail> handlerBookNotFound(BookNotFoundException ex, WebRequest request) {
        String message = String.format("Not Found Book id=%s", ex.getMessage());
        ErrorDetail errorDetail = new ErrorDetail(new Date(), message, request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
}
