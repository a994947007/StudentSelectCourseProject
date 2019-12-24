package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.service.CourseService;
/**
 * 删除课程界面
 * @author Lenovo
 *
 */
public class DeleteCourseView extends JPanel {
	private JTextField courseId;

	/**
	 * Create the panel.
	 */
	public DeleteCourseView() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("课程Id");
		lblNewLabel.setBounds(299, 158, 72, 18);
		add(lblNewLabel);
		
		courseId = new JTextField();
		courseId.setBounds(385, 155, 168, 24);
		add(courseId);
		courseId.setColumns(10);
		//给删除按钮绑定事件
		JButton btnNewButton = new JButton("删除");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	 boolean flag =  CourseService.getInstance().deleteCourseById(Integer.parseInt(courseId.getText())); 
		    	 if(flag){
		    		 JOptionPane.showMessageDialog(null, "删除成功");
		    	 }else{
		    		 JOptionPane.showMessageDialog(null, "删除失败");
		    	 }
			}
		});
		btnNewButton.setBounds(400, 219, 113, 27);
		add(btnNewButton);

	}

}
