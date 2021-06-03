package com.pcc.pc_configurator.Entities;

import javax.persistence.*;

@Entity
public class Cooler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int tier;
    private float noise_Level_DB;
    private boolean is_Air;
    private boolean is_Workstation;
    @OneToOne
    @JoinColumn(name = "product_FK", referencedColumnName = "id")
    private Product product;

    public Cooler(int tier, float noise_Level_DB, boolean is_Air, boolean is_Workstation, Product product) {
        this.tier = tier;
        this.noise_Level_DB = noise_Level_DB;
        this.is_Air = is_Air;
        this.is_Workstation = is_Workstation;
        this.product = product;
    }

    public Cooler() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public float getNoise_Level_DB() {
        return noise_Level_DB;
    }

    public void setNoise_Level_DB(float noise_Level_DB) {
        this.noise_Level_DB = noise_Level_DB;
    }

    public boolean isIs_Air() {
        return is_Air;
    }

    public void setIs_Air(boolean is_Air) {
        this.is_Air = is_Air;
    }

    public boolean isIs_Workstation() {
        return is_Workstation;
    }

    public void setIs_Workstation(boolean is_Workstation) {
        this.is_Workstation = is_Workstation;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
