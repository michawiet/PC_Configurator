package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageDTO {
    private long id;
    @JsonView(Views.Normal.class)
    private int capacityGB;
    @JsonView(Views.Normal.class)
    private int tier;
    @JsonView(Views.Normal.class)
    private String type;
    @JsonView(Views.Normal.class)
    private String formFactor;
    @JsonView(Views.Normal.class)
    private String interface_;
    private long productFK;
}
