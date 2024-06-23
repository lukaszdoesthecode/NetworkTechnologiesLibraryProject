package com.stecyk.library.libraryprojectnetworktechstecyk.service;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.Enums.UserRole;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.UserEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity getOne(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity create(UserEntity user) {
        return userRepository.save(user);
    }

    public void delete(long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    public long getUserIdByName(String name) {
        Optional<UserEntity> user = userRepository.findByName(name);
        if (user.isPresent()) {
            return user.get().getUser_id();
        } else {
            throw new RuntimeException("User not found with name: " + name);
        }
    }

    public String getUserRoleByName(String name) {
        Optional<UserEntity> user = userRepository.findByName(name);
        if (user.isPresent()) {
            return user.get().getUserRole().name();
        } else {
            throw new RuntimeException("User not found with name: " + name);
        }
    }
}
