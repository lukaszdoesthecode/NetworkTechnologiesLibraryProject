package com.stecyk.library.libraryprojectnetworktechstecyk.controller;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.UserEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("isAuthenticated()")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public List<UserEntity> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserEntity getOne(@PathVariable long id){
        UserEntity user = userService.getOne(id);

        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id);
        }
        return userService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity user){
        var newUser = userService.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        UserEntity user = userService.getOne(id);

        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id);
        }
        userService.delete(id);
    }
}
