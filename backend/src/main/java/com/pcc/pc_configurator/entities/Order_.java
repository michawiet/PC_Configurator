package com.pcc.pc_configurator.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Order_ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "user_Id", referencedColumnName = "id")
    private User user;

    public Order_(LocalDate date, User user) {
        this.date = date;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order_{" +
                "id=" + id +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
