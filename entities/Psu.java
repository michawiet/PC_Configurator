package com.pcc.pc_configurator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Psu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private float tier;
    private String formFactor;
    private String efficiencyRating;
    private int wattage;
    private String modular;
    private long productFK;

    public Psu(long id, float tier, String formFactor, String efficiencyRating, int wattage, String modular, long productFK) {
        this.id = id;
        this.tier = tier;
        this.formFactor = formFactor;
        this.efficiencyRating = efficiencyRating;
        this.wattage = wattage;
        this.modular = modular;
        this.productFK = productFK;
    }

    public Psu() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getTier() {
        return tier;
    }

    public void setTier(float tier) {
        this.tier = tier;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getEfficiencyRating() {
        return efficiencyRating;
    }

    public void setEfficiencyRating(String efficiencyRating) {
        this.efficiencyRating = efficiencyRating;
    }

    public int getWattage() {
        return wattage;
    }

    public void setWattage(int wattage) {
        this.wattage = wattage;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }

    public long getProductFK() {
        return productFK;
    }

    public void setProductFK(long productFK) {
        this.productFK = productFK;
    }

    @Override
    public String toString() {
        return "Psu{" +
                "id=" + id +
                ", tier=" + tier +
                ", formFactor='" + formFactor + '\'' +
                ", efficiencyRating='" + efficiencyRating + '\'' +
                ", wattage=" + wattage +
                ", modular='" + modular + '\'' +
                ", productFK=" + productFK +
                '}';
    }
}
