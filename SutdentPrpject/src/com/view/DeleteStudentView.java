package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.service.CourseService;
import com.service.UserService;
/**
 * 删除学生界面
 * @author Lenovo
 *
 */
public class DeleteStudentView extends JPanel {
	private JTextField userId;

	/**
	 * Create the panel.
	 */
	public DeleteStudentView() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("学生Id");
		lblNewLabel.setBounds(299, 158, 72, 18);
		add(lblNewLabel);
		
		userId = new JTextField();
		userId.setBounds(385, 155, 168, 24);
		add(userId);
		userId.setColumns(10);
		//给删除按钮绑定事件
		JButton btnNewButton = new JButton("删除");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	 boolean flag =  UserService.getInstance().deleteUserById(Integer.parseInt(userId.getText())); 
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
