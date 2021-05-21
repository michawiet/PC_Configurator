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
    private String socket;
    private String form_Factor;
    private int memory_Slots;
    private int memory_Max_GB;
    private long product_FK;

    public Motherboard(float tier, String chipset, String socket, String form_Factor, int memory_Slots, int memory_Max_GB) {
        this.tier = tier;
        this.chipset = chipset;
        this.socket = socket;
        this.form_Factor = form_Factor;
        this.memory_Slots = memory_Slots;
        this.memory_Max_GB = memory_Max_GB;
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

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getForm_Factor() {
        return form_Factor;
    }

    public void setForm_Factor(String form_Factor) {
        this.form_Factor = form_Factor;
    }

    public int getMemory_Slots() {
        return memory_Slots;
    }

    public void setMemory_Slots(int memory_Slots) {
        this.memory_Slots = memory_Slots;
    }

    public int getMemory_Max_GB() {
        return memory_Max_GB;
    }

    public void setMemory_Max_GB(int memory_Max_GB) {
        this.memory_Max_GB = memory_Max_GB;
    }

    public long getProduct_FK() {
        return product_FK;
    }

    public void setProduct_FK(long product_FK) {
        this.product_FK = product_FK;
    }

    @Override
    public String toString() {
        return "Motherboard{" +
                "id=" + id +
                ", tier=" + tier +
                ", chipset='" + chipset + '\'' +
                ", formFactor='" + form_Factor + '\'' +
                ", memorySlots=" + memory_Slots +
                ", memoryMaxGB=" + memory_Max_GB +
                ", productFK=" + product_FK +
                '}';
    }
}
