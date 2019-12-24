package com.dao;

import java.util.List;

import com.admin.User;
import com.model.Course;
/*
 * 数据库用户表操作接口
 */
public interface UserDao {

	public void save(User user);		//保存一个用户
		
	public User getById(Integer id);	//根据用户id读取用户
	
	public User getByUsername(String username);	//根据用户名读取用户
	
	public List<User> list();	//获取用户列表
	
	public List<Course> listSelectedCourse(Integer userId);	//根据用户id获取当前用户已选课程

	public boolean deleteUserById(int id);	//根据用户id删除用户

	public boolean updateUser(User user);	//更新用户信息
}
