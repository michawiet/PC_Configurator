package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.OrderList;
import org.springframework.data.repository.CrudRepository;

public interface OrderListRepo extends CrudRepository<OrderList,Long> {
}
