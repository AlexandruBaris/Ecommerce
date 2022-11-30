package com.alexandru.springbootecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StatusNotFound extends RuntimeException{
    public StatusNotFound(String message){
        super(message);
    }
}
