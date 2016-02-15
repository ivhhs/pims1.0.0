package com.sise.pms;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *项目名称:pms
 *类名:Node22Panel
 *类描述:人员调动历史查询
 *创建人:马增群
 *创建时间:2014年11月13日 下午10:04:19
 *修改人:
 *修改时间:
 *修改备注:
 *@version 1.0.0
 */
public class Node22Panel extends JPanel{
	HistrjnBean hbean = new HistrjnBean();
	JLabel title;
	JTable table;
	JPanel p1,p2,p3;
	JScrollPane scrollPane;
	JLabel numLabel,nameLabel,sexLabel,birLabel;
	JTextField numText,nameText,sexText,birText;
	public Node22Panel(){
		init();
	}
	public void init(){
		//a.上部布局
		p1 = new JPanel();
		title = new JLabel("");
		p1.add(title);
		//b.中部布局
		p2 = new JPanel();
		
		String tableTitle[] = {"流水号","人员姓名","原部门","新部门","变更次数","变更日期"};
		
		String tableValues[][] = hbean.transferHis();//将人员调动历史的信息添加到表格
		//创建表格
		table = new JTable(tableValues,tableTitle);
		
		scrollPane = new JScrollPane(table);
		
		p2.add(scrollPane);
		//c.下部布局
		p3 = new JPanel();
		
		
		add("North",p1);
		add("Center",p2);
		add("South",p3);
	}
}
