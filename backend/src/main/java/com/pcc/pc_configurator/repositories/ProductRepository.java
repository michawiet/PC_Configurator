package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
