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
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 *项目名称:pms
 *类名:Node11Panel
 *类描述:人员基本信息管理的添加信息类
 *创建人:马增群
 *创建时间:2014年11月13日 下午10:03:59
 *修改人:
 *修改时间:
 *修改备注:
 *@version 1.0.0
 */
public class Node11Panel extends JPanel implements ActionListener,ItemListener{

	String DeptId = "1";
	
	PersonBean pbean = new PersonBean();
	
	JLabel title;
	JButton add,clear;	//添加，清除
	JPanel p1,p2;
	GridBagLayout gridL;
	GridBagConstraints gridC;
	JComboBox deptCombobox;//部门
	JTextField pIdText,nameText,sexText,birText,natText,adText,otherText;
	JLabel pIdLabel,nameLabel,sexLabel,birLabel,natLabel,adLabel,deptLabel,otherLabel;
//构造方法
	public Node11Panel(){
		init();
	
	}
	
	
	/*
	 * 用于初始组件
	 */
	public void init(){
		
		setLayout(new BorderLayout());//设置布局为BorderLayout
		
		gridL = new GridBagLayout();
		
		int width = 10;//JTextField的宽度,便于代码修改
		//a.上部布局
		p1 = new JPanel();
		title = new JLabel("增加人员信息");
		p1.add(title);
		//b.中部布局
		p2 = new JPanel();
		p2.setLayout(gridL);
		
		//人员编号
		pIdLabel = new JLabel("人员编号:");
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 1;
		gridC.insets = new Insets(10, 1, 20, 10);
		gridL.setConstraints(pIdLabel, gridC);
		p2.add(pIdLabel);
		
		pIdText = new JTextField(width);
		pIdText.setEditable(false);
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 1;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(pIdText, gridC);
		p2.add(pIdText);
		pIdText.setText(""+pbean.getID());
		
		//人员姓名
		nameLabel = new JLabel("人员姓名:");
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 1;
		gridC.insets = new Insets(20, 10, 20, 10);
		gridL.setConstraints(nameLabel, gridC);
		p2.add(nameLabel);
		
		nameText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 1;
		gridC.insets = new Insets(20, 20, 20, 10);
		gridL.setConstraints(nameText, gridC);
		p2.add(nameText);
		//性别
		sexLabel = new JLabel("性别		:");
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 2;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(sexLabel, gridC);
		p2.add(sexLabel);
		
		sexText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 2;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(sexText, gridC);
		p2.add(sexText);
		//出生年月
		birLabel = new JLabel("出生年月:");
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 2;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(birLabel, gridC);
		p2.add(birLabel);
		
		birText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 2;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(birText, gridC);
		p2.add(birText);
		

		//民族
		natLabel = new JLabel("民族		:");
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 3;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(natLabel, gridC);
		p2.add(natLabel);
		
		natText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 3;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(natText, gridC);
		p2.add(natText);

		//地址
		adLabel = new JLabel("地址	:");
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 3;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(adLabel, gridC);
		p2.add(adLabel);
		
		adText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 3;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(adText, gridC);
		p2.add(adText);
		
		
		//部门
		deptLabel = new JLabel("部门		:");
		gridC = new GridBagConstraints();
		gridC.gridx = 0;
		gridC.gridy = 4;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(deptLabel, gridC);
		p2.add(deptLabel);
		
		DeptBean dbean = new DeptBean();//创建部门数据库操作对象
		String deptItems[] = dbean.searchAllForDept();//将从Dept表里获取的部门信息加载到JComboBox
		deptCombobox = new JComboBox(deptItems);
		deptCombobox.addItemListener(this);//添加JComboBox选择事件
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 4;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(deptCombobox, gridC);
		p2.add(deptCombobox);
		
		//其他
		otherLabel = new JLabel("其他:");
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 4;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(otherLabel, gridC);
		p2.add(otherLabel);
		
		otherText = new JTextField(width);
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 4;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(otherText, gridC);
		p2.add(otherText);
		
		//添加按钮
		ImageIcon image1=new ImageIcon("../pms/images/add.png");
		add =  new JButton("添加",image1);
		
		add.addActionListener(this);//添加动作事件
		gridC = new GridBagConstraints();
		gridC.gridx = 1;
		gridC.gridy = 5;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(add, gridC);
		p2.add(add);
		
		//清空按钮
		ImageIcon image2=new ImageIcon("../pms/images/clear.png");
		clear = new JButton("清空",image2);
		
		clear.addActionListener(this);//添加动作事件
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 5;
		gridC.insets = new Insets(20, 1, 20, 10);
		gridL.setConstraints(clear, gridC);
		p2.add(clear);
		
		add("North",p1);
		add("Center",p2);	
	}
	/*
	 * 重载的动作事件纷纷
	 */
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == add){//选择添加按钮
			
			String salary = "0";//工资
			String assess = "未考核";//考核信息
			PersonBean p1 = new PersonBean();
			p1.add(pIdText.getText(), nameText.getText(), sexText.getText(), birText.getText(), natText.getText(),
			adText.getText(),DeptId,salary, assess, otherText.getText());
			JOptionPane op = new JOptionPane();
			op.showMessageDialog(null, "插入信息成功!");//提示信息
			clearAll();//清空
		}else if(e.getSource() == clear){//选择清除按钮
			clearAll();
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
		deptCombobox.setToolTipText(null);
		otherText.setText(null);		
	}

	@Override
	/*
	 *重载的itemStateChanged方法
	 */
	public void itemStateChanged(ItemEvent e) {

		if(e.getStateChange()==ItemEvent.SELECTED){//选中JComboBox组件
			String s = ""+e.getItem();//获取字符串
			String[] arr = s.split("-");//以"-"分割
			DeptId = arr[0];//部门编号
		}
		
	}

}
