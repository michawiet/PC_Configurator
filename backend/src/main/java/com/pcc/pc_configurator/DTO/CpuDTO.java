package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.Views;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CpuDTO {
    private long id;
    @JsonView(Views.Normal.class)
    private String socket;
    @JsonView(Views.Normal.class)
    private int cores;
    @JsonView(Views.Normal.class)
    private boolean smt;
    @JsonView(Views.Normal.class)
    private boolean integratedGPU;
    @JsonView(Views.Normal.class)
    private int tdpW;
    @JsonView(Views.Normal.class)
    private int stPref;
    @JsonView(Views.Normal.class)
    private int mtPref;
    @JsonView(Views.Normal.class)
    private float coreClock;
    @JsonView(Views.Normal.class)
    private float boostClock;
    private long productFK;

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
        return "CpuDTO{" +
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
