package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.model.Course;
import com.service.CourseService;
import com.service.UserService;

public class AddUserSelectCourse extends JPanel {
	private JTextField textField;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public AddUserSelectCourse() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("学生Id");
		lblNewLabel.setBounds(232, 148, 72, 18);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(329, 145, 179, 24);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("课程");
		lblNewLabel_1.setBounds(232, 212, 72, 18);
		add(lblNewLabel_1);
		//绑定事件
		JButton btnNewButton = new JButton("选择课程");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("") || comboBox.getSelectedItem().toString().equals("")){
					JOptionPane.showMessageDialog(null, "选择课程信息不能为空");
					return;
				}
				boolean flag = CourseService.getInstance().selectCourseById(Integer.parseInt(textField.getText()),Integer.parseInt(comboBox.getSelectedItem().toString().split("-")[0]) );
				if(flag){
					JOptionPane.showMessageDialog(null, "选择成功");
				}else{
					JOptionPane.showMessageDialog(null, "选择失败");
				}
			}
		});
		btnNewButton.setBounds(360, 278, 113, 27);
		add(btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.setBounds(329, 209, 179, 24);
		add(comboBox);
		List<Course> list = CourseService.getInstance().list();
		for (Course c : list) {
			comboBox.addItem(c.getId() + "-" +c.getCourseName());
		}

	}

}
