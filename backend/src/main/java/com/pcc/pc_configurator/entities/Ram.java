package com.pcc.pc_configurator.entities;

import javax.persistence.*;

@Entity
public class Ram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int speed;
    private int modules_Count;
    private int module_Capacity_Gb;
    private float fw_Latency_Ns;
    private int cl;
    @OneToOne
    @JoinColumn(name = "product_FK", referencedColumnName = "id")
    private Product product;

    public Ram(int speed, int modules_Count, int module_Capacity_Gb, float fw_Latency_Ns, int cl, Product product) {
        this.speed = speed;
        this.modules_Count = modules_Count;
        this.module_Capacity_Gb = module_Capacity_Gb;
        this.fw_Latency_Ns = fw_Latency_Ns;
        this.cl = cl;
        this.product = product;
    }

    public Ram() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getModules_Count() {
        return modules_Count;
    }

    public void setModules_Count(int modulesCount) {
        this.modules_Count = modulesCount;
    }

    public int getModule_Capacity_Gb() {
        return module_Capacity_Gb;
    }

    public void setModule_Capacity_Gb(int moduleCapacity) {
        this.module_Capacity_Gb = moduleCapacity;
    }

    public float getFw_Latency_Ns() {
        return fw_Latency_Ns;
    }

    public void setFw_Latency_Ns(float fwLatencyNs) {
        this.fw_Latency_Ns = fwLatencyNs;
    }

    public int getCl() {
        return cl;
    }

    public void setCl(int cl) {
        this.cl = cl;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
