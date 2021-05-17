package com.pcc.pc_configurator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Case_ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String power_Supply_Standard;
    private String max_Motherboard_Size;
    private String type;
    private String side_Panel_Window;
    private long product_FK;

    public Case_(String powerSupplyStandard, String maxMotherboardSize, String type, String sidePanelWindow, long productFK) {
        this.power_Supply_Standard = powerSupplyStandard;
        this.max_Motherboard_Size = maxMotherboardSize;
        this.type = type;
        this.side_Panel_Window = sidePanelWindow;
        this.product_FK = productFK;
    }

    public Case_() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPower_Supply_Standard() {
        return power_Supply_Standard;
    }

    public void setPower_Supply_Standard(String powerSupplyStandard) {
        this.power_Supply_Standard = powerSupplyStandard;
    }

    public String getMax_Motherboard_Size() {
        return max_Motherboard_Size;
    }

    public void setMax_Motherboard_Size(String maxMotherboardSize) {
        this.max_Motherboard_Size = maxMotherboardSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSide_Panel_Window() {
        return side_Panel_Window;
    }

    public void setSide_Panel_Window(String sidePanelWindow) {
        this.side_Panel_Window = sidePanelWindow;
    }

    public long getProduct_FK() {
        return product_FK;
    }

    public void setProduct_FK(long productFK) {
        this.product_FK = productFK;
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", powerSupplyStandard='" + power_Supply_Standard + '\'' +
                ", maxMotherboardSize='" + max_Motherboard_Size + '\'' +
                ", type='" + type + '\'' +
                ", sidePanelWindow='" + side_Panel_Window + '\'' +
                ", productFK=" + product_FK +
                '}';
    }
}
