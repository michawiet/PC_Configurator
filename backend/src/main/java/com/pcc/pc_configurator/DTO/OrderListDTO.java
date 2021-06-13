package com.pcc.pc_configurator.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pcc.pc_configurator.entities.Order_;
import com.pcc.pc_configurator.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDTO {
    private long id;
    private Product product;
    //private Product product;
    //private Order_ order_;
    private int quantity;
}
