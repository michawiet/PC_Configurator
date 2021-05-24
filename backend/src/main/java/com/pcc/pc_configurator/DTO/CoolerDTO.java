package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoolerDTO {
    @JsonIgnore
    private long id;
    private int tier;
    private float noiseLevelDB;
    private boolean isAir;
    private boolean isWorkstation;
    private Product product;
}
