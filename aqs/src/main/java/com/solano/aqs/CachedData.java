package com.solano.aqs;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author github.com/solano33
 * @date 2024/10/17 22:45
 */
public class CachedData {
    Object data;
    boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    void processCachedData() {
        // 先加读锁
        rwl.readLock().lock();

        if (!cacheValid) {
            // 如果缓存过期，则需要先释放读锁，再加写锁。读写锁是不支持升级的
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                // Recheck state because another thread might have
                // acquired write lock and changed state before we did.
                if (!cacheValid) {
                    data = "new data";
                    cacheValid = true;
                }
                // 在释放写锁前，需要先加读锁，不然可能被抢了
                rwl.readLock().lock();
            } finally {
                rwl.writeLock().unlock(); // Unlock write, still hold read
            }
        }
        try {
            use(data);
        } finally {
            rwl.readLock().unlock();
        }
    }

    private void use(Object data) {

    }
}