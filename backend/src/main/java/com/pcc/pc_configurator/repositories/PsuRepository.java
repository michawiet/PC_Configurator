package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Psu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsuRepository extends JpaRepository<Psu,Long> {
}
