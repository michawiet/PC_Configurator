package com.pcc.pc_configurator.Entities;

import javax.persistence.*;

@Entity
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "product_Id", referencedColumnName = "id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "order_Id", referencedColumnName = "id")
    private Order_ order_;
    private int quantity;

    public OrderList(Product product, Order_ order_, int quantity) {
        this.product = product;
        this.order_ = order_;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order_ getOrder_() {
        return order_;
    }

    public void setOrder_(Order_ order_) {
        this.order_ = order_;
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
                ", product=" + product +
                ", order_=" + order_ +
                ", quantity=" + quantity +
                '}';
    }
}
