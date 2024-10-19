package com.solano.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author github.com/solano33
 * @date 2024/10/16 00:05
 */
public class MyAqs {

    static int count = 0;
    static AtomicInteger atomicCount = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        unlock();
        lock();
    }

    private static void unlock() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    count++;
                    atomicCount.addAndGet(1);
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println("unlock: count = " + count);
        System.out.println("unlock: atomicCount.get() = " + atomicCount.get());
        count = 0;
        atomicCount = new AtomicInteger(0);
    }

    private static void lock() throws InterruptedException {
        MyLock myLock = new MyLock();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    myLock.lock();
                    count++;
                    myLock.unlock();
                    atomicCount.addAndGet(1);
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println("lock: count = " + count);
        System.out.println("lock: atomicCount.get() = " + atomicCount.get());
    }
}

class MyLock implements Lock {

    private final MySync sync = new MySync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}

class MySync extends AbstractQueuedSynchronizer {

    /**
     * 尝试加锁，只会加一次
     */
    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    @Override
    protected boolean isHeldExclusively() {
        return getState() == 1;
    }

    public Condition newCondition() {
        return new ConditionObject();
    }
}