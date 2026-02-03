package com.bank.bankapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bank.bankapp.dto.AccountRequest;
import com.bank.bankapp.dto.AccountResponse;
import com.bank.bankapp.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    // CREATE ACCOUNT
    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // DEPOSIT
    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountResponse> deposit(
            @PathVariable Long id,
            @RequestParam double amount) {

        return ResponseEntity.ok(service.deposit(id, amount));
    }

    // WITHDRAW
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(
            @PathVariable Long id,
            @RequestParam double amount) {

        return ResponseEntity.ok(service.withdraw(id, amount));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Account Deleted");
    }

    // TRANSFER
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestParam Long fromId,
            @RequestParam Long toId,
            @RequestParam double amount) {

        return ResponseEntity.ok(service.transfer(fromId, toId, amount));
    }

    @PutMapping("/{id}/role/{roleId}")
    public String updateRole(@PathVariable Long id,@PathVariable Long roleId) {
        service.updateRole(id, roleId);
        return "Role Updated";
    }

}
