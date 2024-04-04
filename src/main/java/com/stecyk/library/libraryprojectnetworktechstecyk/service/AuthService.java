package com.stecyk.library.libraryprojectnetworktechstecyk.service;

import com.stecyk.library.libraryprojectnetworktechstecyk.controller.Errors.UserAlreadyExistsError;
import com.stecyk.library.libraryprojectnetworktechstecyk.controller.Errors.WrongPasswordError;
import com.stecyk.library.libraryprojectnetworktechstecyk.controller.SecurityController.*;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.AuthEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.UserEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.UserRole;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository.AuthRepository;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JWTService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public RegisterResponseDTO register(RegisterDTO dto) {
        Optional<AuthEntity> existingAuth = authRepository.findByUsername(dto.getUsername());
        if(existingAuth.isPresent()){
            throw UserAlreadyExistsError.create(dto.getUsername());
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        UserEntity createdUser = userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        authEntity.setUsername(dto.getUsername());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(createdUser);

         authRepository.save(authEntity);

        return new RegisterResponseDTO(userEntity.getUser_id(), authEntity.getUsername(), authEntity.getRole());
    }


    public LoginResponseDTO login(LoginDTO dto){
        AuthEntity authEntity = authRepository.findByUsername(dto.getUsername()).orElseThrow(RuntimeException::new);

        if (!authEntity.getPassword().matches(passwordEncoder.encode(dto.getPassword()))){
            throw WrongPasswordError.create();
        }
        String token = jwtService.generateToken(authEntity);


        return new LoginResponseDTO(token);
    }
}
