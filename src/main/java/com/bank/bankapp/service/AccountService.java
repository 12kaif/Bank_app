package com.bank.bankapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.bankapp.dto.AccountRequest;
import com.bank.bankapp.dto.AccountResponse;
import com.bank.bankapp.exception.AccountNotFoundException;
import com.bank.bankapp.exception.InsufficientBalanceException;
import com.bank.bankapp.model.Account;
import com.bank.bankapp.repository.AccountRepository;
import com.bank.bankapp.repository.RoleRepository;
import com.bank.bankapp.model.Role;




@Service
public class AccountService {

    private final AccountRepository repo;
    private final TransactionService txService;
    private final RoleRepository roleRepo;


    public AccountService(AccountRepository repo,
                      TransactionService txService,
                      RoleRepository roleRepo) {
    this.repo = repo;
    this.txService = txService;
    this.roleRepo = roleRepo;
}



    // ===== HELPER METHODS =====

    private AccountResponse mapToResponse(Account acc) {

    String roleName = null;

    if (acc.getRole() != null) {
        roleName = acc.getRole().getRoleName();
    }

    return new AccountResponse(
        acc.getId(),
        acc.getName(),
        acc.getBalance(),
        roleName
    );
}



    private Account getEntityById(Long id) {
        return repo.findById(id)
            .orElseThrow(() ->
                new AccountNotFoundException("Account not found with id " + id));
    }

    // ===== CREATE =====
    public AccountResponse create(AccountRequest req) {
        Account acc = new Account();
        acc.setName(req.getName());
        acc.setBalance(req.getBalance());

        Role role = roleRepo.findById(req.getRoleId())
            .orElseThrow(() -> new RuntimeException("Role not found"));

        acc.setRole(role);

        return mapToResponse(repo.save(acc));
    }

    // ===== GET ALL =====
    public List<AccountResponse> getAll() {
        return repo.findAll()
                   .stream()
                   .map(this::mapToResponse)
                   .toList();
    }

    // ===== GET BY ID =====
    public AccountResponse getById(Long id) {
        return mapToResponse(getEntityById(id));
    }

    // ===== DEPOSIT =====
    public AccountResponse deposit(Long id, double amount) {

        Account acc = getEntityById(id);
        acc.setBalance(acc.getBalance() + amount);

        Account saved = repo.save(acc);

        txService.saveTransaction(id, amount, "DEPOSIT");

        return mapToResponse(saved);
    }


    // ===== WITHDRAW =====
    public AccountResponse withdraw(Long id, double amount) {

        Account acc = getEntityById(id);

        if (acc.getBalance() < amount)
            throw new InsufficientBalanceException("Insufficient Balance");

        acc.setBalance(acc.getBalance() - amount);

        Account saved = repo.save(acc);

        txService.saveTransaction(id, amount, "WITHDRAW");

        return mapToResponse(saved);
    }


    // ===== DELETE =====
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new AccountNotFoundException("Account not found with id " + id);
        }
        repo.deleteById(id);
    }

    // ===== TRANSFER =====
@Transactional
public String transfer(Long fromId, Long toId, double amount) {

    if (amount <= 0) {
        throw new IllegalArgumentException("Transfer amount must be positive");
    }

    Account from = getEntityById(fromId);
    Account to = getEntityById(toId);

    if (from.getBalance() < amount) {
        throw new InsufficientBalanceException("Insufficient balance");
    }

    from.setBalance(from.getBalance() - amount);
    to.setBalance(to.getBalance() + amount);

    repo.save(from);
    repo.save(to);

    // ADD THESE TWO LINES
    txService.saveTransaction(fromId, amount, "TRANSFER_OUT");
    txService.saveTransaction(toId, amount, "TRANSFER_IN");

    return "Transfer successful from Account " + fromId + " to " + toId;
}

    public void updateRole(Long accId, Long roleId) {

        Account acc = getEntityById(accId);

        Role role = roleRepo.findById(roleId)
            .orElseThrow(() -> new RuntimeException("Role not found"));

        acc.setRole(role);

        repo.save(acc);
    }



}
