package com.solano;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            System.out.println("now is " + LocalDateTime.now());
            TimeUnit.SECONDS.sleep(10);
        }
    }
}