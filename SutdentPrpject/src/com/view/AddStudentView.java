package com.view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.admin.User;
import com.admin.UserType;
import com.service.UserService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 添加学生界面
 * @author Lenovo
 *
 */
public class AddStudentView extends JPanel {
	private JTextField username;
	private JTextField password;
	private JTextField name;
	private JButton btnNewButton;

	/**
	 * Create the panel.
	 */
	public AddStudentView() {
		setLayout(null);
		
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
		//绑定事件
		btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( username.equals("") || password.equals("") || name.equals("")){
					JOptionPane.showMessageDialog(null, "添加不能为空");
					return;
				}
				String _username = username.getText();
				String _password = password.getText();
				String _name = name.getText();
				User user = new User(null, _username, _password, _name);
				user.setUserType(UserType.STUDENT);
				boolean flag = UserService.getInstance().addUser(user);
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
