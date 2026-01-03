package com.abhi.trashbot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "smart_bins")
public class SmartBin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, unique = true)
    private String binCode;   // unique hardware ID

    private String location;

    private String status;    // ACTIVE / OFFLINE

    // Constructors
    public SmartBin() {}

    public SmartBin(User user, String binCode, String location, String status) {
        this.user = user;
        this.binCode = binCode;
        this.location = location;
        this.status = status;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getBinCode() { return binCode; }
    public void setBinCode(String binCode) { this.binCode = binCode; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
