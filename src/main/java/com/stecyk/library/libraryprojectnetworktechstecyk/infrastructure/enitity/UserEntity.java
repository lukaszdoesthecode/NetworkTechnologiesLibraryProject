package com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "library")
public class UserEntity {
    @Id
    @Column (name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;


    @Basic
    @Column (name = "email")
    private String email;

    @Basic
    @Column (name = "name")
    private String name;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AuthEntity auth;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity id;

    public BookEntity getId() {
        return id;
    }

    public void setId(BookEntity id) {
        this.id = id;
    }

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
}
