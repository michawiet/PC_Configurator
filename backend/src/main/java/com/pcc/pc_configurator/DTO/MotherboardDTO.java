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
public class MotherboardDTO {
    @JsonIgnore
    private long id;
    private float tier;
    private String chipset;
    private String socket;
    private String formFactor;
    private int memorySlots;
    private int memoryMaxGB;
    private Product product;
}
