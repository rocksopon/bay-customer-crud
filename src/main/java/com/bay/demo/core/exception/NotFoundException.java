package com.bay.demo.core.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {
    private String code;
    private String message;

    public NotFoundException(String code, String message){
        super(message);
        this.message = message;
        this.code = code;
    }
}
