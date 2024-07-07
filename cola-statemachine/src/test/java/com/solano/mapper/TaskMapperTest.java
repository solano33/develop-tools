package com.solano.mapper;

import com.solano.entity.TaskStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Resource
    private TaskMapper taskMapper;

    @Test
    public void test() {
        taskMapper.updateState(1, TaskStateEnum.progress);
    }

}