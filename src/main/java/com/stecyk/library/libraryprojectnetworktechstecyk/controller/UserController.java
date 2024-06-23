package com.stecyk.library.libraryprojectnetworktechstecyk.controller;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.UserEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.service.BookService;
import com.stecyk.library.libraryprojectnetworktechstecyk.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "User", description = "User data represented in the system")
public class UserController {
    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/allUsers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users returned"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public List<UserEntity> getAllUsers() {
        /**
         * Method to get all users.
         * @return List of all users.
         */
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public UserEntity getOne(@PathVariable long id) {
        /**
         * Method to get one user by id.
         * @param id - id of user.
         * @return User with this id.
         */
        UserEntity user = userService.getOne(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id);
        }
        return user;
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Creation failed", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity user) {
        /**
         * Method to create new user.
         * @param user - user to create.
         * @return Created user.
         */
        var newUser = userService.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public void delete(@PathVariable long id) {
        /**
         * Method to delete user by id.
         * @param id - id of user.
         */
        UserEntity user = userService.getOne(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + id);
        }
        userService.delete(id);
    }

    @GetMapping("/getUserIdByName/{name}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User ID returned"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<Long> getUserIdByName(@PathVariable String name) {
        /**
         * Method to get user ID by name.
         * @param name - name of the user.
         * @return User ID.
         */
        long userId = userService.getUserIdByName(name);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @GetMapping("/getUserRoleByName/{name}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User role returned"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<String> getUserRoleByName(@PathVariable String name) {
        /**
         * Method to get user role by name.
         * @param name - name of the user.
         * @return User role.
         */
        String userRole = userService.getUserRoleByName(name);
        return new ResponseEntity<>(userRole, HttpStatus.OK);
    }
}
