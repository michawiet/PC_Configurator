package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Case_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.CrudRepository;

public interface CaseRepository extends JpaRepository<Case_,Integer> {
}
