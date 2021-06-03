package com.pcc.pc_configurator;

import com.pcc.pc_configurator.Entities.OrderList;
import com.pcc.pc_configurator.Repositories.OrderListRepository;
import com.pcc.pc_configurator.Repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
