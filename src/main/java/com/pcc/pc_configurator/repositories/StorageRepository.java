package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.Storage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends CrudRepository<Storage,Long> {
}
