package com.view;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.service.CourseService;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
/**
 * 学生已选课程界面
 * @author Lenovo
 *
 */
public class StudentAlreadySelectedCourse extends JPanel {
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public StudentAlreadySelectedCourse() {
		setLayout(null);

		model = new DefaultTableModel();
		model.addColumn("课程号");
		model.addColumn("课程名称");
		model.addColumn("上课老师");
		model.addColumn("课程学分");
		model.addColumn("具体操作");
		table = new JTable();
		table.setModel(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(39, 40, 753, 554);
		add(scroll);
		
		//绑定事件
		JButton selectCourseBtn = new JButton("退选");
		selectCourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			      for(int rowIndex : table.getSelectedRows()){
			    	 boolean flag =  CourseService.getInstance().unSelectCourseById(LoginView.getUser().getId(),Integer.parseInt(table.getValueAt(rowIndex, 0).toString())); 
			    	 if(flag){
			    		 model.removeRow(rowIndex);
			    		 JOptionPane.showMessageDialog(null, "退选成功");
			    	 }else{
			    		 JOptionPane.showMessageDialog(null, "退选失败");
			    	 }
			      }	
			}
		});
		selectCourseBtn.setBounds(595, 624, 125, 35);
		add(selectCourseBtn);

		//加载数据
		Vector<Vector<String>> rows = CourseService.getInstance().selectedCourseList(LoginView.getUser().getId());
		for (Vector<String> row : rows) {
			model.addRow(row);
		}
		

		table.getColumnModel().getColumn(4).setCellRenderer(new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value,
					boolean isSelected, boolean hasFocus,final int row, int column) {
				JCheckBox box = new JCheckBox();
				box.setSelected(hasFocus);
				box.setHorizontalAlignment((int) 0.5f);
				return box;
			}
		});

	}
}
