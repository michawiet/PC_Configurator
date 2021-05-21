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
    private int memory_GB;
    private int core_Clock_MHZ;
    private int boost_Clock_MHZ;
    private float length_MM;
    private int tdp;
    private int recommended_Psu_Watts;
    private int performance;
    private long product_FK;

    public Gpu(String chipset, int memoryGB, int coreClockMHZ, int boostClockMHZ, float lengthMM, int tdp, int recommendedPsuWatts, int performance, long productFK) {
        this.chipset = chipset;
        this.memory_GB = memoryGB;
        this.core_Clock_MHZ = coreClockMHZ;
        this.boost_Clock_MHZ = boostClockMHZ;
        this.length_MM = lengthMM;
        this.tdp = tdp;
        this.recommended_Psu_Watts = recommendedPsuWatts;
        this.performance = performance;
        this.product_FK = productFK;
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

    public int getMemory_GB() {
        return memory_GB;
    }

    public void setMemory_GB(int memoryGB) {
        this.memory_GB = memoryGB;
    }

    public int getCore_Clock_MHZ() {
        return core_Clock_MHZ;
    }

    public void setCore_Clock_MHZ(int coreClockMHZ) {
        this.core_Clock_MHZ = coreClockMHZ;
    }

    public int getBoost_Clock_MHZ() {
        return boost_Clock_MHZ;
    }

    public void setBoost_Clock_MHZ(int boostClockMHZ) {
        this.boost_Clock_MHZ = boostClockMHZ;
    }

    public float getLength_MM() {
        return length_MM;
    }

    public void setLength_MM(float lengthMM) {
        this.length_MM = lengthMM;
    }

    public int getTdp() {
        return tdp;
    }

    public void setTdp(int tdp) {
        this.tdp = tdp;
    }

    public int getRecommended_Psu_Watts() {
        return recommended_Psu_Watts;
    }

    public void setRecommended_Psu_Watts(int recommendedPsuWatts) {
        this.recommended_Psu_Watts = recommendedPsuWatts;
    }

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public long getProduct_FK() {
        return product_FK;
    }

    public void setProduct_FK(long productFK) {
        this.product_FK = productFK;
    }

    @Override
    public String toString() {
        return "Gpu{" +
                "id=" + id +
                ", chipset='" + chipset + '\'' +
                ", memoryGB=" + memory_GB +
                ", coreClockMHZ=" + core_Clock_MHZ +
                ", boostClockMHZ=" + boost_Clock_MHZ +
                ", lengthMM=" + length_MM +
                ", tdp=" + tdp +
                ", recommendedPsuWatts=" + recommended_Psu_Watts +
                ", performance=" + performance +
                ", productFK=" + product_FK +
                '}';
    }
}
