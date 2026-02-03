package com.bank.bankapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private Double interestRate;
    private int tenureMonths;
    private String status;
    private LocalDate appliedDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Loan() {}

    public Loan(Double amount, Double interestRate,
                int tenureMonths, String status, LocalDate appliedDate,
                Account account) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.tenureMonths = tenureMonths;
        this.status = status;
        this.appliedDate = appliedDate;
        this.account = account;
    }

    public Long getId() { return id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public Double getInterestRate() { return interestRate; }
    public void setInterestRate(Double interestRate) { this.interestRate = interestRate; }

    public int getTenureMonths() { return tenureMonths; }
    public void setTenureMonths(int tenureMonths) { this.tenureMonths = tenureMonths; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDate appliedDate) { this.appliedDate = appliedDate; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }
}
