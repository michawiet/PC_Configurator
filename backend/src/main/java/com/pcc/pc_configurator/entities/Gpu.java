package com.pcc.pc_configurator.entities;

import javax.persistence.*;

@Entity
public class Gpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String chipset;
    private int memory_GB;
    private int core_Clock_MHZ;
    private int boost_Clock_MHZ;
    private float length_MM;
    private int tdp;
    private int recommended_Psu_Watts;
    private int performance;
    @OneToOne
    @JoinColumn(name = "product_FK", referencedColumnName = "id")
    private Product product;

    public Gpu() {
    }

    public Gpu(String chipset, int memory_GB, int core_Clock_MHZ, int boost_Clock_MHZ, float length_MM, int tdp, int recommended_Psu_Watts, int performance, Product product) {
        this.chipset = chipset;
        this.memory_GB = memory_GB;
        this.core_Clock_MHZ = core_Clock_MHZ;
        this.boost_Clock_MHZ = boost_Clock_MHZ;
        this.length_MM = length_MM;
        this.tdp = tdp;
        this.recommended_Psu_Watts = recommended_Psu_Watts;
        this.performance = performance;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public int getMemory_GB() {
        return memory_GB;
    }

    public void setMemory_GB(int memory_GB) {
        this.memory_GB = memory_GB;
    }

    public int getCore_Clock_MHZ() {
        return core_Clock_MHZ;
    }

    public void setCore_Clock_MHZ(int core_Clock_MHZ) {
        this.core_Clock_MHZ = core_Clock_MHZ;
    }

    public int getBoost_Clock_MHZ() {
        return boost_Clock_MHZ;
    }

    public void setBoost_Clock_MHZ(int boost_Clock_MHZ) {
        this.boost_Clock_MHZ = boost_Clock_MHZ;
    }

    public float getLength_MM() {
        return length_MM;
    }

    public void setLength_MM(float length_MM) {
        this.length_MM = length_MM;
    }

    public int getTdp() {
        return tdp;
    }

    public void setTdp(int tdp) {
        this.tdp = tdp;
    }

    public int getRecommended_Psu_Watts() {
        return recommended_Psu_Watts;
    }

    public void setRecommended_Psu_Watts(int recommended_Psu_Watts) {
        this.recommended_Psu_Watts = recommended_Psu_Watts;
    }

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
