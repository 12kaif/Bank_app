package com.bank.bankapp.service;

import com.bank.bankapp.dto.LoanDTO;
import com.bank.bankapp.model.Loan;

import java.util.List;

public interface LoanService {

    Loan applyLoan(LoanDTO dto);
    List<Loan> getAllLoans();
    Loan approveLoan(Long id);
    Loan rejectLoan(Long id);
}
