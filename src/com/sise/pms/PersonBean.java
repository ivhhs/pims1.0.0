package com.sise.pms;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


/**
 *项目名称:pms
 *类名:PersonBean
 *类描述:实现对人员信息表进行数据库操作的功能，包括人员信息的增加、修改、删除、查询等
 *创建人:马增群
 *创建时间:2014年11月13日 下午10:04:33
 *修改人:
 *修改时间:
 *修改备注:
 *@version 1.0.0
 */
public class PersonBean {
	Database db = new Database();
	ResultSet res=null;

	String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
	/*
	 * 添加人员信息
	 * 用于:(1)模块1【基本信息管理】的添加人员信息,将数据导入到数据库
	 * 
	 */
		public void add(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8,String s9,String s10){
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
		this.s5 = s5;
		this.s6 = s6;
		this.s7 = s7;
		this.s8 = s8;
		this.s9 = s9;
		this.s10 = s10;
		checkEmpty();//检查文本框是否为空
		
		String sql="insert into Person values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s10+"')";
	    db.update(sql);//插入数据
	}
	/*
	 * 检查文本框是否为空
	 */
		public void checkEmpty(){
			if((s1.trim().equals("")) || (s1 == null)){
				JOptionPane.showMessageDialog(null, "请填写编号！");
			}else if((s2.trim().equals("")) || (s2  == null)){
				JOptionPane.showMessageDialog(null, "请填写姓名！");
			}else if((s3.trim().equals("")) || (s3== null)){
				JOptionPane.showMessageDialog(null, "请填写性别！");
			}else if((s4.trim().equals("")) || (s4 == null)){
				JOptionPane.showMessageDialog(null, "请填写出生年月！");
			}else if((s5.trim().equals("")) || (s5 == null)){
				JOptionPane.showMessageDialog(null, "请填写民族！");
			}else if((s6.trim().equals("")) || (s6 == null)){
				JOptionPane.showMessageDialog(null, "请填写地址！");
			}else if((s7.trim().equals("")) || (s7 == null)){
				JOptionPane.showMessageDialog(null, "请填写部门！");
			}else if((s8.trim().equals("")) || (s8 == null)){
				JOptionPane.showMessageDialog(null, "请填写其他信息！");
			}
		}
		
