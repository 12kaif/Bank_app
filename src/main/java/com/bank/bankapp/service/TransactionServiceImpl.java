package com.bank.bankapp.service;

import com.bank.bankapp.model.Transaction;
import com.bank.bankapp.repository.TransactionRepository;
 

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repo;

    public TransactionServiceImpl(TransactionRepository repo) {
        this.repo = repo;
    }

    @Override
    public Transaction saveTransaction(Long accountId,
                                       double amount,
                                       String type) {

        Transaction tx = new Transaction();
        tx.setAccountId(accountId);
        tx.setAmount(amount);
        tx.setType(type);
        tx.setDate(LocalDateTime.now());

        return repo.save(tx);
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return repo.findByAccountId(accountId);
    }
}
