package com.stecyk.library.libraryprojectnetworktechstecyk.controller;

import com.stecyk.library.libraryprojectnetworktechstecyk.controller.Errors.BookAlreadyExistsError;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.BookEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.service.BookService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@PreAuthorize("isAuthenticated()")
@Tag(name = "Book", description = "Book data represented in the system")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/allBooks")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All books returned"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public List<BookEntity> getAllBooks(){
        /**
         * Method to get all books.
         * @return List of all books.
         */
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book returned"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public BookEntity getOne(@PathVariable long id){
        /**
         * Method to get one book by id.
         * @param id - id of book.
         * @return Book with this id.
         */
        BookEntity book = bookService.getOne(id);
        if(book == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with ID: " + id);
        }
        return bookService.getOne(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_W')")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created"),
            @ApiResponse(responseCode = "409", description = "Book already exists"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<BookEntity> create(@RequestBody BookEntity book) {
        /**
         * Method to create new book.
         * @param book - book to create.
         * @return Created book.
         */
        if(bookService.getOne(book.getId()) == null){
            throw BookAlreadyExistsError.create();
        }

        var newBook = bookService.create(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_W')")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public void delete(@PathVariable long id){
        /**
         * Method to delete book by id.
         * @param id - id of book.
         */
        BookEntity book = bookService.getOne(id);
        if(book == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with ID: " + id);
        }
        bookService.delete(id);
    }
}
