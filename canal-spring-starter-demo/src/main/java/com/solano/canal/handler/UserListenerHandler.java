package com.solano.canal.handler;

import com.solano.canal.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * todo：还没调通，demo
 * @author github.com/solano33
 * @date 2024/9/18 00:34
 */
@Slf4j
@CanalTable("user")
@Component
public class UserListenerHandler implements EntryHandler<User> {

    @Override
    public void insert(User user) {
        log.info("insert: {}", user);
    }

    @Override
    public void update(User before, User after) {
        log.info("update: \nbefore: {}, \nafter: {}", before, after);
    }

    @Override
    public void delete(User user) {
        log.info("delete: {}", user);
    }
}
