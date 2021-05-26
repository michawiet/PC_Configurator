package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CpuRepository extends JpaRepository<Cpu,Long> {
    //@Query("FROM ReleaseDateType AS rdt WHERE cm.rdt.cacheMedia.id = ?1")
    //@Query("SELECT c FROM Cpu c JOIN Product p on p.id = c.product_FK WHERE p.id = :id")
    //List<Temp> findByIdTemp(@Param("id") Long along);
}
