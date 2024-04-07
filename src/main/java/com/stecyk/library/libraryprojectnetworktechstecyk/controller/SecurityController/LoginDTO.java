package com.stecyk.library.libraryprojectnetworktechstecyk.controller.SecurityController;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
    @Schema(description = "Username", example = "username")
    @NotBlank(message = "Username is mandatory in DTO")
    private String username;
    @Schema(description = "Password", example = "password")
    @NotBlank(message = "Password is mandatory in DTO")
    private String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
