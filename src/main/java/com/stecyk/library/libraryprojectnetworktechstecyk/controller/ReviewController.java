package com.stecyk.library.libraryprojectnetworktechstecyk.controller;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.BookEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.ReviewEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService)
    {
     this.reviewService = reviewService;
    }

    @GetMapping("/allBooks")
    public List<ReviewEntity> getAllReviews(){
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    public ReviewEntity getOne(@PathVariable long id){
        return reviewService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<ReviewEntity> create(@RequestBody ReviewEntity entity){
        var newReview = reviewService.create(entity);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        reviewService.delete(id);
    }
}
