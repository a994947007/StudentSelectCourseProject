package com.service;

import java.util.List;
import java.util.Vector;

import com.admin.User;
import com.admin.UserType;
import com.dao.UserDao;
import com.dao.UserMysqlDao;
/**
 * 用户服务类
 * @author Lenovo
 *
 */
public class UserService {
	private volatile static UserService service = null;
	private UserDao dao = new UserMysqlDao();
	private UserService(){}
	static{
		if(service == null){
			synchronized (UserService.class) {
				if(service == null){
					service = new UserService();
				}
			}
		}
	}
	//获取当前类单例
	public static UserService getInstance(){
		return service;
	}
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	public User login(User user){
		User u = dao.getByUsername(user.getUsername());
		return u;
	}

	/**
	 * 根据ID删除用户
	 * @param id 用户id
	 * @return
	 */
	public boolean deleteUserById(int id) {
		return dao.deleteUserById(id);
	}

	/**
	 * 获取用户列表
	 * @return
	 */
	public Vector<Vector<String>> tableList() {
		List<User> list = dao.list();
		Vector<Vector<String>> table = new Vector<Vector<String>>();
		for (User user : list) {		//遍历用户列表
			if(user.getUserType().equals(UserType.STUDENT)){
				Vector<String> row = new Vector<String>();
				row.add("" + user.getId());
				row.add(user.getUsername());
				row.add(user.getName());
				table.add(row);			
			}
		}
		return table;
	}
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user) {
		User u = dao.getByUsername(user.getUsername());
		if(u == null){
			dao.save(user);
			return true;
		}
		return false;
	}
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public boolean modifyUser(User user){
		return dao.updateUser(user);
	}
}
