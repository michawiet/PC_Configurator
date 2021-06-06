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
    @OneToOne
    @JoinColumn(name = "product_FK", referencedColumnName = "id")
    private Product product;
    //private long product_FK;


    public Cpu(String socket, int cores, boolean smt, boolean integrated_GPU, int tdp_W, int st_Pref, int mt_Pref, float core_Clock, float boost_Clock, Product product) {
        this.socket = socket;
        this.cores = cores;
        this.smt = smt;
        this.integrated_GPU = integrated_GPU;
        this.tdp_W = tdp_W;
        this.st_Pref = st_Pref;
        this.mt_Pref = mt_Pref;
        this.core_Clock = core_Clock;
        this.boost_Clock = boost_Clock;
        this.product = product;
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

    public void setIntegrated_GPU(boolean integrated_GPU) {
        this.integrated_GPU = integrated_GPU;
    }

    public int getTdp_W() {
        return tdp_W;
    }

    public void setTdp_W(int tdp_W) {
        this.tdp_W = tdp_W;
    }

    public int getSt_Pref() {
        return st_Pref;
    }

    public void setSt_Pref(int st_Pref) {
        this.st_Pref = st_Pref;
    }

    public int getMt_Pref() {
        return mt_Pref;
    }

    public void setMt_Pref(int mt_Pref) {
        this.mt_Pref = mt_Pref;
    }

    public float getCore_Clock() {
        return core_Clock;
    }

    public void setCore_Clock(float core_Clock) {
        this.core_Clock = core_Clock;
    }

    public float getBoost_Clock() {
        return boost_Clock;
    }

    public void setBoost_Clock(float boost_Clock) {
        this.boost_Clock = boost_Clock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
