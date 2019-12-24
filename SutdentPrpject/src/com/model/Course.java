package com.model;
/**
 * 课程对象
 * @author Lenovo
 *
 */
public class Course {
	private Integer id;
	private String courseName;
	private CourseType courseType;
	private String courseAddress;
	private String teacher;			//授课老师
	private Integer totalCount;		//课程容量
	private Integer currentCount;	//已选人数
	private Integer score;			//课程学分
	public Course(Integer id, String courseName, CourseType courseType,
			String courseAddress) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.courseType = courseType;
		this.courseAddress = courseAddress;
	}
	public Course() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public CourseType getCourseType() {
		return courseType;
	}
	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}
	public String getCourseAddress() {
		return courseAddress;
	}
	public void setCourseAddress(String courseAddress) {
		this.courseAddress = courseAddress;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

}
