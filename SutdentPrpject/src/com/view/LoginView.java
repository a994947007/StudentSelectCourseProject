package com.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.admin.User;
import com.service.UserService;
/**
 * 用户登录界面，根据用户的用户名不同，跳转到不同的登录界面
 * @author Lenovo
 *
 */
public class LoginView {

	private JFrame frame;
	private JTextField username;
	private JTextField password;
	private static ThreadLocal<User> threadLocal = new ThreadLocal<User>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView window = new LoginView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginView() {
		initialize();
	}

	private void initialize() {
		//绘制界面
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户名");
		lblNewLabel.setBounds(40, 51, 72, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setBounds(40, 134, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		username = new JTextField();
		username.setBounds(140, 48, 166, 24);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(140, 131, 166, 24);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		//添加登录按钮触发事件
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setUsername(username.getText());
				user.setPassword(password.getText());
				User u = UserService.getInstance().login(user);
				if(u == null || !u.getPassword().equals(user.getPassword())){
					JOptionPane.showMessageDialog(null, "登录失败");
					return;
				}
				switch (u.getUserType()) {
				case ADMIN:
					new AdminIndexView();
					setUser(u);
					frame.dispose();
					break;
				case STUDENT:
					 new StudentIndexView();
					setUser(u);
					frame.dispose();
					break;
				}
			}
		});
		btnNewButton.setBounds(134, 195, 113, 27);
		frame.getContentPane().add(btnNewButton);
	}
	
	//获取当前登录的用户
	public static void setUser(User user) {
		threadLocal.set(user);
	}
	//记录当前登录用户
	public static User getUser(){
		return threadLocal.get();
	}
}
