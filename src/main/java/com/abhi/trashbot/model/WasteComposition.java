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

    private int plastic;
    private int metal;
    private int glass;
    private int others;
	public int getPlastic() {
		return plastic;
	}
	public SmartBin getSmartBin() {
		return smartBin;
	}
	public void setSmartBin(SmartBin smartBin) {
		this.smartBin = smartBin;
	}
	public void setPlastic(int plastic) {
		this.plastic = plastic;
	}
	public int getMetal() {
		return metal;
	}
	public void setMetal(int metal) {
		this.metal = metal;
	}
	public int getGlass() {
		return glass;
	}
	public void setGlass(int glass) {
		this.glass = glass;
	}
	public int getOthers() {
		return others;
	}
	public void setOthers(int others) {
		this.others = others;
	}

}
