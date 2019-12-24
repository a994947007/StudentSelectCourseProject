package com.service;

import java.util.List;
import java.util.Vector;

import com.dao.CourseDao;
import com.dao.CourseMysqlDao;
import com.dao.UserDao;
import com.dao.UserMysqlDao;
import com.model.Course;
/**
 * 课程操作类
 * @author Lenovo
 *
 */
public class CourseService {
	private CourseDao dao = new CourseMysqlDao();
	private UserDao udao = new UserMysqlDao();
	private CourseService() {
	}
	private static class Instance{
		public static CourseService instance;
		static{
			instance = new CourseService();
		}
	}
	//获取当前类的单例
	public static CourseService getInstance(){
		return Instance.instance;
	}
	//获取课程表
	public Vector<Vector<String>> tableList(){
		return dao.tableList();
	}
	//以列表的方式获取课程
	public List<Course> list(){
		return dao.list();
	}
	//获取已经选择的课程
	public boolean selectCourseById(Integer userId,Integer id){
		Course course = dao.getCourseById(id);
		if(course  != null && course.getCurrentCount() < course.getTotalCount()){	//课程存在且未满
			List<Course> list = udao.listSelectedCourse(userId);
			for (Course c : list) {
				System.out.println(c.getId());
				if( c.getId() == id){
					return false;	//已经选课不能再选
				}
			}
			dao.addSelectedCourseRecord(userId, course.getId());
			return true;
		}
		return false;
	}
	/**
	 * 获取用户选择的课程
	 * @param userId
	 * @return
	 */
	public Vector<Vector<String>> selectedCourseList(Integer userId){
		List<Course> list = udao.listSelectedCourse(userId);
		Vector<Vector<String>> vs = new Vector<Vector<String>>();
		for (Course course : list) {
			Vector<String> row = new Vector<String>();
			row.add("" + course.getId());
			row.add("" + course.getCourseName());
			row.add("" + course.getTeacher());
			row.add("" + course.getScore());
			vs.add(row);
		}
		return vs;
	}
	/**
	 * 取消用户选择的课程
	 * @param userId
	 * @param courseId
	 * @return
	 */
	public boolean unSelectCourseById(Integer userId, int courseId) {
		dao.deleteSelectedCourseRecord(userId, courseId);
		return true;
	}
	/**
	 * 根据Id选择课程
	 * @param id
	 * @return
	 */
	public boolean deleteCourseById(Integer id) {
		return dao.deleteCourseById(id);
	}
	/**
	 * 添加课程
	 * @param course
	 * @return
	 */
	public boolean addCourse(Course course) {
		Course c = dao.getCourseByCourseName(course.getCourseName());
		if(c == null){	//如果课程不存在则添加
			dao.save(course);
			return true;
		}
		return false;
	}

	public boolean modifyCourse(Course course) {
		return dao.updateCourse(course);
	}
}
