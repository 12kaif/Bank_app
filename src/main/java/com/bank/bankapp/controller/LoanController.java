package com.bank.bankapp.controller;

import com.bank.bankapp.dto.LoanDTO;
import com.bank.bankapp.model.Loan;
import com.bank.bankapp.service.LoanService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @PostMapping("/apply")
    public Loan applyLoan(@RequestBody LoanDTO dto) {
        return service.applyLoan(dto);
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return service.getAllLoans();
    }

    @PutMapping("/approve/{id}")
    public Loan approveLoan(@PathVariable Long id) {
        return service.approveLoan(id);
    }

    @PutMapping("/reject/{id}")
    public Loan rejectLoan(@PathVariable Long id) {
        return service.rejectLoan(id);
    }
}
