package com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
