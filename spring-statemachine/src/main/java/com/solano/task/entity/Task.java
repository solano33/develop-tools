package com.solano.task.entity;

/**
 * @author github.com/solano33
 * @date 2024/7/4 21:33
 */
public class Task {

    public String uuid;

    public TaskStatueEnum statue;

    public Task(String uuid, TaskStatueEnum statue) {
        this.uuid = uuid;
        this.statue = statue;
    }

    @Override
    public String toString() {
        return "Task{" +
                "uuid='" + uuid + '\'' +
                ", statue=" + statue +
                '}';
    }
}
