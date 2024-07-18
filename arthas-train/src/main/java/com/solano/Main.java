package com.solano;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Random random = new Random();
            int a = random.nextInt();
            int b = random.nextInt();
            int print = print(a, b);
            TimeUnit.SECONDS.sleep(10);
        }
    }

    private static int print(int a, int b) {
        log.info("now is " + LocalDateTime.now() + ", a = " + a + ", b = " + b);
        log.debug("debug...");
        return a + b;
    }
}