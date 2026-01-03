package com.abhi.trashbot.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private double value;          // ₹ amount
    private String status;         // ISSUED / REDEEMED

    @Column(unique = true)
    private String code;           // Coupon code (TB-XXXXXX)

    private LocalDateTime createdAt;

    // Constructors
    public Coupon() {}

    public Coupon(User user, double value) {
        this.user = user;
        this.value = value;
        this.status = "ISSUED";
        this.createdAt = LocalDateTime.now();
        this.code = generateCode();
    }

    // Generate unique coupon code
    private String generateCode() {
        return "TB-" + UUID.randomUUID()
                .toString()
                .substring(0, 6)
                .toUpperCase();
    }

    // Getters & Setters
    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCode() { return code; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
