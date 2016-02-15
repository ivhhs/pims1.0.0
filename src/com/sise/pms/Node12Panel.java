package com.sise.pms;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *项目名称:pms
 *类名:Node12Panel
 *类描述:基本信息管理的修改人员信息
 *创建人:马增群
 *创建时间:2014年11月13日 下午10:04:03
 *修改人:
 *修改时间:
 *修改备注:
 *@version 1.0.0
 */
public class Node12Panel extends JPanel implements ActionListener,ItemListener{
	
	

	String DeptId = "1";//部门编号
	
	String salary = "0";//工资
	
	String assess = "未考核";//考核信息
	
	String[] p = null;
	
	String PersonId = "";//人员编号
	
	PersonBean pbean = new PersonBean();
	
	JLabel title;
	JButton update,clear;	//修改，清空
	JPanel p1,p2;
	GridBagLayout gridL;
	GridBagConstraints gridC;
	JComboBox msg;//选择人员信息
	JTextField pIdText,nameText,sexText,birText,natText,adText,otherText;
	JLabel pIdLabel,nameLabel,sexLabel,birLabel,natLabel,adLabel,msgLabel,otherLabel;
	public Node12Panel(){
		init();
	
	}
	
	public void init(){
		
		setLayout(new BorderLayout());
		
		gridL = new GridBagLayout();
		
		int width = 8;//JTextField的宽度,便于代码修改
		//a.上部布局
		p1 = new JPanel();
		title = new JLabel("修改人员信息");
		p1.add(title);
		//b.中部布局
		p2 = new JPanel();
		p2.setLayout(gridL);
		//人员编号
		pIdLabel = new JLabel("人员编号:");
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 1;
		gridC.insets = new Insets(10, 1, 20, 5);
		gridL.setConstraints(pIdLabel, gridC);
		p2.add(pIdLabel);
		
		pIdText = new JTextField(width);
		pIdText.setEditable(false);
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 1;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(pIdText, gridC);
		p2.add(pIdText);
		//人员姓名
		nameLabel = new JLabel("人员姓名:");
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 1;
		gridC.insets = new Insets(20, 10, 20, 5);
		gridL.setConstraints(nameLabel, gridC);
		p2.add(nameLabel);
		
		nameText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 1;
		gridC.insets = new Insets(20, 20, 20, 5);
		gridL.setConstraints(nameText, gridC);
		p2.add(nameText);
		//性别
		sexLabel = new JLabel("性别		:");
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 2;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(sexLabel, gridC);
		p2.add(sexLabel);
		
		sexText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 2;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(sexText, gridC);
		p2.add(sexText);
		
		//出生年月
		birLabel = new JLabel("出生年月:");
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 2;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(birLabel, gridC);
		p2.add(birLabel);
		
		birText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 2;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(birText, gridC);
		p2.add(birText);
		//民族
		natLabel = new JLabel("民族		:");
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 3;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(natLabel, gridC);
		p2.add(natLabel);
		
		natText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 3;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(natText, gridC);
		p2.add(natText);
		
	//地址
		adLabel = new JLabel("地址	:");
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 3;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(adLabel, gridC);
		p2.add(adLabel);
		
		adText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 3;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(adText, gridC);
		p2.add(adText);
		//其他
		otherLabel = new JLabel("其他 	:");
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 4;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(otherLabel, gridC);
		p2.add(otherLabel);
		
		otherText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 4;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(otherText, gridC);
		p2.add(otherText);
		//选择人员信息
		msgLabel = new JLabel("选择人员信息:");
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 5;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(msgLabel, gridC);
		p2.add(msgLabel);
		
		
		String msgStr[]= pbean.getPersonMsg();//获取数据库信息
		msg = new JComboBox(msgStr);
		msg.addItemListener(this);//添加JComboBox选择事件
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 5;
		gridC.insets = new Insets(20,1,20,5);
		gridL.setConstraints(msg,gridC);
		p2.add(msg);
		
		//修改按钮
		ImageIcon image1=new ImageIcon("../pms/images/modify.png");
		update =  new JButton("修改",image1);
		update.addActionListener(this);//添加动作事件
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 5;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(update, gridC);
		p2.add(update);
		//清空按钮
		ImageIcon image2=new ImageIcon("../pms/images/clear.png");
		clear = new JButton("清空",image2);
		
		clear.addActionListener(this);//添加动作事件
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 5;
		gridC.insets = new Insets(20, 1, 20, 5);
		gridL.setConstraints(clear, gridC);
		p2.add(clear);
		
		add("North",p1);
		add("Center",p2);	
	}
/*
 * 重载的动作事件
 * 
 */
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == update){//单击修改
			//调用PersonBean的修改方法
			pbean.update(pIdText.getText(), nameText.getText(), sexText.getText(), 
					birText.getText(), natText.getText(), adText.getText(),DeptId,salary,assess, otherText.getText());
			
			JOptionPane op = new JOptionPane();
			op.showMessageDialog(null, "修改成功!");//提示
		}else if(e.getSource() == clear){
			clearAll();//清空文本框
			
		}
	}
	

	/*
	 * 清空文本框方法
	 */
	public void clearAll(){
		pIdText.setText(null);
		nameText.setText(null);
		sexText.setText(null);
		birText.setText(null);
		natText.setText(null);
		adText.setText(null);
		//msg.setToolTipText(null);
		otherText.setText(null);		
	}

	/*
	 * 重载的JcomboBox事件
	 */
	public void itemStateChanged(ItemEvent e) {
	
		if(e.getStateChange()==ItemEvent.SELECTED){//选择状态
			String s = "" + e.getItem().toString();//获取字符串
			int i = s.indexOf("-");//以"-"分割
			PersonId = s.substring(0, i);//人员编号
			p = pbean.search(PersonId);//字符串数组p用于获取从数据库导入的人员信息
			
			//将数据库导入的人员信息通过数组，添加到相应的组件
			pIdText.setText(p[0]);//人员编号
			nameText.setText(p[1]);
			sexText.setText(p[2]);
			birText.setText(p[3]);//出生年月信息
			natText.setText(p[4]);//民族信息
			adText.setText(p[5]);//地址信息
			DeptId = "" + p[6];
			salary = "" + p[7];//工资
			assess = "" + p[8];//考核信息
			otherText.setText(p[9]);
			
		}
	}
}
