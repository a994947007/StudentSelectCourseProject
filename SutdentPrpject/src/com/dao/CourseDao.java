package com.dao;

import java.util.List;
import java.util.Vector;

import com.model.Course;
/**
 * 操作课程数据表的接口
 * @author Lenovo
 *
 */
public interface CourseDao {

	public void save(Course course);		//保存一个课程
	public Vector<Vector<String>> tableList();	//获取课程表
	public Course getCourseById(Integer id);	//根据id获取某个课程
	public void addSelectedCourseRecord(Integer userId,Integer courseId);	//根据用户id和课程id增加选课记录
	public void deleteSelectedCourseRecord(Integer userId, int courseId);	//删除选课记录
	public boolean deleteCourseById(Integer id);		//删除课程
	public Course getCourseByCourseName(String name);	//根据课程名获取课程
	public boolean updateCourse(Course course);	//更新课程信息
	public List<Course> list();	//获取课程列表

}
