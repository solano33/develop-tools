package com.solano.service;

import com.alibaba.cola.statemachine.StateMachine;
import com.solano.entity.Task;
import com.solano.entity.TaskEven;
import com.solano.entity.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;

/**
 * @author github.com/solano33
 * @date 2024/7/8 00:36
 */
@Slf4j
@Service
public class TaskService {

    @Resource
    private StateMachine<TaskStateEnum, TaskEven, Task> taskStateMachine;

    public void updateTasks() {

        boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        log.info("actualTransactionActive: {}", actualTransactionActive);

        Task task = new Task(1, TaskStateEnum.init, "单程优化2");
        updateTask(task);
    }

    public void updateTask(Task task) {
        boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        log.info("actualTransactionActive: {}", actualTransactionActive);

        log.info("before, task: {}", task);
        TaskStateEnum taskStateEnum = taskStateMachine.fireEvent(TaskStateEnum.init, TaskEven.initialization, task);

        log.info("after, task: {}", task);
    }
}
