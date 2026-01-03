package com.abhi.trashbot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_rewards")
public class UserReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private int points;
    private String level;

    // Constructors
    public UserReward() {}

    public UserReward(User user) {
        this.user = user;
        this.points = 0;
        this.level = "Eco Ninja";
    }

    // Getters & Setters
    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
}
