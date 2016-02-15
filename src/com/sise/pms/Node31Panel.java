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
 *类名:Node31Panel
 *类描述:人员考核
 *创建人:马增群
 *创建时间:2014年11月13日 下午10:04:22
 *修改人:
 *修改时间:
 *修改备注:
 *@version 1.0.0
 */
public class Node31Panel extends JPanel implements ActionListener,ListSelectionListener,ItemListener{
	PersonBean pbean=new PersonBean();
	DeptBean dbean = new DeptBean();
	HistrjnBean hbean = new HistrjnBean();
	JLabel title;
	JPanel p1,p2,p3;
	JTable table;
	JScrollPane scrollPane;
	JButton ok,clear;
	GridBagLayout gridL;
	JComboBox thisAssessCom;//本次考核
	GridBagConstraints gridC;
	JTextField nameText,preAssessText;//上次考核
	JLabel nameLabel,preAssessLabel,thisAssessLabel;
	
	String tableTitle[] = {"工号","姓名","性别","部门","薪酬","考核信息"};
	
	String tableValues [][] = dbean.searchMsg();
	
	ListSelectionModel listModel;
	
	String PersonId;//人员编号
	
	String assess="优秀";//考核信息
	
	JOptionPane op = new JOptionPane();
	
	
	public Node31Panel(){
		init();
	}
	public void init(){
		int width = 10;//JTextField的宽度
		
		setLayout(new BorderLayout());
		gridL = new GridBagLayout();
		//a.上部布局
		p1 = new JPanel();
		
		title = new JLabel("人员考核");
		p1.add(title);
		//b.中部布局
		p2 = new JPanel();
		
	//创建表格
		table = new JTable(tableValues,tableTitle);
		listModel=table.getSelectionModel();
		listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listModel.addListSelectionListener(this);
		
		scrollPane = new JScrollPane(table);
		
		p2.add(scrollPane);
		//c.下部布局
		p3 = new JPanel();
		p3.setLayout(gridL);
		//姓名
		nameLabel = new JLabel("姓名:");
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
	//上次考核
		preAssessLabel = new JLabel("上次考核:");
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(preAssessLabel, gridC);
		p3.add(preAssessLabel);
		
		preAssessText = new JTextField(width);
		preAssessText.setEditable(false);
		gridC = new GridBagConstraints();
		gridC.gridx = 3;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(preAssessText, gridC);
		p3.add(preAssessText);
		//本次考核
		thisAssessLabel = new JLabel("本次考核:");
		gridC = new GridBagConstraints();
		gridC.gridx = 4;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(thisAssessLabel, gridC);
		p3.add(thisAssessLabel);
		//定义一个数组,将数组信息加到JComboBox组件
		String assessAry[]={"优秀","合格","不合格"};
		thisAssessCom = new JComboBox(assessAry);
		thisAssessCom.addItemListener(this);//添加JComboBox选择事件
		gridC = new GridBagConstraints();
		gridC.gridx = 5;
		gridC.gridy = 1;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(thisAssessCom, gridC);
		p3.add(thisAssessCom);
		//确定按钮
		ImageIcon image1=new ImageIcon("../pms/images/right.png");
		ok= new JButton("确定",image1);
		ok.addActionListener(this);
		gridC = new GridBagConstraints();
		gridC.gridx = 2;
		gridC.gridy = 2;
		gridC.insets = new Insets(5, 10, 5, 10);
		gridL.setConstraints(ok, gridC);
		p3.add(ok);
		//清空信息按钮
		ImageIcon image2=new ImageIcon("../pms/images/clear.png");
		clear = new JButton("清空信息",image2);
		clear.addActionListener(this);//添加动作事件
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
	 * 单击按钮触发控件
	 * */
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == clear){
			clearAll();//清空信息
		}else if(e.getSource()==ok){
			pbean.updateAssess(PersonId, assess);//更新考核信息
		
			
			String jourNoStr=""+hbean.countJourNo();//流水号
			
			
			Calendar now = new GregorianCalendar();
			SimpleDateFormat formatter=new SimpleDateFormat();
			formatter.applyPattern("yyyy-MM-dd HH-mm-ss");//时间格式
			String regDate=""+formatter.format(now.getTime());//获取系统时间
			
			
			String chgTime=""+hbean.countChgTime("人员考核", PersonId);//获取改变次数
					//添加信息到Histrjn表
			hbean.add(jourNoStr, "人员考核",preAssessText.getText(),assess, regDate, chgTime,PersonId);
		
			op.showMessageDialog(null,"人员考核更改成功!");
			clearAll();//清空
			
		}
	}
	/*
	 * 清空文本框方法
	 */
	public void clearAll(){
		nameText.setText(null);
		preAssessText.setText(null);
		thisAssessCom.setToolTipText(null);
	}
	
	@Override
	/*
	 * 表格选中时触发事件
	 */
	public void valueChanged(ListSelectionEvent e) {
		int row[]=table.getSelectedRows();//表格的行
		int col[]=table.getSelectedColumns();//表格的列
		
		for(int i=0;i<row.length;i++)
			for(int j=0;j<col.length;j++){
			
				nameText.setText(tableValues[row[i]][1]);
				
				preAssessText.setText(tableValues[row[i]][5]);//上次考核信息
				
				PersonId=tableValues[row[i]][0];//人员编号
				
				
			}
	}

/*
 * JConboBox被选中时触发事件
 * */
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED){
			assess=""+e.getItem();//获取新的考核信息
		}
		
	}
}
