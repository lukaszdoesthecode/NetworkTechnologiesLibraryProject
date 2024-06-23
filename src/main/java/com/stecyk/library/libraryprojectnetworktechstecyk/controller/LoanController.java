package com.stecyk.library.libraryprojectnetworktechstecyk.controller;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.LoanEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.UserEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository.LoanRepository;
import com.stecyk.library.libraryprojectnetworktechstecyk.service.LoanService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@Tag(name = "Loan", description = "Loan")
public class LoanController {
    private final LoanService loanService;
    private final LoanRepository loanRepository;

    @Autowired
    public LoanController(LoanService loanService, LoanRepository loanRepository) {
        this.loanService = loanService;
        this.loanRepository = loanRepository;
    }

    @GetMapping("/allLoans")
    //@PreAuthorize("hasRole('ROLE_W')")
    //@SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All loans returned"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public List<LoanEntity> getAllLoans(){
        /**
         * Method to get all loans.
         * @return List of all loans.
         */
        return loanService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_W')")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan returned"),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public LoanEntity getOne(@PathVariable long id){
        /**
         * Method to get one loan by id.
         * @param id - id of loan.
         * @return Loan with this id.
         */
        return loanService.getOne(id);
    }


    @GetMapping("/myLoans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All loans of current user returned"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public List<LoanEntity> myLoans(){
        /**
         * Method to get all loans of current user.
         * @return List of all loans of current user.
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserEntity) {
            UserEntity user = (UserEntity) authentication.getPrincipal();
            return loanRepository.findByUserId(user.getUser_id());
        }
        throw new IllegalStateException("Current user not authenticated or user details not found");
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_W')")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Loan created"),
            @ApiResponse(responseCode = "400", description = "Creation failed", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    public ResponseEntity<LoanEntity> create(@RequestBody LoanEntity loan){
        /**
         * Method to create new loan.
         * @param loan - loan to create.
         * @return Created loan.
         */
        var newLoan = loanService.create(loan);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan deleted"),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_W')")
    public void delete(@PathVariable long id){
        /**
         * Method to delete loan by id.
         * @param id - id of loan.
         */
        loanService.delete(id);
    }
}
