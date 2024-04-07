package com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "books", schema = "library")
public class BookEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @Schema(description = "Book id", example = "1")
    private long id;

    @Basic
    @Column(name = "isbn", unique = true)
    @Schema(description = "Book ISBN", example = "978-3-16-148410-0")
    @NotBlank(message = "ISBN is mandatory")
    private String isbn;

    @Basic
    @Column(name = "title")
    @Schema(description = "Book title", example = "The Book")
    @NotBlank(message = "Title is mandatory")
    private String title;

    @Basic
    @Column (name = "author")
    @Schema(description = "Book author", example = "John Doe")
    @NotBlank(message = "Author is mandatory")
    private String author;

    @Basic
    @Column (name = "publisher")
    @Schema(description = "Book publisher", example = "Publisher")
    @NotBlank(message = "Publisher is mandatory")
    private String publisher;

    @Basic
    @Column (name = "publication_year")
    @Schema(description = "Book publication year", example = "2021")
    @NotBlank(message = "Publication year is mandatory")
    private int publication_year;

    @Basic
    @Column (name = "available_copies")
    @Schema(description = "Book available copies", example = "5")
    private int available_copies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public int getAvailable_copies() {
        return available_copies;
    }

    public void setAvailable_copies(int available_copies) {
        this.available_copies = available_copies;
    }
}
