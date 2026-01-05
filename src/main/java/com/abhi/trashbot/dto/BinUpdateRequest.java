package com.abhi.trashbot.dto;

public class BinUpdateRequest {

    private String deviceId;
    private int medicalFill;
    private int ewasteFill;
    private int recyclableFill;

    public BinUpdateRequest() {}

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public int getMedicalFill() { return medicalFill; }
    public void setMedicalFill(int medicalFill) { this.medicalFill = medicalFill; }

    public int getEwasteFill() { return ewasteFill; }
    public void setEwasteFill(int ewasteFill) { this.ewasteFill = ewasteFill; }

    public int getRecyclableFill() { return recyclableFill; }
    public void setRecyclableFill(int recyclableFill) { this.recyclableFill = recyclableFill; }
}