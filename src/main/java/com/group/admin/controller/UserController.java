package com.group.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;

import com.group.admin.service.UserService;
import com.group.admin.vo.UserVO;
import com.group.admin.vo.UserVO2;
import com.mysql.cj.util.StringUtils;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	//登录
	@RequestMapping("/loginUser")
	@ResponseBody
	public String login(UserVO2 user,HttpServletRequest request){
		String username=user.getUserName();
		String pwd=user.getPassWord();
		if(StringUtils.isNullOrEmpty(pwd) || StringUtils.isNullOrEmpty(username)){
			return "用户名密码不能为空 ！";
		}
		UserVO2 loginUser=userService.login(user);
		if(null==loginUser){
			return "用户名密码错误";
		}else{
			request.getSession().setAttribute("session_user",loginUser);
		}
		return "login sucess";
	}
	
	//跳转注册 
	@RequestMapping("/toRegister")
	public String toRegister(){
		return "user/register";
	}
	//注册
	@RequestMapping("/register")
	public String register(UserVO2 user){
		int result = userService.saveUser2(user);
		if(result==0){
			System.out.println("----注册失败 ----");
		}
		System.out.println("=====注册成功=====");
		return "welcome";
	}
	//注销
	@RequestMapping("/outUser")
	public String outUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute("session_user");
//		response.sendRedirect("/user/toIndex");
		return "user/index";
	}
	
	@RequestMapping("/welcome")
	public String welcome(Model model){
		return "welcome";
	}
	@RequestMapping("/toIndex")
	public String index(Model model){
		return "user/index";
	}
	
	@RequestMapping("/addUser")
	public String addUser(Model model){
		return "user_add";
	}
	
	@RequestMapping("/save")
	public String save(UserVO user,Model model){
		userService.saveUser(user);
		List<UserVO> userList = userService.getUserList();
		model.addAttribute("userList",userList);
		return "user_list";
	}
	@RequestMapping("/save2")
	public String save2(UserVO2 user,Model model){
		userService.saveUser2(user);
		List<UserVO2> userList = userService.getUserList2();
		model.addAttribute("userList",userList);
		return "user_list";
	}
	
	@RequestMapping("/getUserList")
	public String getUserList(Model model){
		List<UserVO> userList = userService.getUserList();
		model.addAttribute("userList",userList);
		return "user_list";
	}
	@RequestMapping("/getUserList2")
	public String getUserList2(Model model){
		List<UserVO2> userList = userService.getUserList2();
		model.addAttribute("userList",userList);
		return "user_list";
	}
	
	
	
	
}
