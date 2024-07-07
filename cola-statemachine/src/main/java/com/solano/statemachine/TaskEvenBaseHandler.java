package com.solano.statemachine;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.solano.entity.TaskEven;
import lombok.Getter;

/**
 * @author wuhongbin@xiaomi.com
 * @date 2024/7/5 16:47
 */
@Getter
public abstract class TaskEvenBaseHandler<S, E, C> {

    protected final TaskEven taskEven;

    protected TaskEvenBaseHandler(TaskEven taskEven) {
        this.taskEven = taskEven;
    }

    public abstract Condition<C> condition();

    public abstract Action<S, E, C> action();
}
