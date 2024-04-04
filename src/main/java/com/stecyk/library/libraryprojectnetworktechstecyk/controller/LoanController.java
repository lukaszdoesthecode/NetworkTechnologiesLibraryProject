package com.stecyk.library.libraryprojectnetworktechstecyk.controller;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.LoanEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@PreAuthorize("hasRole('ROLE_W')")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/allLoans")
    public List<LoanEntity> getAllLoans(){
        return loanService.getAll();
    }

    @GetMapping("/{id}")
    public LoanEntity getOne(@PathVariable long id){
        return loanService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<LoanEntity> create(@RequestBody LoanEntity loan){
        var newLoan = loanService.create(loan);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        loanService.delete(id);
    }
}
