package com.group.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.admin.dao.UserDao;
import com.group.admin.mapper.UserMapper;
import com.group.admin.vo.UserVO;
import com.group.admin.vo.UserVO2;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserMapper userMapper;
	
	
	public void saveUser(UserVO user){
		userDao.save(user);
	}
	
	public List<UserVO> getUserList(){
		return userDao.findAll();
	}
	
	public int saveUser2(UserVO2 user){
		return userMapper.addUser(user);
	}
	
	public List<UserVO2> getUserList2(){
		return userMapper.getUserList();
	}
	
	public UserVO2 login(UserVO2 user){
		return userMapper.getUser(user);
	}
	
	
}
