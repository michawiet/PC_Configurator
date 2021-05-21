package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Order_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends JpaRepository<Order_,Long> {
}
