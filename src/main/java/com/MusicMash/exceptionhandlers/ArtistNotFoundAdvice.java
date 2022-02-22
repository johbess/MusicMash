package com.MusicMash.exceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ArtistNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ArtistNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String ArtistNotFoundHandler(ArithmeticException ex) {
        return ex.getMessage();
    }
}
