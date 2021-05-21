package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsuDTO {
    private long id;
    @JsonView(Views.Normal.class)
    private float tier;
    @JsonView(Views.Normal.class)
    private String formFactor;
    @JsonView(Views.Normal.class)
    private String efficiencyRating;
    @JsonView(Views.Normal.class)
    private int wattage;
    @JsonView(Views.Normal.class)
    private String modular;
    private long productFK;

}
