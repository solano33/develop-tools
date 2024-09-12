package com.solano.redis.pojo;

import lombok.*;

/**
 * @author github.com/solano33
 * @date 2024/9/10 22:54
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;

    private String name;

    private int age;
}
