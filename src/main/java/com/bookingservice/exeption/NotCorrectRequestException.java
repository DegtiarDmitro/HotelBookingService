package com.bookingservice.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NotCorrectRequestException extends Exception  {

    public NotCorrectRequestException(String message) {
        super(message);
    }
}
