package com.pcc.pc_configurator.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoolerDTO {
    private long id;
    private int tier;
    private float noiseLevelDB;
    private boolean isAir;
    private boolean isWorkstation;
    private long productFK;
}
