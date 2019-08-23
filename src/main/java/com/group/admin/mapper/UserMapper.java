package com.group.admin.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.group.admin.vo.UserVO2;

@Repository
public interface UserMapper {

	List<UserVO2> getUserList();
	UserVO2 getUser(UserVO2 user);
	int addUser(UserVO2 user);
	
	
}
