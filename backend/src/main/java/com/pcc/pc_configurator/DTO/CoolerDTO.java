package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoolerDTO {
    private long id;
    @JsonView(Views.Normal.class)
    private int tier;
    @JsonView(Views.Normal.class)
    private float noiseLevelDB;
    @JsonView(Views.Normal.class)
    private boolean isAir;
    @JsonView(Views.Normal.class)
    private boolean isWorkstation;
    private long productFK;
}
