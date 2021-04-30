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
    private float noiseLevelDB;
    private boolean isAir;
    private boolean isWorkstation;
    private long productFK;

    public Cooler(long id, int tier, float noiseLevelDB, boolean isAir, boolean isWorkstation, long productFK) {
        this.id = id;
        this.tier = tier;
        this.noiseLevelDB = noiseLevelDB;
        this.isAir = isAir;
        this.isWorkstation = isWorkstation;
        this.productFK = productFK;
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

    public float getNoiseLevelDB() {
        return noiseLevelDB;
    }

    public void setNoiseLevelDB(float noiseLevelDB) {
        this.noiseLevelDB = noiseLevelDB;
    }

    public boolean isAir() {
        return isAir;
    }

    public void setAir(boolean air) {
        isAir = air;
    }

    public boolean isWorkstation() {
        return isWorkstation;
    }

    public void setWorkstation(boolean workstation) {
        isWorkstation = workstation;
    }

    public long getProductFK() {
        return productFK;
    }

    public void setProductFK(long productFK) {
        this.productFK = productFK;
    }

    @Override
    public String toString() {
        return "Cooler{" +
                "id=" + id +
                ", tier=" + tier +
                ", noiseLevelDB=" + noiseLevelDB +
                ", isAir=" + isAir +
                ", isWorkstation=" + isWorkstation +
                ", productFK=" + productFK +
                '}';
    }
}
