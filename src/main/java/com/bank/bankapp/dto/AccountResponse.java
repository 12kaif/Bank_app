package com.bank.bankapp.dto;

public class AccountResponse {

    private Long id;
    private String name;
    private double balance;
    private String roleName;


    public AccountResponse(Long id, String name, double balance, String roleName) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getRoleName() {
        return roleName;
    }
}
