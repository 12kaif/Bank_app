package com.bank.bankapp.service;

import com.bank.bankapp.dto.LoanDTO;
import com.bank.bankapp.model.Account;
import com.bank.bankapp.model.Loan;
import com.bank.bankapp.repository.AccountRepository;
import com.bank.bankapp.repository.LoanRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository repo;
    private final AccountRepository accountRepo;

    public LoanServiceImpl(LoanRepository repo,
                           AccountRepository accountRepo) {
        this.repo = repo;
        this.accountRepo = accountRepo;
    }

    @Override
    public Loan applyLoan(LoanDTO dto) {

        Account acc = accountRepo.findById(dto.getAccountId())
            .orElseThrow(() -> new RuntimeException("Account not found"));

        Loan loan = new Loan();
        loan.setAmount(dto.getAmount());
        loan.setInterestRate(dto.getInterestRate());
        loan.setTenureMonths(dto.getTenureMonths());
        loan.setStatus("PENDING");
        loan.setAppliedDate(LocalDate.now());
        loan.setAccount(acc);

        return repo.save(loan);
    }

    @Override
    public List<Loan> getAllLoans() {
        return repo.findAll();
    }

    @Override
    public Loan approveLoan(Long id) {
        Loan loan = repo.findById(id).orElseThrow();
        loan.setStatus("APPROVED");
        return repo.save(loan);
    }

    @Override
    public Loan rejectLoan(Long id) {
        Loan loan = repo.findById(id).orElseThrow();
        loan.setStatus("REJECTED");
        return repo.save(loan);
    }
}
