package com.pcc.pc_configurator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String chipset;
    private int memoryGB;
    private int coreClockMHZ;
    private int boostClockMHZ;
    private float lengthMM;
    private int tdp;
    private int recommendedPsuWatts;
    private int performance;
    private long productFK;

    public Gpu(long id, String chipset, int memoryGB, int coreClockMHZ, int boostClockMHZ, float lengthMM, int tdp, int recommendedPsuWatts, int performance, long productFK) {
        this.id = id;
        this.chipset = chipset;
        this.memoryGB = memoryGB;
        this.coreClockMHZ = coreClockMHZ;
        this.boostClockMHZ = boostClockMHZ;
        this.lengthMM = lengthMM;
        this.tdp = tdp;
        this.recommendedPsuWatts = recommendedPsuWatts;
        this.performance = performance;
        this.productFK = productFK;
    }

    public Gpu() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public int getMemoryGB() {
        return memoryGB;
    }

    public void setMemoryGB(int memoryGB) {
        this.memoryGB = memoryGB;
    }

    public int getCoreClockMHZ() {
        return coreClockMHZ;
    }

    public void setCoreClockMHZ(int coreClockMHZ) {
        this.coreClockMHZ = coreClockMHZ;
    }

    public int getBoostClockMHZ() {
        return boostClockMHZ;
    }

    public void setBoostClockMHZ(int boostClockMHZ) {
        this.boostClockMHZ = boostClockMHZ;
    }

    public float getLengthMM() {
        return lengthMM;
    }

    public void setLengthMM(float lengthMM) {
        this.lengthMM = lengthMM;
    }

    public int getTdp() {
        return tdp;
    }

    public void setTdp(int tdp) {
        this.tdp = tdp;
    }

    public int getRecommendedPsuWatts() {
        return recommendedPsuWatts;
    }

    public void setRecommendedPsuWatts(int recommendedPsuWatts) {
        this.recommendedPsuWatts = recommendedPsuWatts;
    }

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public long getProductFK() {
        return productFK;
    }

    public void setProductFK(long productFK) {
        this.productFK = productFK;
    }

    @Override
    public String toString() {
        return "Gpu{" +
                "id=" + id +
                ", chipset='" + chipset + '\'' +
                ", memoryGB=" + memoryGB +
                ", coreClockMHZ=" + coreClockMHZ +
                ", boostClockMHZ=" + boostClockMHZ +
                ", lengthMM=" + lengthMM +
                ", tdp=" + tdp +
                ", recommendedPsuWatts=" + recommendedPsuWatts +
                ", performance=" + performance +
                ", productFK=" + productFK +
                '}';
    }
}
