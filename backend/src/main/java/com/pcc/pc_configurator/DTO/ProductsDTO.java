package com.pcc.pc_configurator.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {
    private long id;
    private String brand;
    private String name;
    private float price;
    private int quantity;
}
