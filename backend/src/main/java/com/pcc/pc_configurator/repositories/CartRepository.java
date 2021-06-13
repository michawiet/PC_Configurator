package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
