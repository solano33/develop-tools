package com.solano.statemachine;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.solano.entity.Task;
import com.solano.entity.TaskEven;
import com.solano.entity.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author wuhongbin@xiaomi.com
 * @date 2024/7/5 16:46
 */
@Slf4j
@Component
public class TaskInitializationHandler extends TaskEvenBaseHandler<TaskStateEnum, TaskEven, Task> {

    public TaskInitializationHandler() {
        super(TaskEven.initialization);
    }

    @Override
    public Condition<Task> condition() {
        return task -> {
            log.info("校验当前任务是否可以初始化, task: {}", task);
            return true;
        };
    }

    @Override
    public Action<TaskStateEnum, TaskEven, Task> action() {
        return (from, to, taskEven, task) -> {
            log.info("执行当前触发事件逻辑, form: {}, to: {}, even: {}",from, to, taskEven);
            log.info("from_task: {}",task);
            task.state = to;
            log.info("to_task: {}", to);
        };
    }
}
