package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.model.Course;
import com.model.CourseType;
import com.service.CourseService;
/**
 * 添加课程界面
 * @author Lenovo
 *
 */
public class AddCourseView extends JPanel {
	private JTextField courseName;
	private JTextField teacher;
	private JTextField totalCount;
	private JTextField score;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public AddCourseView() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("课程名称");
		lblNewLabel.setBounds(242, 145, 72, 18);
		add(lblNewLabel);
		
		courseName = new JTextField();
		courseName.setBounds(342, 142, 195, 24);
		add(courseName);
		courseName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("老师");
		lblNewLabel_1.setBounds(242, 253, 72, 18);
		add(lblNewLabel_1);
		
		teacher = new JTextField();
		teacher.setBounds(342, 250, 195, 24);
		add(teacher);
		teacher.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("课程容量");
		lblNewLabel_2.setBounds(242, 319, 72, 18);
		add(lblNewLabel_2);
		
		totalCount = new JTextField();
		totalCount.setBounds(342, 316, 195, 24);
		add(totalCount);
		totalCount.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("学分");
		lblNewLabel_3.setBounds(242, 381, 72, 18);
		add(lblNewLabel_3);
		
		score = new JTextField();
		score.setBounds(342, 378, 195, 24);
		add(score);
		score.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"必修课", "公共选修课"}));
		comboBox.setBounds(342, 197, 195, 24);
		add(comboBox);
		
		JLabel lblNewLabel_5 = new JLabel("课程类型");
		lblNewLabel_5.setBounds(242, 200, 72, 18);
		add(lblNewLabel_5);
		
		//给添加按钮绑定添加事件
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Course course = new Course();
				if(courseName.getText().equals("") || teacher.getText().equals("") || totalCount.getText().equals("") || score.getText().equals("")){
					JOptionPane.showMessageDialog(null, "课程信息都不能为空");
				}
				course.setCourseName(courseName.getText());
				course.setTeacher(teacher.getText());
				course.setTotalCount(Integer.parseInt(totalCount.getText()));
				course.setScore(Integer.parseInt(score.getText()));
				if(comboBox.getSelectedItem().equals("必修课")){		//如果是公共课，则设置类型为公共课
					course.setCourseType(CourseType.REQUIRED_COURSE);
				}else if(comboBox.getSelectedItem().equals("公共选修课")){
					course.setCourseType(CourseType.PUBLIC_ELECTIVE_COURSE);				
				}
				boolean flag = CourseService.getInstance().addCourse(course);
		    	 if(flag){//是否添加成功
		    		 JOptionPane.showMessageDialog(null, "添加成功");
		    	 }else{
		    		 JOptionPane.showMessageDialog(null, "添加失败");
		    	 }
			}
		});
		btnNewButton.setBounds(336, 453, 174, 27);
		add(btnNewButton);

	}
}
