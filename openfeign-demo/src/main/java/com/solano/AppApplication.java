package com.solano;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class AppApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AppApplication.class, args);
        log.info("applicationContext: {}", applicationContext);
    }
}
