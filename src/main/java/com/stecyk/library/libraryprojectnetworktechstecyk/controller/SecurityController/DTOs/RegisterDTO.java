package com.stecyk.library.libraryprojectnetworktechstecyk.controller.SecurityController.DTOs;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.Enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class RegisterDTO {
    @Schema(description = "Password", example = "password")
    @NotBlank(message = "Password is mandatory in DTO")
    private String password;
    @Schema(description = "Username", example = "username")
    @NotBlank(message = "Username is mandatory in DTO")
    private String username;
    @Schema(description = "Role", example = "ROLE_U")
    @NotBlank(message = "Role is mandatory")
    private UserRole role;
    @Schema(description = "Email", example = "email")
    @NotBlank(message = "Email is mandatory in DTO")
    private String email;

    public RegisterDTO(String password, String username, UserRole role, String email) {
        this.password = password;
        this.username = username;
        this.role = role;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
