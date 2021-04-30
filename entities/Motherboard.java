package com.pcc.pc_configurator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Motherboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private float tier;
    private String chipset;
    private String formFactor;
    private int memorySlots;
    private int memoryMaxGB;
    private long productFK;

    public Motherboard(long id, float tier, String chipset, String formFactor, int memorySlots, int memoryMaxGB, long productFK) {
        this.id = id;
        this.tier = tier;
        this.chipset = chipset;
        this.formFactor = formFactor;
        this.memorySlots = memorySlots;
        this.memoryMaxGB = memoryMaxGB;
        this.productFK = productFK;
    }

    public Motherboard() {
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

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public int getMemorySlots() {
        return memorySlots;
    }

    public void setMemorySlots(int memorySlots) {
        this.memorySlots = memorySlots;
    }

    public int getMemoryMaxGB() {
        return memoryMaxGB;
    }

    public void setMemoryMaxGB(int memoryMaxGB) {
        this.memoryMaxGB = memoryMaxGB;
    }

    public long getProductFK() {
        return productFK;
    }

    public void setProductFK(long productFK) {
        this.productFK = productFK;
    }

    @Override
    public String toString() {
        return "Motherboard{" +
                "id=" + id +
                ", tier=" + tier +
                ", chipset='" + chipset + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", memorySlots=" + memorySlots +
                ", memoryMaxGB=" + memoryMaxGB +
                ", productFK=" + productFK +
                '}';
    }
}
