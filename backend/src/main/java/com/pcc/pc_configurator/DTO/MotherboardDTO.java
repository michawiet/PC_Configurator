package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotherboardDTO {
    private long id;
    @JsonView(Views.Normal.class)
    private float tier;
    @JsonView(Views.Normal.class)
    private String chipset;
    @JsonView(Views.Normal.class)
    private String socket;
    @JsonView(Views.Normal.class)
    private String formFactor;
    @JsonView(Views.Normal.class)
    private int memorySlots;
    @JsonView(Views.Normal.class)
    private int memoryMaxGB;
    private long productFK;
}
