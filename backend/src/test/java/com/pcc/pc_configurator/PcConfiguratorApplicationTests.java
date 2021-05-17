package com.pcc.pc_configurator;

import com.pcc.pc_configurator.entities.OrderList;
import com.pcc.pc_configurator.entities.Order_;
import com.pcc.pc_configurator.entities.Product;
import com.pcc.pc_configurator.entities.User;
import com.pcc.pc_configurator.repositories.OrderListRepository;
import com.pcc.pc_configurator.repositories.OrderRepository;
import com.pcc.pc_configurator.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class PcConfiguratorApplicationTests {
    @Autowired
    OrderListRepository orderListRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    @Test
    void contextLoads() {
        //User user = testEntityManager.find(User.class,12);

        OrderList newList = new OrderList();
        //newList.setProduct_Id(20);
       //newList.setOrder_Id(10);
       //newList.setQuantity(21);
        OrderList sOrder = orderListRepository.save(newList);
        assertTrue(sOrder.getId() >=1001);
    }

}
