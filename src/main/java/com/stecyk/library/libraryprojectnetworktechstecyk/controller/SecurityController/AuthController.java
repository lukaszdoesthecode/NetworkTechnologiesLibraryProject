package com.stecyk.library.libraryprojectnetworktechstecyk.controller.SecurityController;

import com.stecyk.library.libraryprojectnetworktechstecyk.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    private final AuthService authService;


    @PostMapping("/register")
    //@PreAuthorize("hasRole('ROLE_W')")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO requestBody){
        RegisterResponseDTO dto = authService.register(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO requestBody){
        LoginResponseDTO dto = authService.login(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}