package com.pcc.pc_configurator.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageDTO {
    private long id;
    private int capacityGB;
    private int tier;
    private String type;
    private String formFactor;
    private String interface_;
    private long productFK;
}
