package com.pcc.pc_configurator.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GpuDTO {
    private long id;
    private String chipset;
    private int memoryGB;
    private int coreClockMHZ;
    private int boostClockMHZ;
    private float lengthMM;
    private int tdp;
    private int recommendedPsuWatts;
    private int performance;
    private long productFK;
}
