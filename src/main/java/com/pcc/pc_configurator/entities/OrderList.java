package com.pcc.pc_configurator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;
    private long product_Id;
    private long order_Id;
    private int quantity;

    public OrderList(LocalDate date, long productId, long orderId, int quantity) {
        this.date = date;
        this.product_Id = productId;
        this.order_Id = orderId;
        this.quantity = quantity;
    }

    public OrderList() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(long productId) {
        this.product_Id = productId;
    }

    public long getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(long orderId) {
        this.order_Id = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "id=" + id +
                ", date=" + date +
                ", productId=" + product_Id +
                ", orderId=" + order_Id +
                ", quantity=" + quantity +
                '}';
    }
}
