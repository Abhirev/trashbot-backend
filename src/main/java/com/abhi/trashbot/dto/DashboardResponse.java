package com.abhi.trashbot.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class DashboardResponse {

    private int ecoScore;
    private String ecoLevel;
    private int level;
    private double totalSegregatedKg;

    private Map<String, Integer> bins;
    private Map<String, Integer> composition;

    private LocalDateTime lastUpdated;

    public DashboardResponse(int ecoScore,
                             String ecoLevel,
                             int level,
                             double totalSegregatedKg,
                             Map<String, Integer> bins,
                             Map<String, Integer> composition,
                             LocalDateTime lastUpdated) {

        this.ecoScore = ecoScore;
        this.ecoLevel = ecoLevel;
        this.level = level;
        this.totalSegregatedKg = totalSegregatedKg;
        this.bins = bins;
        this.composition = composition;
        this.lastUpdated = lastUpdated;
    }

    // getters (explicit, no Lombok)
    public int getEcoScore() { return ecoScore; }
    public String getEcoLevel() { return ecoLevel; }
    public int getLevel() { return level; }
    public double getTotalSegregatedKg() { return totalSegregatedKg; }
    public Map<String, Integer> getBins() { return bins; }
    public Map<String, Integer> getComposition() { return composition; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
}
