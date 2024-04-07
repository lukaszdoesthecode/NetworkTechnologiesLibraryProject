package com.stecyk.library.libraryprojectnetworktechstecyk.controller.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookAlreadyExistsError extends RuntimeException{
    /**
     * Class representing error when book with this id already exists.
     */
    private BookAlreadyExistsError(String message){
        super(message);
    }

    public static ResponseStatusException create(){
        BookAlreadyExistsError error = new BookAlreadyExistsError(String.format("Book with this id already exists."));
        return new ResponseStatusException(HttpStatus.CONFLICT, error.getMessage(), error);
    }
}
