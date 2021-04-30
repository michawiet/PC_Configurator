package com.pcc.pc_configurator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int capacityGB;
    private int tier;
    private String type;
    private String formFactor;
    private String interface_;
    private long productFK;

    public Storage(long id, int capacityGB, int tier, String type, String formFactor, String interface_, long productFK) {
        this.id = id;
        this.capacityGB = capacityGB;
        this.tier = tier;
        this.type = type;
        this.formFactor = formFactor;
        this.interface_ = interface_;
        this.productFK = productFK;
    }

    public Storage() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapacityGB() {
        return capacityGB;
    }

    public void setCapacityGB(int capacityGB) {
        this.capacityGB = capacityGB;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getInterface_() {
        return interface_;
    }

    public void setInterface_(String interface_) {
        this.interface_ = interface_;
    }

    public long getProductFK() {
        return productFK;
    }

    public void setProductFK(long productFK) {
        this.productFK = productFK;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", capacityGB=" + capacityGB +
                ", tier=" + tier +
                ", type='" + type + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", interface_='" + interface_ + '\'' +
                ", productFK=" + productFK +
                '}';
    }
}
