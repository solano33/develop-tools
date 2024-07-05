package com.sulano.entity;

/**
 * @author github.com/solano33
 * @date 2024/7/4 21:33
 */
public class Task {

    public String uuid;

    public TaskStateEnum state;

    public Task(String uuid, TaskStateEnum state) {
        this.uuid = uuid;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Task{" +
                "uuid='" + uuid + '\'' +
                ", statue=" + state +
                '}';
    }
}
