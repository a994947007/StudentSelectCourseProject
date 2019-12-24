package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.db.DB;
import com.model.Course;
import com.model.CourseType;
/**
 * 数据库课程数据表操作类
 * @author Lenovo
 *
 */
public class CourseMysqlDao implements CourseDao{

	//保存一个课程
	@Override
	public void save(Course course) {
		String sql = "insert into p_course values(null,?,?,?,?,0,?)";
		Connection connection = null;
		PreparedStatement stmt = null;
		try{
			connection = DB.getConnection();
			stmt = DB.prePareStatement(connection, sql);
			stmt.setString(1, course.getCourseName());
			stmt.setString(3, course.getTeacher());
			stmt.setInt(4, course.getTotalCount());
			stmt.setInt(5, course.getScore());
			switch(course.getCourseType()){
				case REQUIRED_COURSE:
					stmt.setInt(2, 0);
					break;
				case PUBLIC_ELECTIVE_COURSE:
					stmt.setInt(2, 1);
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

	//获取数据库中课程表
	@Override
	public Vector<Vector<String>> tableList() {
		Vector<Vector<String>> list = new Vector<Vector<String>>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from p_course";
		try{
			connection = DB.getConnection();
			stmt = DB.createStatement(connection);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Vector<String> row = new Vector<String>();
				row.add("" + rs.getInt("id"));
				row.add("" + rs.getString("courseName"));
				row.add("" + rs.getInt("totalCount"));
				row.add("" + rs.getInt("currentCount"));
				row.add("" + rs.getString("teacher"));
				row.add("" + rs.getInt("score"));
				list.add(row);
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

	//根据课程id获取课程表
	@Override
	public Course getCourseById(Integer id) {
		Course course = null;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from p_course where id = " + id;
		try{
			connection = DB.getConnection();
			stmt = DB.createStatement(connection);
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				course = new Course();
				course.setId(rs.getInt("id"));
				course.setCourseName(rs.getString("courseName"));
				course.setTeacher(rs.getString("teacher"));
				course.setTotalCount(rs.getInt("totalCount"));
				course.setCurrentCount(rs.getInt("currentCount"));
				course.setScore(rs.getInt("score"));
				switch(rs.getInt("courseType")){
					case 0:
						course.setCourseType(CourseType.REQUIRED_COURSE);
						break;
					case 1:
						course.setCourseType(CourseType.PUBLIC_ELECTIVE_COURSE);
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
		return course;	
	}

	//添加一条选课记录
	@Override
	public void addSelectedCourseRecord(Integer userId, Integer courseId) {
		Connection connection = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql1 = "insert into p_selected_course values(null,?,?)";
		String sql2 = "update p_course set currentCount = currentCount + 1 where id = ?";
		try {
			connection = DB.getConnection();
			connection.setAutoCommit(false);
			pstmt1 = DB.prePareStatement(connection, sql1);
			pstmt1.setInt(1, userId);
			pstmt1.setInt(2, courseId);
			pstmt2 = DB.prePareStatement(connection, sql2);
			pstmt2.setInt(1, courseId);
			pstmt1.execute();
			pstmt2.execute();
			connection.commit();
		}catch(Exception e){
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DB.close(pstmt1);
			DB.close(pstmt2);
			DB.close(connection);
		}
	}

	//删除一条选课记录
	@Override
	public void deleteSelectedCourseRecord(Integer userId, int courseId) {
		Connection connection = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql1 = "delete from p_selected_course where userId = ? and courseId = ?";
		String sql2 = "update p_course set currentCount = currentCount - 1 where id = ?";
		try {
			connection = DB.getConnection();
			connection.setAutoCommit(false);
			pstmt1 = DB.prePareStatement(connection, sql1);
			pstmt1.setInt(1, userId);
			pstmt1.setInt(2, courseId);
			pstmt2 = DB.prePareStatement(connection, sql2);
			pstmt2.setInt(1, courseId);
			pstmt1.execute();
			pstmt2.execute();
			connection.commit();
		}catch(Exception e){
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DB.close(pstmt1);
			DB.close(pstmt2);
			DB.close(connection);	
		}
	}

	//根据课程id删除课程
	@Override
	public boolean deleteCourseById(Integer id) {
		String sql1 = "delete from p_course where id = ?";
		String sql2 = "delete from p_selected_course where courseId  = ?";
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

	//根据课程名称获取课程
	@Override
	public Course getCourseByCourseName(String name) {
		Course course = null;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from p_course where courseName = '" + name + "'";
		try{
			connection = DB.getConnection();
			stmt = DB.createStatement(connection);
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				course = new Course();
				course.setId(rs.getInt("id"));
				course.setCourseName(rs.getString("courseName"));
				course.setTeacher(rs.getString("teacher"));
				course.setTotalCount(rs.getInt("totalCount"));
				course.setCurrentCount(rs.getInt("currentCount"));
				course.setScore(rs.getInt("score"));
				switch(rs.getInt("courseType")){
					case 0:
						course.setCourseType(CourseType.REQUIRED_COURSE);
						break;
					case 1:
						course.setCourseType(CourseType.PUBLIC_ELECTIVE_COURSE);
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
		return course;	
	}

	//更新课程信息
	@Override
	public boolean updateCourse(Course course) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sql = "update p_course set courseName=?,courseType=?,teacher=?,totalCount=?,score=? where id=?";
		try {
			connection = DB.getConnection();
			pstmt = DB.prePareStatement(connection, sql);
			pstmt.setString(1,course.getCourseName());
			pstmt.setString(3, course.getTeacher());
			pstmt.setInt(4, course.getTotalCount());
			pstmt.setInt(5, course.getScore());
			pstmt.setInt(6, course.getId());
			switch(course.getCourseType()){
				case REQUIRED_COURSE:
					pstmt.setInt(2, 0);
					break;
				case PUBLIC_ELECTIVE_COURSE:
					pstmt.setInt(2, 1);
					break;
			}
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

	//获取课程列表
	@Override
	public List<Course> list() {
		List<Course> list = new ArrayList<Course>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from p_course";
		try{
			connection = DB.getConnection();
			stmt = DB.createStatement(connection);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Course course = new Course();
				course.setId(rs.getInt("id"));
				course.setCourseName(rs.getString("courseName"));
				course.setTotalCount(rs.getInt("totalCount"));
				course.setCurrentCount(rs.getInt("currentCount"));
				course.setTeacher(rs.getString("teacher"));
				course.setScore(rs.getInt("score"));
				switch(rs.getInt("courseType")){
					case 0:
						course.setCourseType(CourseType.REQUIRED_COURSE);
						break;
					case 1:
						course.setCourseType(CourseType.PUBLIC_ELECTIVE_COURSE);
						break;					
				}	
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
}
