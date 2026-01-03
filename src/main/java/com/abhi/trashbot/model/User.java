package com.abhi.trashbot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String institution;
    private String location;

    // Constructors
    public User() {}

    public User(String name, String email, String password,
                String institution, String location) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.institution = institution;
        this.location = location;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
