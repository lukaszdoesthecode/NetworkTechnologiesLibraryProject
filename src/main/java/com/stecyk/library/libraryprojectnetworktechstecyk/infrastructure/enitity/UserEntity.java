package com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.Enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users", schema = "library")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @Schema(description = "User id", example = "1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    @Basic
    @NotBlank(message = "Email is mandatory")
    @Schema(description = "User email", example = "mail@example.com")
    @Column(name = "email")
    private String email;

    @Basic
    @NotBlank(message = "Name is mandatory")
    @Schema(description = "User name", example = "John Doe")
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Schema(description = "User role", example = "ROLE_U/ROLE_W")
    @NotBlank(message = "User type is mandatory")
    @Column(name = "user_type")
    private UserRole userRole;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AuthEntity auth;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
