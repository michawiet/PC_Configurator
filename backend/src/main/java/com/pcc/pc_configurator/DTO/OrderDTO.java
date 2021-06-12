package com.pcc.pc_configurator.DTO;

import com.pcc.pc_configurator.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private long id;
    private LocalDate date;
    private User user;
    private String status;

}
