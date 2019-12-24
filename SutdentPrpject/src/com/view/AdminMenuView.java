package com.view;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
/**
 * 管理员菜单界面，继承于JTree
 * @author Lenovo
 *
 */
public class AdminMenuView extends JTree{
	AdminMenuView(final AdminIndexView indexView) {
		MutableTreeNode root = new DefaultMutableTreeNode("菜单");
	    MutableTreeNode aNode = new DefaultMutableTreeNode("课程管理");
	    MutableTreeNode bNode = new DefaultMutableTreeNode("学生管理");
	    
	    root.insert(aNode, 0);
	    root.insert(bNode, 1);
	    aNode.insert(new DefaultMutableTreeNode("课程列表"), 0);
	    aNode.insert(new DefaultMutableTreeNode("增加课程"), 1);
	    aNode.insert(new DefaultMutableTreeNode("删除课程"), 2);
	    aNode.insert(new DefaultMutableTreeNode("修改课程"), 3);
	    
	    bNode.insert(new DefaultMutableTreeNode("学生列表"), 0);
	    bNode.insert(new DefaultMutableTreeNode("增加学生"), 1);
	    bNode.insert(new DefaultMutableTreeNode("删除学生"), 2);
	    bNode.insert(new DefaultMutableTreeNode("修改学生信息"), 3);
	    bNode.insert(new DefaultMutableTreeNode("增加学生选课记录"), 4);
	    
	    DefaultTreeModel model = new DefaultTreeModel(root);
	    super.setModel(model);
	    this.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				String type = e.getPath().getLastPathComponent().toString();
	
				if(type.equals("课程列表")){
					indexView.getCf().setRightComponent(new CourseListView());
				}else if(type.equals("增加课程")){
					indexView.getCf().setRightComponent(new AddCourseView());
				}else if(type.equals("删除课程")){
					indexView.getCf().setRightComponent(new DeleteCourseView());
				}else if(type.equals("修改课程")){
					indexView.getCf().setRightComponent(new ModifyCourseView());
				}else if(type.equals("学生列表")){
					indexView.getCf().setRightComponent(new StudentListView());
				}else if(type.equals("增加学生")){
					indexView.getCf().setRightComponent(new AddStudentView());
				}else if(type.equals("删除学生")){
					indexView.getCf().setRightComponent(new DeleteStudentView());
				}else if(type.equals("修改学生信息")){
					indexView.getCf().setRightComponent(new ModifyStudentView());
				}else if(type.equals("增加学生选课记录")){
					indexView.getCf().setRightComponent(new AddUserSelectCourse());
				}
			}
		});	
	}
}
