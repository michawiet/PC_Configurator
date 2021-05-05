package com.pcc.pc_configurator.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsuDTO {
    private long id;
    private float tier;
    private String formFactor;
    private String efficiencyRating;
    private int wattage;
    private String modular;
    private long productFK;

}
