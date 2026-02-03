package com.bank.bankapp.dto;

import jakarta.validation.constraints.NotBlank;


public class AccountRequest {

    @NotBlank
    private String name;

    private double balance;

    private Long roleId;


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public Long getRoleId() { return roleId; }   
    public void setRoleId(Long roleId) { this.roleId = roleId; }
}
