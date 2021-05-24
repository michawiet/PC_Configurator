package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GpuDTO {
    @JsonIgnore
    private long id;
    private String chipset;
    private int memoryGB;
    private int coreClockMHZ;
    private int boostClockMHZ;
    private float lengthMM;
    private int tdp;
    private int recommendedPsuWatts;
    private int performance;
    @JsonIgnore
    private long productFK;
}
