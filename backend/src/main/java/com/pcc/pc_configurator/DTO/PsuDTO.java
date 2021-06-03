package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.Entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsuDTO {
    @JsonIgnore
    private long id;
    private float tier;
    private String formFactor;
    private String efficiencyRating;
    private int wattage;
    private String modular;
    private Product product;

}
