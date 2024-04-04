package com.stecyk.library.libraryprojectnetworktechstecyk.service;

import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.enitity.LoanEntity;
import com.stecyk.library.libraryprojectnetworktechstecyk.infrastructure.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<LoanEntity> getAll(){
        return loanRepository.findAll();
    }

    public LoanEntity getOne(long id){
        return loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    public LoanEntity create(LoanEntity loan){
        return loanRepository.save(loan);
    }

    public void delete(long id){
        if(!loanRepository.existsById(id)){
            throw new RuntimeException();
        }
        loanRepository.deleteById(id);
    }

}
