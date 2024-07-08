package com.solano.user.mapper;

import com.solano.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author github.com/solano33
 * @date 2024/5/24 19:54
 */
@Mapper
public interface UserMapper {

	List<User> selectAll();
}
