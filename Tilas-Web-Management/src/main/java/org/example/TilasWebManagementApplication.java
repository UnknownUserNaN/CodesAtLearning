package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan // 此注解开启了SpringBoot对Servlet、Filter、Listener组件的扫描
@SpringBootApplication
public class TilasWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TilasWebManagementApplication.class, args);
    }

}
