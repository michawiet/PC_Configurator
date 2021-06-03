package com.pcc.pc_configurator.Entities;

import javax.persistence.*;

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
    @OneToOne
    @JoinColumn(name = "product_FK", referencedColumnName = "id")
    private Product product;

    public Storage(int capacity_GB, int tier, String type, String form_Factor, String storage_interface, Product product) {
        this.capacity_GB = capacity_GB;
        this.tier = tier;
        this.type = type;
        this.form_Factor = form_Factor;
        this.storage_interface = storage_interface;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
