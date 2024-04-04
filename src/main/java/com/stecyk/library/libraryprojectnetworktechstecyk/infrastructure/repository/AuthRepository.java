package com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    Optional<AuthEntity> findByUsername(String username);
}
