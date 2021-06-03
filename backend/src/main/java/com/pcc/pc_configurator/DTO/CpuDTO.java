package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pcc.pc_configurator.Entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpuDTO {
    @JsonIgnore
    private long id;
    private String socket;
    private int cores;
    private boolean smt;
    private boolean integratedGPU;
    private int tdpW;
    private int stPref;
    private int mtPref;
    private float coreClock;
    private float boostClock;
    private Product product;

}
