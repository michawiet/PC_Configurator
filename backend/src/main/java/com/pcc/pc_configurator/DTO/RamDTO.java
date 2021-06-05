package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pcc.pc_configurator.Entities.Product;
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
    private Product product;
}
