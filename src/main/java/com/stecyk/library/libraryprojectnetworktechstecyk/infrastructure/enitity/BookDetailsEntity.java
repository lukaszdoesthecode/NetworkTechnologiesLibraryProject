package com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "details", schema = "library")
public class BookDetailsEntity {
    @Id
    @Column(name = "book_detail_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Book detail id", example = "1")
    private long detailId;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @Schema(description = "Book id", example = "1")
    @NotBlank(message = "Book id is mandatory")
    private BookEntity id;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    @Schema(description = "Book genre", example = "HORROR")
    private BookGenre genre;

    @Column(name = "summary")
    @Schema(description = "Book summary", example = "This is a summary")
    private String summary;

    @Column(name = "cover")
    @Schema(description = "Book cover", example = "This is a cover")
    private String cover;

    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public BookEntity getId() {
        return id;
    }

    public void setId(BookEntity id) {
        this.id = id;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
