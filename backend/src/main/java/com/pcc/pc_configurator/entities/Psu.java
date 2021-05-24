package com.pcc.pc_configurator.entities;

import javax.persistence.*;

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
    @OneToOne
    @JoinColumn(name = "product_FK", referencedColumnName = "id")
    private Product product;

    public Psu(float tier, String form_Factor, String efficiency_Rating, int wattage, String modular, Product product) {
        this.tier = tier;
        this.form_Factor = form_Factor;
        this.efficiency_Rating = efficiency_Rating;
        this.wattage = wattage;
        this.modular = modular;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
