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
import javax.swing.text.TableView;

/**
 *项目名称:pms
 *类名:Node13Panel
 *类描述:基本信息管理的删除人员信息
 *创建人:马增群
 *创建时间:2014年11月13日 下午10:04:06
 *修改人:
 *修改时间:
 *修改备注:
 *@version 1.0.0
 */
public class Node13Panel extends JPanel implements ActionListener,ListSelectionListener{
	PersonBean pbean = new PersonBean();
	JLabel title;
	JButton delete;//删除按钮
	JPanel p1,p2,p3;
	JTable table;//表格
	JScrollPane scrollPane;
	GridBagLayout gridL;
	GridBagConstraints gridC;
	JLabel pIdLabel,nameLabel,deptLabel;
	JTextField pIdText,nameText,deptText;
	
	//表格标题
	String tableTitle[] = {"编号","姓名","出生年月","民族","地址","部门"};
	//表格的数据，从数据库获取
	String [][]tableValues = pbean.select();
	//设置表格选择模式
	ListSelectionModel listModel=null;
	
	public Node13Panel(){
	
		init();
	}
	//初始组件
	public void init(){
		int width = 5;//JTextField组件的宽度
		
		setLayout(new BorderLayout());//设置布局为BorderLayout
		
		gridL = new GridBagLayout();
		
		//a.上部布局
		p1 = new JPanel();
		
		title = new JLabel("删除人员信息");
		p1.add(title);
		
		//b.中部布局
		p2 = new JPanel();
		//创建表格
		table = new JTable(tableValues,tableTitle);
		
		//设置表格
		listModel = table.getSelectionModel();
		listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listModel.addListSelectionListener(this);
		
		//将表格添加到滚动面板
		scrollPane = new JScrollPane(table);
		
		p2.add(scrollPane);
		
		//c.下部布局
		p3 = new JPanel();
		p3.setLayout(gridL);
		//编号
		pIdLabel = new JLabel("编号:");
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(pIdLabel, gridC);
		p3.add(pIdLabel);
		
		pIdText = new JTextField(width);
		pIdText.setEditable(false);
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(pIdText, gridC);
		p3.add(pIdText);
		//姓名
		nameLabel = new JLabel("姓名:");
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(nameLabel, gridC);
		p3.add(nameLabel);
		
		nameText = new JTextField(width);
		nameText.setEditable(false);
		gridC = new GridBagConstraints();
		gridC.gridx = 4;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(nameText, gridC);
		p3.add(nameText);
		//部门
		deptLabel = new JLabel("部门:");
		gridC = new GridBagConstraints();
		gridC.gridx = 5;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(deptLabel, gridC);
		p3.add(deptLabel);
		
		deptText = new JTextField(10);
		deptText.setEditable(false);
		gridC = new GridBagConstraints();
		gridC.gridx = 6;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(deptText, gridC);
		p3.add(deptText);
		//删除按钮
		ImageIcon image1=new ImageIcon("../pms/images/no.png");
		delete = new JButton("删除",image1);
		delete.addActionListener(this);//添加动作事件
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 2;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(delete, gridC);
		p3.add(delete);
		
		//添加到主面板
		add("North",p1);
		add("Center",p2);
		add("South",p3);
	}
	
	/*
	 * 
	 * 重载的动作事件方法
	 * */
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==delete){//选择删除按钮
			pbean.delete(pIdText.getText());//删除人员信息
			JOptionPane op = new JOptionPane();
			op.showMessageDialog(null, "删除人员信息成功!");
			//文本框清空
			pIdText.setText(null);
			nameText.setText(null);
			deptText.setText(null);
		}
		
	}
	@Override
	/*
	 * 重载的表格选择事件
	 * 
	 * */
	public void valueChanged(ListSelectionEvent e) {
		int row[]=table.getSelectedRows();//表格的行
		int col[]=table.getSelectedColumns();//表格的列
		
		for(int i=0;i<row.length;i++)
			for(int j=0;j<col.length;j++){
				pIdText.setText(tableValues[row[i]][0]);
				nameText.setText(tableValues[row[i]][1]);
				deptText.setText(tableValues[row[i]][5]);
				
			}	
	}	
}
