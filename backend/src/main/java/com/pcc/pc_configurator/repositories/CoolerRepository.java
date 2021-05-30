package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Cooler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface CoolerRepository extends JpaRepository<Cooler,Integer> {
}
