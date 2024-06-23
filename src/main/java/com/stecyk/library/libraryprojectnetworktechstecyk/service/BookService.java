package com.stecyk.library.libraryprojectnetworktechstecyk.service;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.BookEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getAll() {
        return bookRepository.findAll();
    }

    public BookEntity getOne(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public BookEntity create(BookEntity book) {
        return bookRepository.save(book);
    }

    public void delete(long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }

}
