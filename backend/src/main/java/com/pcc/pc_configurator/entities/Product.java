package com.pcc.pc_configurator.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String brand;
    private String name;
    private float price;
    private int quantity;
    private String image;

    public Product(String brand, String name, float price, int quantity, String image) {
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        //  "basket"          klucz   wartość(aka. id i ilość)
        Map<String, List<Integer>> localStorage;
        //List<Integer> basket;
        List<Map<String, Integer>> basket;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
