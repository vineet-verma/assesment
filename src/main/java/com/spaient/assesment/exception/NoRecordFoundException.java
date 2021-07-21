package com.spaient.assesment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoRecordFoundException extends RuntimeException {

    public NoRecordFoundException() {
        super("No Record found.");
    }

    public NoRecordFoundException(String message) {
        super(message);
    }
}
