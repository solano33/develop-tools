package com.solano.statemachine;

import com.alibaba.cola.statemachine.StateMachine;
import com.solano.entity.Task;
import com.solano.entity.TaskEven;
import com.solano.entity.TaskStateEnum;
import com.solano.service.TaskService;
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
    @Resource
    private TaskService taskService;

    @Test
    public void test() {
        taskService.updateTasks();
    }

}