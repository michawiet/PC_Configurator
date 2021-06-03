package com.pcc.pc_configurator.Entities;

import javax.persistence.*;

@Entity
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long product_Id;
    private long order_Id;
    private int quantity;

    public OrderList(long productId, long orderId, int quantity) {
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
                ", productId=" + product_Id +
                ", orderId=" + order_Id +
                ", quantity=" + quantity +
                '}';
    }
}
