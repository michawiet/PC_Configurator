package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product,Long> {
}
