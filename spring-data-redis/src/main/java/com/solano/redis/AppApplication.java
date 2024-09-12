package com.solano.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

/**
 * @author github.com/solano33
 * @date 2024/9/10 22:07
 */
@Slf4j
@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AppApplication.class, args);
        log.info("applicationContext: {}", applicationContext);
    }
}
