package com.view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import com.model.Course;
import com.model.CourseType;
import com.service.CourseService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
/**
 * 修改课程界面
 * @author Lenovo
 *
 */
public class ModifyCourseView extends JPanel {
	private JTextField courseId;
	private JTextField courseName;
	private JTextField teacher;
	private JTextField totalCount;
	private JTextField score;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public ModifyCourseView() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("课程Id");
		lblNewLabel.setBounds(227, 173, 72, 18);
		add(lblNewLabel);
		
		courseId = new JTextField();
		courseId.setBounds(329, 170, 171, 24);
		add(courseId);
		courseId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("课程类别");
		lblNewLabel_1.setBounds(227, 289, 72, 18);
		add(lblNewLabel_1);
		
		courseName = new JTextField();
		courseName.setBounds(329, 228, 171, 24);
		add(courseName);
		courseName.setColumns(10);
		
		JLabel courseNameLabel = new JLabel("课程名称");
		courseNameLabel.setBounds(227, 231, 72, 18);
		add(courseNameLabel);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"必修课", "公共选修课"}));
		comboBox.setBounds(329, 286, 171, 24);
		add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("老师");
		lblNewLabel_3.setBounds(227, 349, 72, 18);
		add(lblNewLabel_3);
		
		teacher = new JTextField();
		teacher.setBounds(329, 346, 171, 24);
		add(teacher);
		teacher.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("课程容量");
		lblNewLabel_4.setBounds(227, 405, 72, 18);
		add(lblNewLabel_4);
		
		totalCount = new JTextField();
		totalCount.setBounds(329, 402, 171, 24);
		add(totalCount);
		totalCount.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("学分");
		lblNewLabel_5.setBounds(227, 460, 72, 18);
		add(lblNewLabel_5);
		
		score = new JTextField();
		score.setBounds(329, 457, 171, 24);
		add(score);
		score.setColumns(10);
		//给修改按钮绑定事件
		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	 Course course = new Course();
		    	 course.setId(Integer.parseInt(courseId.getText()));
		    	 course.setCourseName(courseName.getText());
		    	 course.setTotalCount(Integer.parseInt(totalCount.getText()));
		    	 course.setTeacher(teacher.getText());
		    	 course.setScore(Integer.parseInt(score.getText()));
		    	 if(comboBox.getSelectedItem().toString().equals("必修课")){
		    		 course.setCourseType(CourseType.REQUIRED_COURSE);
		    	 }else{
		    		 course.setCourseType(CourseType.PUBLIC_ELECTIVE_COURSE);	 
		    	 }
		    	 boolean flag =  CourseService.getInstance().modifyCourse(course); 
		    	 if(flag){
		    		 JOptionPane.showMessageDialog(null, "修改成功");
		    	 }else{
		    		 JOptionPane.showMessageDialog(null, "修改失败");
		    	 }
			}
		});
		btnNewButton.setBounds(329, 516, 171, 27);
		add(btnNewButton);

	}

}
