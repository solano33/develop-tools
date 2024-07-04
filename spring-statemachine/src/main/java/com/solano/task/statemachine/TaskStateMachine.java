package com.solano.task.statemachine;

import com.solano.task.entity.Task;
import com.solano.task.entity.TaskEven;
import com.solano.task.entity.TaskStatueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import javax.annotation.Resource;
import java.util.EnumSet;

@Slf4j
@EnableStateMachine(name = "taskStateMachine2")
public class TaskStateMachine extends StateMachineConfigurerAdapter<TaskStatueEnum, TaskEven> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<TaskStatueEnum, TaskEven> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<TaskStatueEnum, TaskEven> states) throws Exception {
        states
                .withStates()
                .initial(TaskStatueEnum.init)
                .states(EnumSet.allOf(TaskStatueEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<TaskStatueEnum, TaskEven> transitions) throws Exception {
        transitions
                .withExternal().source(TaskStatueEnum.init).target(TaskStatueEnum.notStart)
                .event(TaskEven.initialization)
                .and()
                .withExternal().source(TaskStatueEnum.notStart).target(TaskStatueEnum.progress)
                .event(TaskEven.allocation)
                .and()
                .withExternal().source(TaskStatueEnum.progress).target(TaskStatueEnum.finish)
                .event(TaskEven.submission)
                .and()
                .withExternal().source(TaskStatueEnum.reject).target(TaskStatueEnum.finish)
                .event(TaskEven.submission)
                .and()
                .withExternal().source(TaskStatueEnum.progress).target(TaskStatueEnum.reject)
                .event(TaskEven.rejection);
    }


    @Bean
    public DefaultStateMachinePersister persister() {
        return new DefaultStateMachinePersister(new StateMachinePersist<TaskStatueEnum, TaskEven, Task>() {
            @Override
            public void write(StateMachineContext<TaskStatueEnum, TaskEven> stateMachineContext, Task task) throws Exception {
                log.info("持久化-write");
            }

            @Override
            public StateMachineContext<TaskStatueEnum, TaskEven> read(Task task) throws Exception {
                log.info("持久化-read");
                return new DefaultStateMachineContext<>(task.statue, null, null, null);
            }
        });
    }
}
