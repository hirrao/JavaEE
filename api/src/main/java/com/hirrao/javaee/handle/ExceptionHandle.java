package com.hirrao.javaee.handle;

import com.hirrao.javaee.exception.ClientException;
import com.hirrao.javaee.model.ApiResponse;
import com.hirrao.javaee.utils.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ApiResponse<String>> handleClientException(ClientException e) {
        logger.warn("Client Error:", e);
        return ResponseBuilder.error(e.getStatusCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
        logger.error("An error occurred: {}", e.getMessage());
        return ResponseBuilder.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
