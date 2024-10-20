package com.solano.redis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author github.com/solano33
 * @date 2024/10/19 17:29
 */
@Data
@AllArgsConstructor
public class TaskMessage {

    private Integer taskId;

    private Integer priority;
}
