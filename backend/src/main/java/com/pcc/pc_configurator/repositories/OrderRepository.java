package com.pcc.pc_configurator.Repositories;

import com.pcc.pc_configurator.Entities.Order_;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order_,Long> {
}
