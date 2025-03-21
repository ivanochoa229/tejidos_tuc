package com.tejidos.exception;

import com.tejidos.presentation.dto.response.exception.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;


@RestControllerAdvice
public class GlobalHandlerException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerException.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> genericHandler(Exception exception, HttpServletRequest request){
        logger.error("Generic handler exception :".concat(exception.getClass().getName()));
        logger.error("Message: ".concat(exception.getMessage()));
        logger.error("Stack trace: ", exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request.getMethod(),
                request.getRequestURI()
        );
        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> dataIntegrityViolationHandler(DataIntegrityViolationException exception, HttpServletRequest request){
        logger.error("Data integrity violation exception: ");
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST,
                request.getMethod(),
                request.getRequestURI()
        );
        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(NotFoundException exception, HttpServletRequest request) {
        logger.error("Resource not found: ", exception);
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                request.getMethod(),
                request.getRequestURI()
        );
        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }

    @ExceptionHandler(CreationException.class)
    public ResponseEntity<ExceptionResponse> categoryCreationException(CreationException exception, HttpServletRequest request){
        return getExceptionBadRequest(exception.getMessage(), request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception,
            HttpServletRequest request) {

        return getExceptionBadRequest("The request body is missing or malformed.", request);
    }

    private static ResponseEntity<ExceptionResponse> getExceptionBadRequest(String exception, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exception,
                HttpStatus.BAD_REQUEST,
                request.getMethod(),
                request.getRequestURI()
        );
        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse);
    }

}
