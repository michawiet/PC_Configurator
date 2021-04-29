package com.pcc.pc_configurator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class PcConfiguratorApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(PcConfiguratorApplication.class, args);
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:database", "admin", "admin");
        // add application code here
        conn.close();
    }

}
