package com.pcc.pc_configurator.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDTO {
    private long id;
    private long productId;
    private long orderId;
    private int quantity;
}
