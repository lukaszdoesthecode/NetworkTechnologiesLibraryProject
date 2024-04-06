package com.stecyk.library.libraryprojectnetworktechstecyk.controller;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.BookDetailsEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.service.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookDetails")
@PreAuthorize("isAuthenticated()")
public class BookDetailsController {
    private final BookDetailsService bookDetailsService;

    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService){
        this.bookDetailsService = bookDetailsService;
    }

    @GetMapping("/allDetails")
    public List<BookDetailsEntity> getAll(){
        return bookDetailsService.getAll();
    }

    @GetMapping("/{id}")
    public BookDetailsEntity getOne(@PathVariable long id){
        return bookDetailsService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<BookDetailsEntity> create(@RequestBody BookDetailsEntity bookDetails){
        var newDetails = bookDetailsService.create(bookDetails);
        return new ResponseEntity<>(newDetails, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        bookDetailsService.delete(id);
    }
}
