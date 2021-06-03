package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pcc.pc_configurator.Entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseDTO {
    @JsonIgnore
    private long id;
    private String power_Supply_Standard;
    private String max_Motherboard_Size;
    private String type;
    private String side_Panel_Window;
    private Product product;

}
