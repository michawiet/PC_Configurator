package com.pcc.pc_configurator.Repositories;

import com.pcc.pc_configurator.Entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Long> {
}
