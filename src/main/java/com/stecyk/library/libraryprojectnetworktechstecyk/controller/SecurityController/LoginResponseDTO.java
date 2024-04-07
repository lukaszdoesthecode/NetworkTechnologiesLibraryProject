package com.stecyk.library.libraryprojectnetworktechstecyk.controller.SecurityController;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class LoginResponseDTO {
    @Schema(description = "Token", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VybmFtZSIsImV4cCI6MTYzNjMwNjIwMCwiaWF0IjoxNjM2MzA1NDAwfQ")
    @NotBlank(message = "Token is mandatory in DTO")
    private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
