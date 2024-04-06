package com.stecyk.library.libraryprojectnetworktechstecyk.service;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.UserEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public List<UserEntity> getAll(){return userRepository.findAll();}

    public UserEntity getOne(long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity create(UserEntity user){
        return userRepository.save(user);
    }

    public void delete(long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException();
        }
        userRepository.deleteById(id);
    }
}


