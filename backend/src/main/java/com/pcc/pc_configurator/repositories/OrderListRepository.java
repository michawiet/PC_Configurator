package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.Entities.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderList,Long> {

}
