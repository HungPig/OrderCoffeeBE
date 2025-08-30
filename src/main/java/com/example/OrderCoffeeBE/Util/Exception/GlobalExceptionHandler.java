package com.example.OrderCoffeeBE.Util.Exception;

import com.example.OrderCoffeeBE.Entity.Res.RestResponse;
import com.example.OrderCoffeeBE.Util.Error.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Data
    public static class ErrorResponse {
        private int status;
        private Date timestamp;
        private String message;
        private String path;

        public ErrorResponse(int status, Date timestamp, String message, String path) {
            this.status = status;
            this.timestamp = timestamp;
            this.message = message;
            this.path = path;
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request, HttpServletRequest httpRequest) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("message", "An unexpected error occurred");
        body.put("path", httpRequest != null ? httpRequest.getRequestURI() : "unknown");
        if (ex != null && ex.getMessage() != null) {
            body.put("details", ex.getMessage());
        } else {
            body.put("details", "Unknown error");
        }
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
