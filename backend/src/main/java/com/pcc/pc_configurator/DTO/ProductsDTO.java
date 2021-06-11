package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {
    //@JsonIgnore
    private long id;
    private String brand;
    private String name;
    private float price;
    @JsonIgnore
    private int quantity;
    private String image;
}
