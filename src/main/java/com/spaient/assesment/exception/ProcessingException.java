package com.spaient.assesment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProcessingException extends RuntimeException {

    public ProcessingException(){
        super("Unable to process request. Please check server log");
    }

    public ProcessingException(String message){
        super(message);
    }
}
