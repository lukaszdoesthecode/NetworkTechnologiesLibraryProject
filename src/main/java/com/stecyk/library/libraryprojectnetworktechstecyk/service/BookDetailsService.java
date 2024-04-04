package com.stecyk.library.libraryprojectnetworktechstecyk.service;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.BookDetailsEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository.BookDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDetailsService {
    private final BookDetailsRepository bookDetailsRepository;

    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository){
        this.bookDetailsRepository = bookDetailsRepository;
    }

    public List<BookDetailsEntity> getAll(){
        return bookDetailsRepository.findAll();
    }

    public BookDetailsEntity getOne(long id){
        return bookDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("Details not found"));
    }

    public BookDetailsEntity create(BookDetailsEntity bookDetails){
        return bookDetailsRepository.save(bookDetails);
    }

    public void delete(long id){
        if(!bookDetailsRepository.existsById(id)){
            throw new RuntimeException();
        }
        bookDetailsRepository.deleteById(id);
    }
}
