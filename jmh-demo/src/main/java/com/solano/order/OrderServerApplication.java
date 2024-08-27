package com.solano.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author solano
 * @date 2024/7/5 16:36
 */
@Slf4j
@ComponentScan(basePackages = "com.solano")
@SpringBootApplication
public class OrderServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(OrderServerApplication.class);
        log.debug("applicationContext: {}", applicationContext);
    }
}
