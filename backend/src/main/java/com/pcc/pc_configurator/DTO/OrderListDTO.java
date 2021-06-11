package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pcc.pc_configurator.entities.Order_;
import com.pcc.pc_configurator.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDTO {
    private long id;
    private long productId;
    private String brand;
    private String name;
    private Float price;
    @JsonIgnore
    private int productQuantity;
    private String image;
    @JsonIgnore
    private long orderId;
    @JsonIgnore
    private LocalDate date;
    //user
    @JsonIgnore
    private long userId;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String username;
    @JsonIgnore
    private String password;
    //private Product product;
    //private Order_ order_;
    private int quantity;
}
