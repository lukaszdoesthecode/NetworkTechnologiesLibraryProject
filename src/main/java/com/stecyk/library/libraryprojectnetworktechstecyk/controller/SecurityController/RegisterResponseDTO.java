package com.stecyk.library.libraryprojectnetworktechstecyk.controller.SecurityController;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.UserRole;

public class RegisterResponseDTO {
    private long userID;
    private String username;
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
