package com.stecyk.library.libraryprojectnetworktechstecyk.controller.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WrongPasswordError extends RuntimeException{
    private WrongPasswordError(String message){
        super(message);
    }

    public static ResponseStatusException create(){
        WrongPasswordError error = new WrongPasswordError(String.format("Wrong password"));
        return new ResponseStatusException(HttpStatus.CONFLICT, error.getMessage(), error);    }
}
