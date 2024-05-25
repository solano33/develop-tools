package com.solano.mapper;

import com.solano.entity.User;

import java.util.List;

/**
 * @author github.com/solano33
 * @date 2024/5/24 19:54
 */
public interface UserMapper {
	List<User> selectAll();
}
