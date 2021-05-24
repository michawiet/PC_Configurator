package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RamDTO {
    @JsonIgnore
    private long id;
    private int speed;
    private int modulesCount;
    private int moduleCapacity;
    private float fwLatencyNs;
    private int cl;
    @JsonIgnore
    private long productFK;
}
