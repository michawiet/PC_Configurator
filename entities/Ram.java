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
    private int modulesCount;
    private int moduleCapacity;
    private float fwLatencyNs;
    private int cl;
    private long productFK;

    public Ram(long id, int speed, int modulesCount, int moduleCapacity, float fwLatencyNs, int cl, long productFK) {
        this.id = id;
        this.speed = speed;
        this.modulesCount = modulesCount;
        this.moduleCapacity = moduleCapacity;
        this.fwLatencyNs = fwLatencyNs;
        this.cl = cl;
        this.productFK = productFK;
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

    public int getModulesCount() {
        return modulesCount;
    }

    public void setModulesCount(int modulesCount) {
        this.modulesCount = modulesCount;
    }

    public int getModuleCapacity() {
        return moduleCapacity;
    }

    public void setModuleCapacity(int moduleCapacity) {
        this.moduleCapacity = moduleCapacity;
    }

    public float getFwLatencyNs() {
        return fwLatencyNs;
    }

    public void setFwLatencyNs(float fwLatencyNs) {
        this.fwLatencyNs = fwLatencyNs;
    }

    public int getCl() {
        return cl;
    }

    public void setCl(int cl) {
        this.cl = cl;
    }

    public long getProductFK() {
        return productFK;
    }

    public void setProductFK(long productFK) {
        this.productFK = productFK;
    }

    @Override
    public String toString() {
        return "Ram{" +
                "id=" + id +
                ", speed=" + speed +
                ", modulesCount=" + modulesCount +
                ", moduleCapacity=" + moduleCapacity +
                ", fwLatencyNs=" + fwLatencyNs +
                ", cl=" + cl +
                ", productFK=" + productFK +
                '}';
    }
}
