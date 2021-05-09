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

    private int capacity_GB;
    private int tier;
    private String type;
    private String form_Factor;
    private String storage_interface;
    private long product_FK;

    public Storage( int capacityGB, int tier, String type, String formFactor, String interface_, long productFK) {
        this.capacity_GB = capacityGB;
        this.tier = tier;
        this.type = type;
        this.form_Factor = formFactor;
        this.storage_interface = interface_;
        this.product_FK = productFK;
    }

    public Storage() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapacity_GB() {
        return capacity_GB;
    }

    public void setCapacity_GB(int capacityGB) {
        this.capacity_GB = capacityGB;
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

    public String getForm_Factor() {
        return form_Factor;
    }

    public void setForm_Factor(String formFactor) {
        this.form_Factor = formFactor;
    }

    public String getStorage_interface() {
        return storage_interface;
    }

    public void setStorage_interface(String interface_) {
        this.storage_interface = interface_;
    }

    public long getProduct_FK() {
        return product_FK;
    }

    public void setProduct_FK(long productFK) {
        this.product_FK = productFK;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", capacityGB=" + capacity_GB +
                ", tier=" + tier +
                ", type='" + type + '\'' +
                ", formFactor='" + form_Factor + '\'' +
                ", interface_='" + storage_interface + '\'' +
                ", productFK=" + product_FK +
                '}';
    }
}
