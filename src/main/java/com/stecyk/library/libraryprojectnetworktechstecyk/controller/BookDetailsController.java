package com.stecyk.library.libraryprojectnetworktechstecyk.controller;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.BookDetailsEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.service.BookDetailsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookDetails")
@PreAuthorize("isAuthenticated()")
@Tag(name = "Book-Details", description = "Operations about book details")
public class BookDetailsController {
    private final BookDetailsService bookDetailsService;

    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService){
        this.bookDetailsService = bookDetailsService;
    }

    @GetMapping("/allDetails")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all book details"),
            @ApiResponse(responseCode = "404", description = "Book details not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public List<BookDetailsEntity> getAll(){
        /**
         * Method to get all book details.
         * @return List of all book details.
         */
        return bookDetailsService.getAll();
    }

    @GetMapping("/{id}")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found book details by id"),
            @ApiResponse(responseCode = "404", description = "Book details not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public BookDetailsEntity getOne(@PathVariable long id){
        /**
         * Method to get one book details by id.
         * @param id - id of book details.
         * @return Book details with this id.
         */
        return bookDetailsService.getOne(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_W')")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created book details"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<BookDetailsEntity> create(@RequestBody BookDetailsEntity bookDetails){
        /**
         * Method to create new book details.
         * @param bookDetails - book details to create.
         * @return Created book details.
         */
        var newDetails = bookDetailsService.create(bookDetails);
        return new ResponseEntity<>(newDetails, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_W')")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book details deleted"),
            @ApiResponse(responseCode = "404", description = "Book details not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public void delete(@PathVariable long id){
        /**
         * Method to delete book details by id.
         * @param id - id of book details.
         */
        bookDetailsService.delete(id);
    }
}
