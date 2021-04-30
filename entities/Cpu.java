package com.pcc.pc_configurator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String socket;
    private int cores;
    private boolean smt;
    private boolean integratedGPU;
    private int tdpW;
    private int stPref;
    private int mtPref;
    private float coreClock;
    private float boostClock;
    private long productFK;

    public Cpu(long id, String socket, int cores, boolean smt, boolean integratedGPU, int tdpW, int stPref, int mtPref, float coreClock, float boostClock, long productFK) {
        this.id = id;
        this.socket = socket;
        this.cores = cores;
        this.smt = smt;
        this.integratedGPU = integratedGPU;
        this.tdpW = tdpW;
        this.stPref = stPref;
        this.mtPref = mtPref;
        this.coreClock = coreClock;
        this.boostClock = boostClock;
        this.productFK = productFK;
    }

    public Cpu() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public boolean isSmt() {
        return smt;
    }

    public void setSmt(boolean smt) {
        this.smt = smt;
    }

    public boolean isIntegratedGPU() {
        return integratedGPU;
    }

    public void setIntegratedGPU(boolean integratedGPU) {
        this.integratedGPU = integratedGPU;
    }

    public int getTdpW() {
        return tdpW;
    }

    public void setTdpW(int tdpW) {
        this.tdpW = tdpW;
    }

    public int getStPref() {
        return stPref;
    }

    public void setStPref(int stPref) {
        this.stPref = stPref;
    }

    public int getMtPref() {
        return mtPref;
    }

    public void setMtPref(int mtPref) {
        this.mtPref = mtPref;
    }

    public float getCoreClock() {
        return coreClock;
    }

    public void setCoreClock(float coreClock) {
        this.coreClock = coreClock;
    }

    public float getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(float boostClock) {
        this.boostClock = boostClock;
    }

    public long getProductFK() {
        return productFK;
    }

    public void setProductFK(long productFK) {
        this.productFK = productFK;
    }

    @Override
    public String toString() {
        return "Cpu{" +
                "id=" + id +
                ", socket='" + socket + '\'' +
                ", cores=" + cores +
                ", smt=" + smt +
                ", integratedGPU=" + integratedGPU +
                ", tdpW=" + tdpW +
                ", stPref=" + stPref +
                ", mtPref=" + mtPref +
                ", coreClock=" + coreClock +
                ", boostClock=" + boostClock +
                ", productFK=" + productFK +
                '}';
    }
}
