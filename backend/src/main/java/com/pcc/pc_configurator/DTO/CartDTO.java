package com.pcc.pc_configurator.DTO;

import com.pcc.pc_configurator.entities.Product;
import com.pcc.pc_configurator.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private long id;
    private User user;
    private Product product;
    private long quantity;
}
