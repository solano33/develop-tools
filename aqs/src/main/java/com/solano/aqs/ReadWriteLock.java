package com.solano.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.solano.aqs.ReadWriteLock.sleep;

/**
 * @author github.com/solano33
 * @date 2024/10/17 00:37
 */
@Slf4j
public class ReadWriteLock {

    public static void main(String[] args) {
        DataContainer<String> stringDataContainer = new DataContainer<>();
//        rr(stringDataContainer);
//        ww(stringDataContainer);
//        wr(stringDataContainer);
        rw(stringDataContainer);
    }

    private static void rw(DataContainer<String> stringDataContainer) {
        read(stringDataContainer);
        write(stringDataContainer);
    }

    private static void ww(DataContainer<String> stringDataContainer) {
        write(stringDataContainer);
        write(stringDataContainer);
    }

    private static void rr(DataContainer<String> stringDataContainer) {
        read(stringDataContainer);
        read(stringDataContainer);
    }

    private static void wr(DataContainer<String> stringDataContainer) {
        write(stringDataContainer);
        read(stringDataContainer);
    }


    private static void read(DataContainer<String> stringDataContainer) {
        new Thread(() -> {
            String data = stringDataContainer.read();
            log.info("data: {}", data);
        }, "readThread").start();
    }

    private static void write(DataContainer<String> stringDataContainer) {
        new Thread(() -> {
            stringDataContainer.write("hello world");
        }, "writeThread").start();
    }

    public static void sleep(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException ignored) {
        }
    }
}


@Slf4j
class DataContainer<T> {
    private T data;
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

    public T read() {
        try {
            readLock.lock();
            log.info("read begin...");
            sleep(1);
            log.info("read end...");
            return data;
        } finally {
            readLock.unlock();
        }
    }

    public void write(T data) {
        writeLock.lock();
        try {
            log.info("write begin...");
            sleep(1);
            this.data = data;
            log.info("write end...");
        } finally {
            writeLock.unlock();
        }
    }
}

