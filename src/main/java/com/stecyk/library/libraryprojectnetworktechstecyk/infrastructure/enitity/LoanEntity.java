package com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.Enums.LoanStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name = "loans", schema = "library")
public class LoanEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "loan_id")
    @Schema(description = "Loan id", example = "1")
    private long loan_id;

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

    @Basic
    @Column(name = "loan_date")
    @Schema(description = "Loan date", example = "2021-01-01")
    @NotBlank(message = "Loan date is mandatory")
    private Date loan_date;

    @Basic
    @Column(name = "due_date")
    @Schema(description = "Due date", example = "2021-01-08")
    @NotBlank(message = "Due date is mandatory")
    private Date due_date;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_status")
    @Schema(description = "Loan status", example = "LOAN_DUE/LOAN_DONE")
    private LoanStatus loan_status;

    public long getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(long loan_id) {
        this.loan_id = loan_id;
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

    public Date getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(Date loan_date) {
        this.loan_date = loan_date;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    @Basic
    @Column(name = "return_date")
    @Schema(description = "Return date", example = "2021-01-08")
    @NotBlank(message = "Return date is mandatory")
    private Date return_date;
}
