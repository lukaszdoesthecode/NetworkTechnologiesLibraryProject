package com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.Enums.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name= "auth", schema = "library")
public class AuthEntity {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Basic
    @Column (name = "username", unique = true, nullable = false)
    private String username;

    @Basic
    @Column (name = "password", unique = true, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column (name = "user_role", nullable = false)
    private UserRole role;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long user_id) {
        this.userId = user_id;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

}
