package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RamDTO {
    private long id;
    @JsonView(Views.Normal.class)
    private int speed;
    @JsonView(Views.Normal.class)
    private int modulesCount;
    @JsonView(Views.Normal.class)
    private int moduleCapacity;
    @JsonView(Views.Normal.class)
    private float fwLatencyNs;
    @JsonView(Views.Normal.class)
    private int cl;
    private long productFK;
}
