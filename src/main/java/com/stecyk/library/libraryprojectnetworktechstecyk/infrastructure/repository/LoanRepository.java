package com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.LoanEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    @Query("SELECT l FROM LoanEntity l WHERE l.user_id = :userId")
    List<LoanEntity> findByUserId(@Param("userId") long userId);
}
