package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {

    List<User> findAllByEmail(String email);

}
