package com.pcc.pc_configurator.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RamDTO {
    private long id;
    private int speed;
    private int modulesCount;
    private int moduleCapacity;
    private float fwLatencyNs;
    private int cl;
    private long productFK;
}