		/*
		 * 获取Person表里的数据行数
		 * 用于：(1)模块1【基本信息管理】的添加人员信息,自动获取PersonId的值
		 */
	public int getID(){
		int id = 1;
		String sql = "select count(*) from Person";//count()函数用于统计
		Database db = new Database();
		res = db.query(sql);//查询
		try {
			if(res.next()){//如果有数据,则获取数据行数
				
				id = res.getInt(1)+1;
			
			}else{
				
				id =1;//否,id设为1
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();//关闭数据库
		}
		return id;
	}
	/*
	 * 获取Person表里的全部信息
	 * 用于:(1)模块1【基本信息管理】的修改人员信息自动添加信息到JComboBox组件上
	 * 
	 */
	public String[] getPersonMsg(){
		
		String sql = "select * from Person";//查询所有信息
		String s[] = null;
		int i = 0;
		int rows = 0;
		res = db.query(sql);
		try {
			if(res.last()){
				rows = res.getRow();//获取行数
			}
			
			if(rows == 0){//行数为0
				s[0]="";
				s[1]="";
				s[2]="";
			}else{//行数不为0
				s = new String[rows];
				res.first();//第一行
				res.previous();//反向查询
				while(res.next()){
					s[i]=res.getString("PersonID")+"-"+res.getString("Name");
					i++;//计数器累加
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();//关闭数据库
		}
		return s;
	}
	String id;
	/*
	 * 查询Person表的信息
	 * 用于:(1)模块1【基本信息管理】的修改人员信息，单击JComboBox组件时,根据PersonID获取信息
	 * 
	 */
	public String[] search(String id){
		this.id = id;
	
		String sql = "select * from Person where PersonID = "+id+"";//根据PersonID查询信息
		
		res = db.query(sql);//查询信息
		String s[] = new String[10];//定义一个数组,用于接收从数据库导入的信息
		try {
				while(res.next()){
					s[0] = res.getString("PersonID");
					s[1] = res.getString("Name");
					s[2] = res.getString("Sex");
					s[3] = res.getString("Birth");
					s[4] = res.getString("Nat");//民族
					s[5] = res.getString("Address");
					s[6] = res.getString("DeptID");
					s[7] = res.getString("Salary");//工资
					s[8] = res.getString("Assess");//考核信息
					s[9] = res.getString("Other");//其他信息
				}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			db.close();//关闭数据库
		}
		return s;//返回一维数组
	}
	
	String pid;
	/*
	 * 删除人员信息
	 * 用于:(1)模块1【基本信息管理】的删除人员信息
	 * 
	 */
	public void delete(String pid){
		this.pid = pid;
		//根据PersonID删除信息
		String sql = "delete from Person where PersonID="+pid+"";
		db.update(sql);
		
		db.close();//关闭数据库
		
		
	}
	
	/*
	 * 涉及Dept表和Person表的查询
	 * 用于:(1)模块1【基本信息管理】的删除人员信息和查询人员信息，将信息加载到表格
	 * 
	 */
	public String[][] select(){
		//涉及Dept和Person表的查询通过DeptID进行连接
		String sql = "select PersonID,Name,Birth,Nat,Address,Dept.DeptID,Person.DeptID,B_Dept,S_Dept from Person,Dept where Person.DeptID = Dept.DeptID order by PersonID";
		
		int i = 0;
		int rows = 0;
		String s[][] = null;//二维数组
		res = db.query(sql);
		
		try {
			
			if(res.last()){
				rows = res.getRow();//获取行数
			}
			if(rows == 0){//行数为0
			
				s = new String[1][6];
				s[0][0] = "";
				s[0][1] = "";
				s[0][2] = "";
				s[0][3] = "";
				s[0][4] = "";
				s[0][5] = "";
				
			}else{//行数不为0
				s = new String[rows][6];
		
				res.first();//第一行
				res.previous();//反向查询
				while(res.next()){
				
					s[i][0]=res.getString("PersonID");
					s[i][1]=res.getString("Name");
					s[i][2]=res.getString("Birth");
					s[i][3]=res.getString("Nat");//民族
					s[i][4]=res.getString("Address");
					s[i][5]=res.getString("B_Dept")+"-"+res.getString("S_Dept");
					i++;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return s;//返回二维数组
	}
	
	/*
	 * 修改人员信息
	 * 用于:(1)模块1【基本信息管理】的修改人员信息，根据PersonID进行修改
	 * 
	 */
	public void update(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8,String s9,String s10){
		this.s1=s1;
		this.s2=s2;
		this.s3=s3;
		this.s4=s4;
		this.s5=s5;
		this.s6=s6;
		this.s7=s7;
		this.s8=s8;
		this.s9=s9;
		this.s10=s10;
		
		String sql="update Person set Name='"+s2+"',Sex='"+s3
				+"',Birth='"+s4+"',Nat='"+s5+"',Address='"+s6+"',DeptID="+s7+",Salary='"+s8+"',Assess='"+s9+"',Other='"+s10+"' where PersonID="+s1+"";
		
		db.update(sql);
	}
	/*
	 * 
	 * 修改部门号
	 * 用于:(1)模块2【人员调动管理】的人员调动,更改部门编号
	 */
	String deptId;
	String personId;
	public void updateDept(String personId,String deptId){
		this.personId=personId;
		this.deptId=deptId;
	//更改部门编号
		String sql="update Person set DeptID="+deptId+" where PersonID="+personId+"";
		db.update(sql);
	}
	/*
	 * 更改人员工资
	 * 用于:(1)模块4【劳资分配管理】的劳资分配,更改人员工资
	 * 
	 */
	String sal;
	public void updateSalary(String personId,String sal){
		this.personId=personId;
		this.sal=sal;
		String sql="update Person set Salary='"+sal+"' where PersonID="+personId+"";
		db.update(sql);
	}
	/*
	 * 更改人员工资
	 * 用于:(1)模块4【人员考核管理】的人员考核,更改人员考核信息
	 * 
	 */
	String assess;
	public void updateAssess(String personId,String assess){
		this.personId=personId;
		this.assess=assess;
		
		String sql="update Person set Assess='"+assess+"' where PersonID="+personId+"";
		db.update(sql);
	}
	
	/*
	 *获取部门编号
	 *用于:(1)模块2【人员调动管理】的人员调动，获取部门原来的编号
	 */
	 
	public String getDeptId(String s1){
		this.s1=s1;
		String i=null;
		
		String sql="select DeptID from Person where PersonID="+s1+"";
		res=db.query(sql);
		try {
			if(res.next()){
				i=res.getString("DeptID");
			}else{
				i="";
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return i;
		
	}
}
