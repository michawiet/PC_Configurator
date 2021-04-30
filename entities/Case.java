package com.pcc.pc_configurator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String powerSupplyStandard;
    private String maxMotherboardSize;
    private String type;
    private String sidePanelWindow;
    private long productFK;

    public Case(long id, String powerSupplyStandard, String maxMotherboardSize, String type, String sidePanelWindow, long productFK) {
        this.id = id;
        this.powerSupplyStandard = powerSupplyStandard;
        this.maxMotherboardSize = maxMotherboardSize;
        this.type = type;
        this.sidePanelWindow = sidePanelWindow;
        this.productFK = productFK;
    }

    public Case() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPowerSupplyStandard() {
        return powerSupplyStandard;
    }

    public void setPowerSupplyStandard(String powerSupplyStandard) {
        this.powerSupplyStandard = powerSupplyStandard;
    }

    public String getMaxMotherboardSize() {
        return maxMotherboardSize;
    }

    public void setMaxMotherboardSize(String maxMotherboardSize) {
        this.maxMotherboardSize = maxMotherboardSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSidePanelWindow() {
        return sidePanelWindow;
    }

    public void setSidePanelWindow(String sidePanelWindow) {
        this.sidePanelWindow = sidePanelWindow;
    }

    public long getProductFK() {
        return productFK;
    }

    public void setProductFK(long productFK) {
        this.productFK = productFK;
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", powerSupplyStandard='" + powerSupplyStandard + '\'' +
                ", maxMotherboardSize='" + maxMotherboardSize + '\'' +
                ", type='" + type + '\'' +
                ", sidePanelWindow='" + sidePanelWindow + '\'' +
                ", productFK=" + productFK +
                '}';
    }
}
