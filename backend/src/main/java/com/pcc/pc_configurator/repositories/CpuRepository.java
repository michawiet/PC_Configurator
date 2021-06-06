package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.Entities.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpuRepository extends JpaRepository<Cpu,Long> {
}
