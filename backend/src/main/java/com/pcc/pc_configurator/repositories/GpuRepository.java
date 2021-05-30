package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Gpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface GpuRepository extends JpaRepository<Gpu,Long> {
}
