package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import com.pcc.pc_configurator.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseDTO {
    private long id;
    @JsonView(Views.Normal.class)
    private String power_Supply_Standard;
    @JsonView(Views.Normal.class)
    private String max_Motherboard_Size;
    @JsonView(Views.Normal.class)
    private String type;
    @JsonView(Views.Normal.class)
    private String side_Panel_Window;
    private long productFK;

}
