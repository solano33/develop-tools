package com.solano.redis.redis;

import org.redisson.api.RSortedSet;
import org.redisson.api.RedissonClient;

import java.util.Collection;
import java.util.List;

/**
 * 现象：
 * 任务流转频率较低、空轮询比较多，导致频繁扫描索引树扫描
 * - 项目启动后填充到队列中，固定100个
 * - 如果任务删除则先清队列，再删数据库
 * - 如果任务优先级修改，则先改队列，再改数据库
 * - 如果新增数据，则先尝试再落数据库
 *
 *
 * @author wuhongbin@xiaomi.com
 * @date 2024/10/18 13:59
 */
public class AirBoundedPriorityQueue<T> {

    private final String topic;
    private final int capacity;
    private final RedissonClient redissonClient;

    public AirBoundedPriorityQueue(String topic, int capacity, RedissonClient redissonClient) {
        this.topic = topic;
        this.capacity = capacity;
        this.redissonClient = redissonClient;
    }

    public int capacity() {
        return capacity;
    }

    /**
     * 往队列添加单个任务
     * 等同于offer(Collections.singletonList(data))
     *
     * @param data
     */
    public void offer(T data) {

    }

    /**
     * 往队列添加一批 n 个任务
     * 当前队列任务数 c, 总容量 t
     * 1. 如果 c + n > t，取出其中优先级最低的 n 个老任务
     *  a. 将 2n 的任务重新排序，按优先级重新入队
     * 2. 如果 c + n <= t，直接入队
     */
    public void offer(Collection<T> data) {

    }


    public T take() {
        return null;
    }

    /**
     * 从队列中取出 n 个数据
     * 当前队列任务数 c, 总容量 t
     * 1. 如果 c + n > t，取出其中优先级最低的 n 个老任务
     *  a. 将 2n 的任务重新排序，按优先级重新入队
     * 2. 如果 c + n <= t，直接入队
     */
    public List<T> take(Integer count) {
        return null;
    }
}
