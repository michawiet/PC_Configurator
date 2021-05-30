package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface MotherboardRepository extends JpaRepository<Motherboard,Long> {
}
