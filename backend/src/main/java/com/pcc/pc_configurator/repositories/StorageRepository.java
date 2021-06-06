package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Long> {
}
