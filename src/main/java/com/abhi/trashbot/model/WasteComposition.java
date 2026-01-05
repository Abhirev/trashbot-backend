package com.abhi.trashbot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "waste_composition")
public class WasteComposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bin_id", nullable = false)
    private SmartBin smartBin;

    private double plastic; 
    private double metal;   
    private double glass;   
    private double others;  
	public double getPlastic() {
		return plastic;
	}
	public SmartBin getSmartBin() {
		return smartBin;
	}
	public void setSmartBin(SmartBin smartBin) {
		this.smartBin = smartBin;
	}
	public void setPlastic(double plastic) {
		this.plastic = plastic;
	}
	public double getMetal() {
		return metal;
	}
	public void setMetal(double metal) {
		this.metal = metal;
	}
	public double getGlass() {
		return glass;
	}
	public void setGlass(double glass) {
		this.glass = glass;
	}
	public double getOthers() {
		return others;
	}
	public void setOthers(double others) {
		this.others = others;
	}

}
