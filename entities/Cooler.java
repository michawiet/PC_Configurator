package com.pcc.pc_configurator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cooler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int tier;
    private float noise_Level_DB;
    private boolean is_Air;
    private boolean is_Workstation;
    private long product_FK;

    public Cooler(int tier, float noiseLevelDB, boolean isAir, boolean isWorkstation, long productFK) {
        this.tier = tier;
        this.noise_Level_DB = noiseLevelDB;
        this.is_Air = isAir;
        this.is_Workstation = isWorkstation;
        this.product_FK = productFK;
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

    public void setNoise_Level_DB(float noiseLevelDB) {
        this.noise_Level_DB = noiseLevelDB;
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

    public long getProduct_FK() {
        return product_FK;
    }

    public void setProduct_FK(long productFK) {
        this.product_FK = productFK;
    }

    @Override
    public String toString() {
        return "Cooler{" +
                "id=" + id +
                ", tier=" + tier +
                ", noiseLevelDB=" + noise_Level_DB +
                ", isAir=" + is_Air +
                ", isWorkstation=" + is_Workstation +
                ", productFK=" + product_FK +
                '}';
    }
}
