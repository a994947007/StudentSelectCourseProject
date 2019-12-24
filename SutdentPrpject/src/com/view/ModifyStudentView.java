package com.view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.admin.User;
import com.service.UserService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 修改学生信息界面
 * @author Lenovo
 *
 */
public class ModifyStudentView extends JPanel {
	private JTextField userId;
	private JTextField username;
	private JTextField password;
	private JTextField name;
	private JButton btnNewButton;

	/**
	 * Create the panel.
	 */
	public ModifyStudentView() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("学生Id");
		lblNewLabel.setBounds(217, 145, 72, 18);
		add(lblNewLabel);
		
		userId = new JTextField();
		userId.setBounds(303, 142, 184, 24);
		add(userId);
		userId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("用户名");
		lblNewLabel_1.setBounds(217, 194, 72, 18);
		add(lblNewLabel_1);
		
		username = new JTextField();
		username.setBounds(303, 191, 184, 24);
		add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("密码");
		lblNewLabel_2.setBounds(217, 242, 72, 18);
		add(lblNewLabel_2);
		
		password = new JTextField();
		password.setBounds(303, 239, 184, 24);
		add(password);
		password.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("姓名");
		lblNewLabel_3.setBounds(217, 293, 72, 18);
		add(lblNewLabel_3);
		
		name = new JTextField();
		name.setBounds(303, 290, 184, 24);
		add(name);
		name.setColumns(10);
		//给修改按钮绑定事件
		btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userId.getText().equals("") || username.equals("") || password.equals("") || name.equals("")){
					JOptionPane.showMessageDialog(null, "添加不能为空");
					return;
				}
				int id = Integer.parseInt(userId.getText());
				String _username = username.getText();
				String _password = password.getText();
				String _name = name.getText();
				User user = new User(id, _username, _password, _name);
				boolean flag = UserService.getInstance().modifyUser(user);
		    	 if(flag){
		    		 JOptionPane.showMessageDialog(null, "添加成功");
		    	 }else{
		    		 JOptionPane.showMessageDialog(null, "添加失败");
		    	 }
			}
		});
		btnNewButton.setBounds(303, 364, 169, 27);
		add(btnNewButton);

	}

}
