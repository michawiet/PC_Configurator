package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GpuDTO {
    private long id;
    @JsonView(Views.Normal.class)
    private String chipset;
    @JsonView(Views.Normal.class)
    private int memoryGB;
    @JsonView(Views.Normal.class)
    private int coreClockMHZ;
    @JsonView(Views.Normal.class)
    private int boostClockMHZ;
    @JsonView(Views.Normal.class)
    private float lengthMM;
    @JsonView(Views.Normal.class)
    private int tdp;
    @JsonView(Views.Normal.class)
    private int recommendedPsuWatts;
    @JsonView(Views.Normal.class)
    private int performance;
    private long productFK;
}
