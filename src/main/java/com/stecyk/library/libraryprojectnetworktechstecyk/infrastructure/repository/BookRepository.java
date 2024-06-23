package com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
