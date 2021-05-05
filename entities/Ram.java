package com.pcc.pc_configurator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int speed;
    private int modules_Count;
    private int module_Capacity;
    private float fw_Latency_Ns;
    private int cl;
    private long product_FK;

    public Ram(int speed, int modulesCount, int moduleCapacity, float fwLatencyNs, int cl, long productFK) {
        this.speed = speed;
        this.modules_Count = modulesCount;
        this.module_Capacity = moduleCapacity;
        this.fw_Latency_Ns = fwLatencyNs;
        this.cl = cl;
        this.product_FK = productFK;
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

    public int getModule_Capacity() {
        return module_Capacity;
    }

    public void setModule_Capacity(int moduleCapacity) {
        this.module_Capacity = moduleCapacity;
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

    public long getProduct_FK() {
        return product_FK;
    }

    public void setProduct_FK(long productFK) {
        this.product_FK = productFK;
    }

    @Override
    public String toString() {
        return "Ram{" +
                "id=" + id +
                ", speed=" + speed +
                ", modulesCount=" + modules_Count +
                ", moduleCapacity=" + module_Capacity +
                ", fwLatencyNs=" + fw_Latency_Ns +
                ", cl=" + cl +
                ", productFK=" + product_FK +
                '}';
    }
}
