package com.solano.redis.redis;

import org.redisson.Redisson;
import org.redisson.api.RSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Comparator;

public class FixedCapacityPriorityQueue {
    private final RSortedSet<Item> sortedSet;
    private final int capacity;

    public FixedCapacityPriorityQueue(String queueName, int capacity) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        RedissonClient redissonClient = Redisson.create(config);
        this.sortedSet = redissonClient.getSortedSet(queueName);
        sortedSet.trySetComparator(new ItemComparator());
        this.capacity = capacity;
    }

    public void add(Item item) {
        if (sortedSet.size() >= capacity) {
            sortedSet.remove(sortedSet.first()); // 移除最低优先级的元素
        }
        sortedSet.add(item);
    }

    public Item poll() {
//        return sortedSet.();
        return null;
    }

    public static class Item {
        private final String name;
        private final double priority;

        public Item(String name, double priority) {
            this.name = name;
            this.priority = priority;
        }

        public String getName() {
            return name;
        }

        public double getPriority() {
            return priority;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", priority=" + priority +
                    '}';
        }
    }

    public static class ItemComparator implements Comparator<Item> {

        @Override
        public int compare(Item o1, Item o2) {
            if (o1.priority == o2.priority) {
                return 0;
            }
            return o1.priority >= o2.priority ? 1 : -1;
        }
    }

    public static void main(String[] args) {
        FixedCapacityPriorityQueue queue = new FixedCapacityPriorityQueue("myQueue", 5);
        
        queue.add(new Item("task1", 1.0));
        queue.add(new Item("task2", 2.0));
        queue.add(new Item("task3", 3.0));
        queue.add(new Item("task4", 4.0));
        queue.add(new Item("task5", 5.0));
        queue.add(new Item("task6", 0.5)); // 这个将会使队列中移除最低优先级的任务

        System.out.println(queue.poll()); // 输出优先级最高的任务
    }
}
