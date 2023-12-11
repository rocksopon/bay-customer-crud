package com.bay.demo.core.exception;

import lombok.Data;

@Data
public class UnprocessableException extends RuntimeException {

    private String code;
    private String message;

    public UnprocessableException(String message, String code){
        super(message);
        this.message = message;
        this.code = code;
    }
}
