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

import com.service.UserService;

public class StudentListView extends JPanel {
	private JTable table;
	private DefaultTableModel model;
	
	/**
	 * Create the panel.
	 */
	public StudentListView() {
		setLayout(null);

		model = new DefaultTableModel();
		model.addColumn("学生Id");
		model.addColumn("学生用户名");
		model.addColumn("学生姓名");
		model.addColumn("具体操作");
		table = new JTable();
		table.setModel(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(39, 40, 753, 554);
		add(scroll);
		
		JButton deleteCourseBtn = new JButton("删除学生");
		deleteCourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			      for(int rowIndex : table.getSelectedRows()){
			    	 boolean flag =  UserService.getInstance().deleteUserById(Integer.parseInt(table.getValueAt(rowIndex, 0).toString())); 
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

		Vector<Vector<String>> rows = UserService.getInstance().tableList();
		for (Vector<String> row : rows) {
			model.addRow(row);
		}
		

		table.getColumnModel().getColumn(3).setCellRenderer(new TableCellRenderer() {
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
