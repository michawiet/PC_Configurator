package com.pcc.pc_configurator.entities;

import javax.persistence.*;

@Entity
public class Cpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String socket;
    private int cores;
    private boolean smt;
    private boolean integrated_GPU;
    private int tdp_W;
    private int st_Pref;
    private int mt_Pref;
    private float core_Clock;
    private float boost_Clock;
    @OneToOne(targetEntity = Product.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "product_FK", referencedColumnName = "id")
    private Product product;
    //private long product_FK;

    public Cpu(String socket, int cores, boolean smt, boolean integrated_GPU, int tdpW, int stPref, int mtPref, float coreClock, float boostClock/*, long product_FK*/) {
        this.socket = socket;
        this.cores = cores;
        this.smt = smt;
        this.integrated_GPU = integrated_GPU;
        this.tdp_W = tdpW;
        this.st_Pref = stPref;
        this.mt_Pref = mtPref;
        this.core_Clock = coreClock;
        this.boost_Clock = boostClock;
        //this.product_FK = product_FK;
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

    public boolean isIntegrated_GPU() {
        return integrated_GPU;
    }

    public void setIntegrated_GPU(boolean integratedGPU) {
        this.integrated_GPU = integratedGPU;
    }

    public int getTdp_W() {
        return tdp_W;
    }

    public void setTdp_W(int tdpW) {
        this.tdp_W = tdpW;
    }

    public int getSt_Pref() {
        return st_Pref;
    }

    public void setSt_Pref(int stPref) {
        this.st_Pref = stPref;
    }

    public int getMt_Pref() {
        return mt_Pref;
    }

    public void setMt_Pref(int mtPref) {
        this.mt_Pref = mtPref;
    }

    public float getCore_Clock() {
        return core_Clock;
    }

    public void setCore_Clock(float coreClock) {
        this.core_Clock = coreClock;
    }

    public float getBoost_Clock() {
        return boost_Clock;
    }

    public void setBoost_Clock(float boostClock) {
        this.boost_Clock = boostClock;
    }

    //public long getProduct_FK() {
    //    return product_FK;
    //}
//
    //public void setProduct_FK(long productFK) {
    //    this.product_FK = productFK;
    //}

    @Override
    public String toString() {
        return "Cpu{" +
                "id=" + id +
                ", socket='" + socket + '\'' +
                ", cores=" + cores +
                ", smt=" + smt +
                ", integratedGPU=" + integrated_GPU +
                ", tdpW=" + tdp_W +
                ", stPref=" + st_Pref +
                ", mtPref=" + mt_Pref +
                ", coreClock=" + core_Clock +
                ", boostClock=" + boost_Clock +
                //", productFK=" + product_FK +
                '}';
    }
}
