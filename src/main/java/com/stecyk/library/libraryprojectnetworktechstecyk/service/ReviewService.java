package com.stecyk.library.libraryprojectnetworktechstecyk.service;


import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.ReviewEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewEntity> getAll(){
        return reviewRepository.findAll();
    }

    public ReviewEntity getOne(long id){
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public ReviewEntity create(ReviewEntity review){
        return reviewRepository.save(review);
    }

    public void delete(long id){
        if(!reviewRepository.existsById(id)){
            throw new RuntimeException();
        }
        reviewRepository.deleteById(id);
    }
}
