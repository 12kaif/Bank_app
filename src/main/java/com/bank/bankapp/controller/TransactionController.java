package com.bank.bankapp.controller;

import com.bank.bankapp.model.Transaction;
import com.bank.bankapp.service.TransactionService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService txService;

    public TransactionController(TransactionService txService) {
        this.txService = txService;
    }

    @GetMapping("/{accountId}")
    public List<Transaction> getTransactions(
            @PathVariable Long accountId) {

        return txService.getTransactionsByAccount(accountId);
    }
}
