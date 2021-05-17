package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}