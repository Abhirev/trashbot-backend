package com.abhi.trashbot.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "bin_status")
public class BinStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bin_id", nullable = false, unique = true)
    private SmartBin smartBin;

    private int medicalFill;      // %
    private int ewasteFill;       // %
    private int recyclableFill;  // % (UPDATED)

    private LocalDateTime lastUpdated;

    // Constructors
    public BinStatus() {}

    public BinStatus(SmartBin smartBin, int medicalFill,
                     int ewasteFill, int recyclableFill) {
        this.smartBin = smartBin;
        this.medicalFill = medicalFill;
        this.ewasteFill = ewasteFill;
        this.recyclableFill = recyclableFill;
        this.lastUpdated = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }

    public SmartBin getSmartBin() { return smartBin; }
    public void setSmartBin(SmartBin smartBin) { this.smartBin = smartBin; }

    public int getMedicalFill() { return medicalFill; }
    public void setMedicalFill(int medicalFill) { this.medicalFill = medicalFill; }

    public int getEwasteFill() { return ewasteFill; }
    public void setEwasteFill(int ewasteFill) { this.ewasteFill = ewasteFill; }

    public int getRecyclableFill() { return recyclableFill; }
    public void setRecyclableFill(int recyclableFill) { this.recyclableFill = recyclableFill; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
