package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Cooler;
import org.springframework.data.repository.CrudRepository;

public interface CoolerRepo extends CrudRepository<Cooler,Long> {
}