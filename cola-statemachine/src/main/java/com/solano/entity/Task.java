package com.solano.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author github.com/solano33
 * @date 2024/7/4 21:33
 */
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Task {

    private Integer id;

    private TaskStateEnum state;

    private String name;
}
