package com.solano.task.statemachine;

import com.solano.task.entity.Task;
import com.solano.task.entity.TaskEven;
import com.solano.task.entity.TaskStatueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@WithStateMachine(name = "taskStateMachine")
public class TaskStateMachineListener {

    @OnTransition(source = "init", target = "notStart")
    public boolean init(Message<TaskEven> message) {
        Task task = (Task) message.getHeaders().get("task");
        task.statue = TaskStatueEnum.notStart;
        log.info("初始化完毕task：{}", task.uuid);
        return true;
    }

}
