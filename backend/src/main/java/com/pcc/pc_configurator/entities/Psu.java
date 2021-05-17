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
    private String form_Factor;
    private String efficiency_Rating;
    private int wattage;
    private String modular;
    private long product_FK;

    public Psu( float tier, String formFactor, String efficiencyRating, int wattage, String modular, long productFK) {
        this.tier = tier;
        this.form_Factor = formFactor;
        this.efficiency_Rating = efficiencyRating;
        this.wattage = wattage;
        this.modular = modular;
        this.product_FK = productFK;
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

    public String getForm_Factor() {
        return form_Factor;
    }

    public void setForm_Factor(String formFactor) {
        this.form_Factor = formFactor;
    }

    public String getEfficiency_Rating() {
        return efficiency_Rating;
    }

    public void setEfficiency_Rating(String efficiencyRating) {
        this.efficiency_Rating = efficiencyRating;
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

    public long getProduct_FK() {
        return product_FK;
    }

    public void setProduct_FK(long productFK) {
        this.product_FK = productFK;
    }

    @Override
    public String toString() {
        return "Psu{" +
                "id=" + id +
                ", tier=" + tier +
                ", formFactor='" + form_Factor + '\'' +
                ", efficiencyRating='" + efficiency_Rating + '\'' +
                ", wattage=" + wattage +
                ", modular='" + modular + '\'' +
                ", productFK=" + product_FK +
                '}';
    }
}
