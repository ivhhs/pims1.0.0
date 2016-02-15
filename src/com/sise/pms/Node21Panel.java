package com.sise.pms;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
 *类名:Node21Panel
 *类描述:人员调动
 *创建人:马增群
 *创建时间:2014年11月13日 下午10:04:16
 *修改人:
 *修改时间:
 *修改备注:
 *@version 1.0.0
 */
public class Node21Panel extends JPanel implements ActionListener,ItemListener,ListSelectionListener{
	PersonBean pbean=new PersonBean();
	DeptBean dbean = new DeptBean();
	HistrjnBean hbean=new HistrjnBean();

	String deptId="1";
	String dDept;//一级部门
	String sDept;//二级部门
	
	JLabel title;
	JPanel p1,p2,p3;
	JTable table;
	JComboBox newDeptCom;//新部门
	JScrollPane scrollPane;
	GridBagLayout gridL;
	GridBagConstraints gridC;
	JButton newDeptBtn,clear;
	JTextField nameText,oriDeptText;
	
	JLabel nameLabel,oriDeptLabel,newDeptLabel;
	
	String tableTitle[] = {"工号","姓名","性别","部门","薪酬","考核信息"};
	
	String tableValues[][] = dbean.searchMsg();//获取数据库数据
	
	ListSelectionModel listModel;
	
	JOptionPane op=new JOptionPane();
	
	String oriDeptId="1";
	
	
	
	public Node21Panel(){
		init();
	}
	public void init(){
		int width = 5;
		
		setLayout(new BorderLayout());
		gridL = new GridBagLayout();
		//a.上部布局
		p1 = new JPanel();
		
		title = new JLabel("人员调动");
		p1.add(title);
		//b.中部布局
		p2 = new JPanel();
		
		//创建表格
		table = new JTable(tableValues,tableTitle);
		listModel=table.getSelectionModel();
		listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listModel.addListSelectionListener(this);
		//将表格加到滚动面板
		scrollPane = new JScrollPane(table);
		
		p2.add(scrollPane);
		//c.下部布局
		p3 = new JPanel();
		p3.setLayout(gridL);
		//姓名
		nameLabel = new JLabel("姓名");
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(nameLabel, gridC);
		p3.add(nameLabel);
		
		nameText = new JTextField(width);
		nameText.setEditable(false);
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(nameText, gridC);
		p3.add(nameText);
		//原部门
		oriDeptLabel = new JLabel("原部门:");
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(oriDeptLabel, gridC);
		p3.add(oriDeptLabel);
		
		oriDeptText = new JTextField(10);
		oriDeptText.setEditable(false);
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(oriDeptText, gridC);
		p3.add(oriDeptText);
	//新部门
		newDeptLabel = new JLabel("新部门:");
		gridC = new GridBagConstraints();
		gridC.gridx = 4;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(newDeptLabel, gridC);
		p3.add(newDeptLabel);
		
		String newDept[] = dbean.searchAllForDept();
		newDeptCom = new JComboBox(newDept);//将数据库的数据加到JComboBox部件
		newDeptCom.addItemListener(this);//添加JComboBox事件
		newDeptCom.setEditable(false);
		gridC = new GridBagConstraints();
		gridC.gridx = 5;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(newDeptCom, gridC);
		p3.add(newDeptCom);
		//调入新部门按钮
		ImageIcon image1=new ImageIcon("../pms/images/login.png");
		newDeptBtn = new JButton("调入新部门",image1);
		newDeptBtn.addActionListener(this);
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 2;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(newDeptBtn, gridC);
		p3.add(newDeptBtn);
		ImageIcon image2=new ImageIcon("../pms/images/clear.png");
		//清空信息按钮
		clear = new JButton("清空信息",image2);
		clear.addActionListener(this);
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 2;
		gridC.insets = new Insets(5, 10, 5, 10);
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
		}else if(e.getSource()==newDeptBtn){
			pbean.updateDept(PersonId, deptId);//更新部门信息
			
			String jourNoStr=""+hbean.countJourNo();//获取流水号
			
			Calendar now = new GregorianCalendar();
			SimpleDateFormat formatter=new SimpleDateFormat();
			formatter.applyPattern("yyyy-MM-dd HH-mm-ss");//设置事件格式
			String regDate=""+formatter.format(now.getTime());//获取系统时间
			
			String chgTime=""+hbean.countChgTime("人员调动", PersonId);//获取数据库改变次数
			oriDeptId=pbean.getDeptId(PersonId);
					
			hbean.add(jourNoStr, "人员调动",oriDeptId,deptId, regDate, chgTime,PersonId);
			table.revalidate();
			op.showMessageDialog(null,"人员调动成功!");
			clearAll();
			
		}
	}
	/*
	 * 清空文本框方法
	 */
	public void clearAll(){
		nameText.setText(null);
		oriDeptText.setText(null);
		
		
	}
	String PersonId;
	@Override
	/*
	 * 表格选中事件方法
	 */
	public void valueChanged(ListSelectionEvent e) {
		int row[]=table.getSelectedRows();//表格的行
		int col[]=table.getSelectedColumns();//表格的列
		
		for(int i=0;i<row.length;i++){
			for(int j=0;j<col.length;j++){
			
				nameText.setText(tableValues[row[i]][1]);//获取表格的信息
				
				oriDeptText.setText(tableValues[row[i]][3]);//原来的部门
				
				PersonId=tableValues[row[i]][0];//人员编号
			}
		}
	}
	

	@Override
	/*
	 * 下拉菜单事件处理
	 */
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED){
			String s=""+e.getItem();//获取字符串
			
			int i = s.indexOf("-");
			
			deptId = s.substring(0,i);
		}
		
	}
}
