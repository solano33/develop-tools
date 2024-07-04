package com.solano.task;

import com.solano.task.entity.Task;
import com.solano.task.entity.TaskEven;
import com.solano.task.entity.TaskStatueEnum;
import com.solano.task.statemachine.TaskStateMachine;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineEventResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author github.com/solano33
 * @date 2024/7/4 21:32
 */
@RequestMapping("/api/task")
@RestController
public class TaskController {

    @Resource
    private StateMachine<TaskStatueEnum, TaskEven> stateMachine;

    @PostMapping("/test")
    public String stateMachine() {
        Task task = new Task(UUID.randomUUID().toString(), TaskStatueEnum.init);
        Message<TaskEven> message = MessageBuilder.withPayload(TaskEven.initialization).setHeader("task", task).build();
        Flux<StateMachineEventResult<TaskStatueEnum, TaskEven>> flux = stateMachine.sendEvent(Mono.just(message));
        return "success";
    }
}
