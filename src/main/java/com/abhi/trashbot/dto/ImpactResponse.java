package com.abhi.trashbot.dto;

public class ImpactResponse {

    private double totalSegregated;
    private double medicalWaste;
    private double eWaste;
    private double co2Reduction;

    public ImpactResponse(double totalSegregated, double medicalWaste,
                          double eWaste, double co2Reduction) {
        this.totalSegregated = totalSegregated;
        this.medicalWaste = medicalWaste;
        this.eWaste = eWaste;
        this.co2Reduction = co2Reduction;
    }

    public double getTotalSegregated() { return totalSegregated; }
    public double getMedicalWaste() { return medicalWaste; }
    public double getEWaste() { return eWaste; }
    public double getCo2Reduction() { return co2Reduction; }
}
