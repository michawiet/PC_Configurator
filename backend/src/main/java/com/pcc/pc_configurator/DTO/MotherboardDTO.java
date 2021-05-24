package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotherboardDTO {
    @JsonIgnore
    private long id;
    private float tier;
    private String chipset;
    private String socket;
    private String formFactor;
    private int memorySlots;
    private int memoryMaxGB;
    @JsonIgnore
    private long productFK;
}
