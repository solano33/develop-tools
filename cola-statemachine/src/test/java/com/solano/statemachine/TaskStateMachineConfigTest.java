package com.solano.statemachine;

import com.alibaba.cola.statemachine.StateMachine;
import com.solano.entity.Task;
import com.solano.entity.TaskEven;
import com.solano.entity.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles("dev2-loc")
public class TaskStateMachineConfigTest {

    @Resource
    private StateMachine<TaskStateEnum, TaskEven, Task> taskStateMachine;

    @Test
    public void test() {
        Task task = new Task(UUID.randomUUID().toString(), TaskStateEnum.init);
        log.info("before, task: {}", task);
        TaskStateEnum taskStateEnum = taskStateMachine.fireEvent(TaskStateEnum.init, TaskEven.initialization, task);
        log.info("after, task: {}", task);
    }

}