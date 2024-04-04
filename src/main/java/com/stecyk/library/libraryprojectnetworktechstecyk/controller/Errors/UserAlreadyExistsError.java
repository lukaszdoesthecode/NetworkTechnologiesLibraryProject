package com.stecyk.library.libraryprojectnetworktechstecyk.controller.Errors;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class UserAlreadyExistsError extends RuntimeException {
    private UserAlreadyExistsError(String message){
        super(message);
    }


    public static ResponseStatusException create(String username){
        UserAlreadyExistsError error = new UserAlreadyExistsError(String.format("User with username: %s already exists.", username));
        return new ResponseStatusException(HttpStatus.CONFLICT, error.getMessage(), error);
    }
}
