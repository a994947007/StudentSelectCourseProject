package com.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.model.Course;
import com.service.CourseService;

public class CourseListView extends JPanel {
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public CourseListView() {
		setLayout(null);

		model = new DefaultTableModel();
		model.addColumn("课程号");
		model.addColumn("课程名称");
		model.addColumn("课程容量");
		model.addColumn("已选人数");
		model.addColumn("上课老师");
		model.addColumn("课程学分");
		model.addColumn("具体操作");
		table = new JTable();
		table.setModel(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(39, 40, 753, 554);
		add(scroll);
		
		JButton deleteCourseBtn = new JButton("删除课程");
		deleteCourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			      for(int rowIndex : table.getSelectedRows()){
			    	 boolean flag =  CourseService.getInstance().deleteCourseById(Integer.parseInt(table.getValueAt(rowIndex, 0).toString())); 
			    	 if(flag){
			    		 model.removeRow(rowIndex);
			    		 JOptionPane.showMessageDialog(null, "删除成功");
			    	 }else{
			    		 JOptionPane.showMessageDialog(null, "删除失败");
			    	 }
			      }	
			}
		});
		deleteCourseBtn.setBounds(395, 624, 125, 35);
		add(deleteCourseBtn);		

		Vector<Vector<String>> rows = CourseService.getInstance().tableList();
		for (Vector<String> row : rows) {
			model.addRow(row);
		}
		

		table.getColumnModel().getColumn(6).setCellRenderer(new TableCellRenderer() {
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
