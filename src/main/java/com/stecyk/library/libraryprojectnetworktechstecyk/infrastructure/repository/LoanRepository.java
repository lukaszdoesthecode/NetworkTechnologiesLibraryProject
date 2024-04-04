package com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
}
