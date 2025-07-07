package com.hirrao.javaee.utils;

import com.hirrao.javaee.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    private ResponseBuilder() {
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(ApiResponse.success(data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> status(HttpStatus status, T data) {
        return ResponseEntity.status(status)
                             .body(ApiResponse.success(data));
    }

    public static ResponseEntity<ApiResponse<String>> error(HttpStatus status, String data) {
        return ResponseEntity.status(status)
                             .body(ApiResponse.error(data));
    }
}
