package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Ram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RamRepository extends JpaRepository<Ram,Long> {
}
