package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CpuRepository extends JpaRepository<Cpu,Long> {
    @Override
    List<Cpu> findAllById(Iterable<Long> iterable);

    @Query("SELECT c.boost_Clock,c.core_Clock FROM Cpu c JOIN Product P on P.id = c.product_FK WHERE c.id = :id")
    Optional<Cpu> findById(@Param("id") Long along);
}
