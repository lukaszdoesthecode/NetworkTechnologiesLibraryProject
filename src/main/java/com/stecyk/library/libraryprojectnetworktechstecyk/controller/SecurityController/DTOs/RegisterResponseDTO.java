package com.stecyk.library.libraryprojectnetworktechstecyk.controller.SecurityController.DTOs;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.Enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class RegisterResponseDTO {
    @Schema(description = "User ID", example = "1")
    private long userID;
    @Schema(description = "Username", example = "username")
    @NotBlank(message = "Username is mandatory in DTO")
    private String username;
    @Schema(description = "Role", example = "ROLE_U")
    @NotBlank(message = "Role is mandatory")
    private UserRole role;


    public RegisterResponseDTO(long userID, String username, UserRole role) {
        this.userID = userID;
        this.username = username;
        this.role = role;
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
    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
