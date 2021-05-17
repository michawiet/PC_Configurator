package com.pcc.pc_configurator.repositories;

import com.pcc.pc_configurator.entities.OrderList;
import com.pcc.pc_configurator.entities.Order_;
import com.pcc.pc_configurator.entities.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderListRepository extends JpaRepository<OrderList,Long> {

    //@Query("SELECT u FROM User u WHERE u.email like :email")
    //User findByEmail(@Param("email") String email);
    //@Query("SELECT u FROM User u JOIN ORDER_ O on u.ID = O.USER_ID")
    //public List<OrderList> findAllByOrder(@Param("") order);
}
