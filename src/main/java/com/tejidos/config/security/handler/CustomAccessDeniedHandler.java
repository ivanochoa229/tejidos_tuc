package com.tejidos.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tejidos.presentation.dto.response.exception.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setBackendMessage(accessDeniedException.getMessage());
        exceptionResponse.setMethod(request.getMethod());
        exceptionResponse.setEndpoint(String.valueOf(request.getRequestURI()));
        exceptionResponse.setStatus(HttpStatus.FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String dtoAsJson = mapper.writeValueAsString(exceptionResponse);
        response.getWriter().write(dtoAsJson);
    }
}
