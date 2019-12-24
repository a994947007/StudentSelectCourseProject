package com.view;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import com.admin.User;


public class StudentIndexView extends JFrame {
	private JPanel contentPane;
	private JSplitPane cf;
	/**
	 * Create the frame.
	 */
	public StudentIndexView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);		
		URL url = StudentIndexView.class.getClassLoader().getResource("药品.jpg");
		JLabel label = new JLabel(new ImageIcon(url));
		JScrollPane scroll = new JScrollPane(new StudentMenuView(this));
		this.cf = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,label);//排版 左右拆分
		this.cf.setDividerLocation(150);
		this.cf.setOneTouchExpandable(true);
		this.setTitle("学生选课系统");
		this.setSize(1000,800);
		this.setLocation(500,390);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(cf);  
		this.setVisible(true);
	}

	
	public JSplitPane getCf(){
		return this.cf;
	}


}
