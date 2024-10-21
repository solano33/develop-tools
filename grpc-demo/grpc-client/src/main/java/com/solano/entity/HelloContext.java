package com.solano.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuhongbin@xiaomi.com
 * @date 2024/10/21 20:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloContext {

    private String name;

    private Integer count;

    private String context;
}
