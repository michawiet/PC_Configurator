package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.Temp;
import com.pcc.pc_configurator.entities.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CpuRepository extends JpaRepository<Cpu,Long> {
    //@Query("FROM ReleaseDateType AS rdt WHERE cm.rdt.cacheMedia.id = ?1")
    @Query(value = "SELECT c.socket, p.brand FROM Cpu c JOIN Product p on p.id = c.product.id WHERE p.id = :id")
    List<Temp> findByIdTemp(@Param("id") Long along);
}
