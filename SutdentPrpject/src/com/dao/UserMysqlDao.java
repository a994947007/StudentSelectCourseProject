package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.admin.User;
import com.admin.UserType;
import com.db.DB;
import com.model.Course;
/**
 * 数据库用户表操作类
 * @author Lenovo
 *
 */
public class UserMysqlDao implements UserDao{
	@Override
	public void save(User user){
		String sql = "insert into p_user values(null,?,?,?,?)";
		Connection connection = null;
		PreparedStatement stmt = null;
		try{
			connection = DB.getConnection();
			stmt = DB.prePareStatement(connection, sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getName());
			switch(user.getUserType()){
				case ADMIN:
					stmt.setInt(4, 0);
					break;
				case STUDENT:
					stmt.setInt(4, 1);
					break;					
			}
			
			stmt.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DB.close(stmt);
			DB.close(connection);
		}
	}

	@Override
	public List<User> list() {
		List<User> list = new ArrayList<User>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from p_user";
		try{
			connection = DB.getConnection();
			stmt = DB.createStatement(connection);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				User user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("name"));
				switch(rs.getInt("userType")){
					case 0:
						user.setUserType(UserType.ADMIN);
						break;
					case 1:
						user.setUserType(UserType.STUDENT);
						break;					
				}	
				list.add(user);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DB.close(rs);
			DB.close(stmt);
			DB.close(connection);
		}
		return list;
	}

	@Override
	public User getById(Integer id) {
		User user = null;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from p_user where id = " + id;
		try{
			connection = DB.getConnection();
			stmt = DB.createStatement(connection);
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("name"));
				switch(rs.getInt("userType")){
					case 0:
						user.setUserType(UserType.ADMIN);
						break;
					case 1:
						user.setUserType(UserType.STUDENT);
						break;					
				}				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DB.close(rs);
			DB.close(stmt);
			DB.close(connection);
		}
		return user;
	}

	@Override
	public User getByUsername(String username) {
		User user = null;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from p_user where username = '" + username + "'";
		try{
			connection = DB.getConnection();
			stmt = DB.createStatement(connection);
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				user = new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getString("name"));
				switch(rs.getInt("userType")){
					case 0:
						user.setUserType(UserType.ADMIN);
						break;
					case 1:
						user.setUserType(UserType.STUDENT);
						break;					
				}				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DB.close(rs);
			DB.close(stmt);
			DB.close(connection);
		}
		return user;
	}

	@Override
	public List<Course> listSelectedCourse(Integer userId) {
		List<Course> list = new ArrayList<Course>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from p_selected_course a LEFT JOIN p_course b on a.courseId = b.id where a.userId = " + userId;
		try{
			connection = DB.getConnection();
			stmt = DB.createStatement(connection);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Course course = new Course();
				course.setId(rs.getInt("courseId"));
				course.setCourseName(rs.getString("courseName"));
				course.setTeacher(rs.getString("teacher"));
				course.setTotalCount(rs.getInt("totalCount"));
				course.setCurrentCount(rs.getInt("currentCount"));
				course.setScore(rs.getInt("score"));
				list.add(course);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DB.close(rs);
			DB.close(stmt);
			DB.close(connection);
		}
		return list;
	}

	@Override
	public boolean deleteUserById(int id) {
		String sql1 = "delete from p_user where id = ?";
		String sql2 = "delete from p_selected_course where userId  = ?";
		Connection connection = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		try{
			connection = DB.getConnection();
			connection.setAutoCommit(false);
			stmt1 = DB.prePareStatement(connection, sql1);
			stmt2 = DB.prePareStatement(connection, sql2);
			stmt1.setInt(1, id);
			stmt2.setInt(1, id);
			int n = stmt1.executeUpdate();
			stmt2.executeUpdate();
			if(n > 0){
				return true;
			}
			connection.commit();
		}catch(Exception e){
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DB.close(stmt1);
			DB.close(stmt2);
			DB.close(connection);
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sql = "update p_user set username=?,password=?,name=? where id=?";
		try {
			connection = DB.getConnection();
			pstmt = DB.prePareStatement(connection, sql);
			pstmt.setString(1,user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setInt(4, user.getId());
			int n = pstmt.executeUpdate();
			if(n > 0){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DB.close(pstmt);
			DB.close(connection);	
		}
		return false;
	}
}
