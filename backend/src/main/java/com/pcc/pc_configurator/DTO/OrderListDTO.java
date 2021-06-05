package com.pcc.pc_configurator.DTO;

import com.pcc.pc_configurator.Entities.Order_;
import com.pcc.pc_configurator.Entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDTO {
    private long id;
    private Product product;
    private Order_ order_;
    private int quantity;
}
