package com.solano.statemachine;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.solano.entity.Task;
import com.solano.entity.TaskEven;
import com.solano.entity.TaskStateEnum;
import com.solano.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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

    @Resource
    private TaskMapper taskMapper;

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
            AopProxyUtils.ultimateTargetClass(this);
            ((TaskInitializationHandler)AopContext.currentProxy()).action(from, to, taskEven, task);
        };
    }

    @Transactional(rollbackFor = Exception.class)
    public void action(TaskStateEnum from, TaskStateEnum to, TaskEven taskEven, Task task) {
        log.info("执行当前触发事件逻辑, form: {}, to: {}, even: {}", from, to, taskEven);
        log.info("from_task: {}", task);
        task.setState(to);
//            taskMapper.updateState(task.getId(), task.getState());
        updateTask3();
        updateTask4();
        log.info("to_task: {}", to);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateTask3() {
        Task task = new Task(3, TaskStateEnum.finish, "");
        taskMapper.updateState(task.getId(), task.getState());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateTask4() {
        Task task = new Task(4, TaskStateEnum.finish, "");
        taskMapper.updateState(task.getId(), task.getState());
        throw new RuntimeException();
    }
}
