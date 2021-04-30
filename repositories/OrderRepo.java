package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order,Long> {
}
