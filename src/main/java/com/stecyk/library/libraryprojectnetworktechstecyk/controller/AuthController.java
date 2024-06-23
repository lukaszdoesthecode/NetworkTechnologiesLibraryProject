package com.stecyk.library.libraryprojectnetworktechstecyk.controller;

import com.stecyk.library.libraryprojectnetworktechstecyk.controller.SecurityController.DTOs.LoginDTO;
import com.stecyk.library.libraryprojectnetworktechstecyk.controller.SecurityController.DTOs.LoginResponseDTO;
import com.stecyk.library.libraryprojectnetworktechstecyk.controller.SecurityController.DTOs.RegisterDTO;
import com.stecyk.library.libraryprojectnetworktechstecyk.controller.SecurityController.DTOs.RegisterResponseDTO;
import com.stecyk.library.libraryprojectnetworktechstecyk.service.AuthService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "User authentication data")
public class AuthController {
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    private final AuthService authService;

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered"),
            @ApiResponse(responseCode = "400", description = "Registration failed", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)

    })
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO requestBody){
        RegisterResponseDTO dto = authService.register(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    @SecurityRequirements
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "User logged in"),
            @ApiResponse(responseCode = "401", description = "Login failed", content = @Content)})
    public ResponseEntity<LoginResponseDTO> login(@Validated @RequestBody LoginDTO requestBody){
        LoginResponseDTO dto = authService.login(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
