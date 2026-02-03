package com.bank.bankapp.model;

import jakarta.persistence.*;


@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    public Account() {}

    public Account(String name, Double balance) {
        this.name = name;
        this.balance = balance;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

}
