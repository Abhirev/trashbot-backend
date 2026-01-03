package com.abhi.trashbot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private double balance;

    // Constructors
    public Wallet() {}

    public Wallet(User user, double balance) {
        this.user = user;
        this.balance = balance;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
