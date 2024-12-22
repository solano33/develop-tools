package com.solano.user.service;

import com.solano.user.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author github.com/solano33
 * @date 2024/12/22 18:00
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public void update() {
        userMapper.update(1, 11);
        int i = 1/0;
        userMapper.update(2, 22);
    }
}
