package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.Entities.Case_;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case_,Long> {
}
