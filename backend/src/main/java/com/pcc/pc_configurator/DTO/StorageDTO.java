package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pcc.pc_configurator.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageDTO {
    @JsonIgnore
    private long id;
    private int capacityGB;
    private int tier;
    private String type;
    private String formFactor;
    private String interface_;
    private Product product;
}
