package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CpuRepository extends JpaRepository<Cpu,Long> {

    @Query(value = "SELECT * FROM Cpu c JOIN Product p on p.id = c.product_FK WHERE p.id = :id" , nativeQuery = true)
    Optional<Cpu> findByIdTemp(@Param("id") Long along);
}
