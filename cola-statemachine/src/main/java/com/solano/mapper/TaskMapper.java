package com.solano.mapper;

import com.solano.entity.TaskStateEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author github.com/solano33
 * @date 2024/7/8 00:37
 */
@Mapper
public interface TaskMapper {

    void updateState(@Param("taskId") Integer taskId, @Param("state") TaskStateEnum state);
}
