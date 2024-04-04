package com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity;

import jakarta.persistence.*;

@Entity
@Table(name = "details", schema = "library")
public class BookDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long detailId; // Assuming you also have an ID for the details entity

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity id;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private BookGenre genre;

    @Column(name = "summary")
    private String summary;

    @Column(name = "cover")
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
