package com.bank.bankapp.service;

import com.bank.bankapp.model.Transaction;
import java.util.List;

public interface TransactionService {

    Transaction saveTransaction(Long accountId, double amount, String type);

    List<Transaction> getTransactionsByAccount(Long accountId);
}
