package com.sise.pms;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *项目名称:pms
 *类名:Node15Panel
 *类描述:基本信息管理的部门管理
 *创建人:马增群
 *创建时间:2014年11月13日 下午10:04:12
 *修改人:
 *修改时间:
 *修改备注:
 *@version 1.0.0
 */
public class Node15Panel extends JPanel implements ActionListener,ListSelectionListener{
	DeptBean dbean = new DeptBean(); 
	JLabel title;
	JPanel p1,p2,p3;
	JTable table;
	JScrollPane scrollPane;
	GridBagLayout gridL;
	GridBagConstraints gridC;
	JLabel pIdLabel,pDeptLabel,sDeptLabel;
	JTextField pIdText,pDeptText,sDeptText;
	JButton getNewId,add,update,delete,clear;
	
	String tableTitle[] = {"部门编号","一级部门","二级部门"};//表格标题
	
	String tableValues[][] = dbean.select();//表格数据从数据库获取
	
	ListSelectionModel listModel;
	
	JOptionPane op=new JOptionPane();
	
	public Node15Panel(){
		init();
	}
	public void init(){
		int width = 10;

		setLayout(new BorderLayout());
		
		gridL = new GridBagLayout();
		
		//a.上部布局
		p1 = new JPanel();
		
		title = new JLabel("部门管理");
		p1.add(title);
		
		//b.中部布局
		p2 = new JPanel();
		
		table = new JTable(tableValues,tableTitle);
		
		listModel=table.getSelectionModel();
		listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listModel.addListSelectionListener(this);
		
		scrollPane = new JScrollPane(table);
		
		p2.add(scrollPane);
		
		//c.下部布局
		p3 = new JPanel();
		p3.setLayout(gridL);
		//人员编号
		pIdLabel = new JLabel("编号:");
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 10, 10);
		gridL.setConstraints(pIdLabel, gridC);
		p3.add(pIdLabel);
		
		pIdText = new JTextField(width);
		pIdText.setEditable(false);
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 10, 10);
		gridL.setConstraints(pIdText, gridC);
		p3.add(pIdText);
		
		//一级部门
		pDeptLabel = new JLabel("一级部门:");
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 10, 10);
		gridL.setConstraints(pDeptLabel, gridC);
		p3.add(pDeptLabel);
		
		pDeptText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 10, 10);
		gridL.setConstraints(pDeptText, gridC);
		p3.add(pDeptText);
		//二级部门
		sDeptLabel = new JLabel("二级部门:");
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 2;
		gridC.insets = new Insets(5, 10, 10, 10);
		gridL.setConstraints(sDeptLabel, gridC);
		p3.add(sDeptLabel);
		
		sDeptText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 2;
		gridC.insets = new Insets(5, 10, 10, 10);
		gridL.setConstraints(sDeptText, gridC);
		p3.add(sDeptText);
		//获取新编号按钮
		ImageIcon image1=new ImageIcon("../pms/images/login.png");
		getNewId = new JButton("获取新编号",image1);
		getNewId.addActionListener(this);
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 3;
		gridC.insets = new Insets(5, 10, 10, 10);
		gridL.setConstraints(getNewId, gridC);
		p3.add(getNewId);
		//添加按钮
		ImageIcon image2=new ImageIcon("../pms/images/add.png");
		add =  new JButton("添加",image2);
		add.addActionListener(this);
		//add.setEnabled(false);
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 3;
		gridC.insets = new Insets(5, 10, 10, 10);
		gridL.setConstraints(add, gridC);
		p3.add(add);
		//修改按钮
		ImageIcon image3=new ImageIcon("../pms/images/modify.png");
		update = new JButton("修改",image3);
		update.addActionListener(this);
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 3;
		gridC.insets = new Insets(5, 10, 10, 10);
		gridL.setConstraints(update, gridC);
		p3.add(update);
		//删除按钮
		ImageIcon image4=new ImageIcon("../pms/images/no.png");
		delete = new JButton("删除",image4);
		delete.addActionListener(this);
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 3;
		gridC.insets = new Insets(5, 10, 10, 10);
		gridL.setConstraints(delete, gridC);
		p3.add(delete);
		//清空按钮
		ImageIcon image5=new ImageIcon("../pms/images/clear.png");
		clear = new JButton("清空",image5);
		clear.addActionListener(this);
		gridC = new GridBagConstraints();
		gridC.gridx = 4;
		gridC.gridy = 3;
		gridC.insets = new Insets(5, 10, 10, 10);
		gridL.setConstraints(clear, gridC);
		p3.add(clear);
		
		add("North",p1);
		add("Center",p2);
		add("South",p3);
	}
	/*
	 * 重载的动作事件方法
	 * */
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == clear){
			clearAll();//清空文本框
		}else if(e.getSource()==delete){
			dbean.deleteDeptMsg(pIdText.getText());//删除部门信息
			clearAll();
			op.showMessageDialog(null, "删除部门信息成功!");
			table.revalidate();
			
		}else if(e.getSource()==update){//修改部门信息
			dbean.update(pIdText.getText(), pDeptText.getText(), sDeptText.getText());
			clearAll();
			op.showMessageDialog(null, "修改部门信息成功!");
		}else if(e.getSource()==getNewId){//获取新的部门编号
			int i =dbean.getNewId();
			String id=Integer.toString(i);

			pIdText.setText(id);
			op.showMessageDialog(null, "获取新编号成功!");
			
		}
		
	}
	/*
	 * 清空文本框方法
	 * */
	public void clearAll(){
		pIdText.setText(null);
		pDeptText.setText(null);
		sDeptText.setText(null);
		
	}
	@Override
	
	/*
	 * 选择表格时触发这个事件
	 */
	public void valueChanged(ListSelectionEvent e) {

		int row[]=table.getSelectedRows();//表格的行
		int col[]=table.getSelectedColumns();//表格的列
		
		for(int i=0;i<row.length;i++)
			for(int j=0;j<col.length;j++){
				pIdText.setText(tableValues[row[i]][0]);
				pDeptText.setText(tableValues[row[i]][1]);
				sDeptText.setText(tableValues[row[i]][2]);
			}
			
	}
}
