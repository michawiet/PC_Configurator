package com.pcc.pc_configurator.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Order_ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;
    private long user_Id;

    public Order_(LocalDate date, long userId) {
        this.date = date;
        this.user_Id = userId;
    }

    public Order_() {
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

    public long getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(long userId) {
        this.user_Id = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", userId=" + user_Id +
                '}';
    }
}
