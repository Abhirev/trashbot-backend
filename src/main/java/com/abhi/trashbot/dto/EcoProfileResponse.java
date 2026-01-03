package com.abhi.trashbot.dto;

public class EcoProfileResponse {

    private double ecoScore;
    private String ecoLevel;

    public EcoProfileResponse(double ecoScore, String ecoLevel) {
        this.ecoScore = ecoScore;
        this.ecoLevel = ecoLevel;
    }

    public double getEcoScore() {
        return ecoScore;
    }

    public String getEcoLevel() {
        return ecoLevel;
    }
}
