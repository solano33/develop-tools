package com.solano.canal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author github.com/solano33
 * @date 2024/9/18 00:40
 */
@Slf4j
@SpringBootApplication
public class CanalApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(CanalApplication.class, args);
        log.info("applicationContext: {}", applicationContext);
    }
}
