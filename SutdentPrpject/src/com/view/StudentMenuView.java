package com.view;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
/**
 * 学生界面菜单
 * @author Lenovo
 *
 */
public class StudentMenuView extends JTree{
	StudentMenuView(final StudentIndexView indexView) {
		MutableTreeNode root = new DefaultMutableTreeNode("菜单");
	    MutableTreeNode aNode = new DefaultMutableTreeNode("课程管理");
	    
	    root.insert(aNode, 0);
	    aNode.insert(new DefaultMutableTreeNode("网上选课"), 0);
	    aNode.insert(new DefaultMutableTreeNode("已选课程"), 1);
	    
	    DefaultTreeModel model = new DefaultTreeModel(root);
	    super.setModel(model);
	    this.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				String type = e.getPath().getLastPathComponent().toString();
	
				if(type.equals("网上选课")){
					indexView.getCf().setRightComponent(new StudentSelectCourse());
				}else if(type.equals("已选课程")){
					indexView.getCf().setRightComponent(new StudentAlreadySelectedCourse());
				}
			}
		});	
	}
}
