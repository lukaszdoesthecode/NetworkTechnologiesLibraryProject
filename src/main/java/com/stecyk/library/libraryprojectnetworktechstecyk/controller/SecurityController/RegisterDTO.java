package com.stecyk.library.libraryprojectnetworktechstecyk.controller.SecurityController;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.UserRole;

public class RegisterDTO {
    private String password;
    private String username;
    private UserRole role;
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
