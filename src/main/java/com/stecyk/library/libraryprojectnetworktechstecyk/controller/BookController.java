package com.stecyk.library.libraryprojectnetworktechstecyk.controller;

import com.stecyk.library.libraryprojectnetworktechstecyk.controller.Errors.BookAlreadyExistsError;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.BookEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/allBooks")
    public List<BookEntity> getAllBooks(){
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_W') or hasRole('ROLE_U')")
    public BookEntity getOne(@PathVariable long id){
        BookEntity book = bookService.getOne(id);
        if(book == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with ID: " + id);
        }
        return bookService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<BookEntity> create(@RequestBody BookEntity book) {
        if(bookService.getOne(book.getId()) == null){
            throw BookAlreadyExistsError.create();
        }

        var newBook = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        BookEntity book = bookService.getOne(id);
        if(book == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with ID: " + id);
        }
        bookService.delete(id);
    }
}
