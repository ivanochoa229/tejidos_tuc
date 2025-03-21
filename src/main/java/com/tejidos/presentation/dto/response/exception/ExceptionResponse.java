package com.tejidos.presentation.dto.response.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExceptionResponse implements Serializable {
    private String backendMessage;
    private HttpStatus status;
    private String method;
    private String endpoint;
    private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));

    public ExceptionResponse() {
    }

    public ExceptionResponse(String backendMessage, HttpStatus status, String endpoint, String method) {
        this.backendMessage = backendMessage;
        this.endpoint = endpoint;
        this.method = method;
        this.status = status;
    }

    public String getBackendMessage() {
        return backendMessage;
    }

    public void setBackendMessage(String backendMessage) {
        this.backendMessage = backendMessage;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
