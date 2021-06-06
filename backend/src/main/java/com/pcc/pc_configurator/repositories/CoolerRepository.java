package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.Entities.Cooler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoolerRepository extends JpaRepository<Cooler,Long> {
}
