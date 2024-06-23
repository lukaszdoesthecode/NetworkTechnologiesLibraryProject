package com.stecyk.library.libraryprojectnetworktechstecyk.controller;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.BookEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.ReviewEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.service.ReviewService;
import io.swagger.v3.oas.annotations.media.Content;
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
@RequestMapping("/api/reviews")
@Tag(name = "Review", description = "Review data represented in the system")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService)
    {
     this.reviewService = reviewService;
    }

    @GetMapping("/allBooks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All reviews returned"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public List<ReviewEntity> getAllReviews(){
        /**
         * Method to get all reviews.
         * @return List of all reviews.
         */
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review returned"),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ReviewEntity getOne(@PathVariable long id){
        /**
         * Method to get one review by id.
         * @param id - id of review.
         * @return Review with this id.
         */
        return reviewService.getOne(id);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Review created"),
            @ApiResponse(responseCode = "400", description = "Creation failed", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<ReviewEntity> create(@RequestBody ReviewEntity entity){
        /**
         * Method to create new review.
         * @param entity - review to create.
         * @return Created review.
         */
        var newReview = reviewService.create(entity);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review deleted"),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })


    public void delete(@PathVariable long id){
        /**
         * Method to delete review by id.
         * @param id - id of review.
         */
        reviewService.delete(id);
    }
}
