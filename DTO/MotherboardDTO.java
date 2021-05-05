package com.pcc.pc_configurator.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotherboardDTO {
    private long id;
    private float tier;
    private String chipset;
    private String formFactor;
    private int memorySlots;
    private int memoryMaxGB;
    private long productFK;
}
