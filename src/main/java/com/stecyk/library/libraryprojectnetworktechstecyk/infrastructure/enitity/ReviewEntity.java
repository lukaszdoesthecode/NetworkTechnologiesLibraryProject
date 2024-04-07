package com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name = "reviews", schema = "library")
public class ReviewEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "review_id")
    @Schema(description = "Review id", example = "1")
    private long review_id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @Schema(description = "Book id", example = "1")
    @NotBlank(message = "Book id is mandatory")
    private BookEntity book_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @Schema(description = "User id", example = "1")
    @NotBlank(message = "User id is mandatory")
    private UserEntity user_id;

    public long getReview_id() {
        return review_id;
    }

    public void setReview_id(long review_id) {
        this.review_id = review_id;
    }

    public BookEntity getBook_id() {
        return book_id;
    }

    public void setBook_id(BookEntity book_id) {
        this.book_id = book_id;
    }

    public UserEntity getUser_id() {
        return user_id;
    }

    public void setUser_id(UserEntity user_id) {
        this.user_id = user_id;
    }

    public Rates getRate() {
        return rate;
    }

    public void setRate(Rates rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReview_date() {
        return review_date;
    }

    public void setReview_date(Date review_date) {
        this.review_date = review_date;
    }

    @Basic
    @Column (name = "rate")
    @Schema(description = "Review rate", example = "5 out of 10")
    @NotBlank(message = "Rate is mandatory")
    private Rates rate;

    @Basic
    @Column(name = "comment")
    @Schema(description = "Review comment", example = "Great book!")
    private String comment;

    @Basic
    @Column (name = "review_date")
    @Schema(description = "Review date", example = "2021-12-01")
    @NotBlank(message = "Review date is mandatory")
    private Date review_date;
}
