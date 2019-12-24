package com.admin;
/**
 * 用户类
 * @author Lenovo
 *
 */
public class User {
	public User(Integer id, String username, String password, String name) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
	}
	private Integer id;		//用户id
	private String username;//用户名
	private String password;//用户密码
	private String name;	//姓名
	private UserType userType;//用户类型，管理员或者学生

	public User(String username, String password, UserType userType) {
		super();
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	public User(Integer id,String username, String password, UserType userType) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
