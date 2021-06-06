package com.pcc.pc_configurator.entities;

import javax.persistence.*;

@Entity
public class Case_ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String power_Supply_Standard;
    private String max_Motherboard_Size;
    private String type;
    private String side_Panel_Window;
    @OneToOne
    @JoinColumn(name = "product_FK", referencedColumnName = "id")
    private Product product;

    public Case_(String power_Supply_Standard, String max_Motherboard_Size, String type, String side_Panel_Window, Product product) {
        this.power_Supply_Standard = power_Supply_Standard;
        this.max_Motherboard_Size = max_Motherboard_Size;
        this.type = type;
        this.side_Panel_Window = side_Panel_Window;
        this.product = product;
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

    public void setPower_Supply_Standard(String power_Supply_Standard) {
        this.power_Supply_Standard = power_Supply_Standard;
    }

    public String getMax_Motherboard_Size() {
        return max_Motherboard_Size;
    }

    public void setMax_Motherboard_Size(String max_Motherboard_Size) {
        this.max_Motherboard_Size = max_Motherboard_Size;
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

    public void setSide_Panel_Window(String side_Panel_Window) {
        this.side_Panel_Window = side_Panel_Window;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
