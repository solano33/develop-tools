package com.solano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wuhongbin@xiaomi.com
 * @date 2024/7/5 16:36
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AppApplication.class);
    }
}
