package com.pcc.pc_configurator;

import com.pcc.pc_configurator.entities.User;
import com.pcc.pc_configurator.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class PcConfiguratorApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Test
    void contextLoads() {
        String email = "chulk2@chron.com";

        User user = userRepository.findByEmail(email);
        assertThat(user).isNull();
    }

}
