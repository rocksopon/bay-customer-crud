package com.bay.demo.core.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.*;

@ControllerAdvice
public class RestRepositoryExceptionHandler {
    @ExceptionHandler(UnprocessableException.class)
    public ResponseEntity handleUnprocessableException(HttpServletRequest request, UnprocessableException e, Throwable ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ExceptionResponse.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .timestamp(new Date())
                .path(request.getRequestURI())
                .error(e.getMessage())
                .code(e.getCode())
                .message(e.getMessage()).build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(HttpServletRequest request, NotFoundException e, Throwable ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .path(request.getRequestURI())
                .error(e.getMessage())
                .code(e.getCode())
                .message(e.getMessage()).build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(HttpServletRequest request, AccessDeniedException e, Throwable ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .timestamp(new Date())
                .path(request.getRequestURI())
                .error(e.getMessage())
                .message(e.getMessage()).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}

@Data
@Builder
class ExceptionResponse {
    private int status;
    private Date timestamp;
    private String path;
    private String error;
    private String code;
    private String message;
}
