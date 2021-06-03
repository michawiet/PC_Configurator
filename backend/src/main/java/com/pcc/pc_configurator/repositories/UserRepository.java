package com.pcc.pc_configurator.Repositories;

import com.pcc.pc_configurator.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Override
    Optional<User> findById(Long aLong);

    @Query("SELECT u FROM User u WHERE u.email like :email")
    User findByEmail(@Param("email") String email);
}
