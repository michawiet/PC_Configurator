package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.Entities.Gpu;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GpuRepository extends JpaRepository<Gpu,Long> {
}
