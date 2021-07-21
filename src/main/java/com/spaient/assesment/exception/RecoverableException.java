package com.spaient.assesment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RecoverableException extends RuntimeException {

    public RecoverableException(){
        super("Please try after some time.");
    }

    public RecoverableException(String message){
        super(message);
    }
}
