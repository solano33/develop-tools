package com.sulano.statemachine;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.StateMachineFactory;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import com.sulano.entity.Task;
import com.sulano.entity.TaskEven;
import com.sulano.entity.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author wuhongbin@xiaomi.com
 * @date 2024/7/5 10:50
 */
@Slf4j
@Configuration
public class TaskStateMachineConfig {

    public static final String taskStateMachineId = "taskStateMachine";

    @Bean(taskStateMachineId)
    public StateMachine<TaskStateEnum, TaskEven, Task> taskStateMachine(List<TaskEvenBaseHandler<TaskStateEnum, TaskEven, Task>> handlers) {
        log.info("handlers: {}", handlers);
        Map<TaskEven, TaskEvenBaseHandler<TaskStateEnum, TaskEven, Task>> handlerMap = handlers.stream().collect(Collectors.toMap(TaskEvenBaseHandler::getTaskEven, Function.identity(), (a, b) -> a));
        StateMachineBuilder<TaskStateEnum, TaskEven, Task> builder = StateMachineBuilderFactory.create();
        builder.externalTransition().from(TaskStateEnum.init).to(TaskStateEnum.notStart).on(TaskEven.initialization)
                .when(handlerMap.get(TaskEven.initialization).condition()).perform(handlerMap.get(TaskEven.initialization).action());
        builder.build(taskStateMachineId);
        StateMachine<TaskStateEnum, TaskEven, Task> taskStateMachine = StateMachineFactory.get(taskStateMachineId);
        taskStateMachine.showStateMachine();
        return taskStateMachine;
    }
}
