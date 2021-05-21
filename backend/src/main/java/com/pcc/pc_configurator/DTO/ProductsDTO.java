package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {
    private long id;
    @JsonView(Views.Normal.class)
    private String brand;
    @JsonView(Views.Normal.class)
    private String name;
    @JsonView(Views.Normal.class)
    private float price;
    @JsonView(Views.Normal.class)
    private int quantity;
}
