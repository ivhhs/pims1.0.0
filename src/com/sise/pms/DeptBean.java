package com.sise.pms;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *项目名称:pms
 *类名:DeptBean
 *类描述:实现对部门相关信息进行数据库操作的功能，包括部门信息的增加、修改、删除、查询等
 *创建人:马增群
 *创建时间:2014年11月13日 下午10:03:34
 *修改人:
 *修改时间:
 *修改备注:
 *@version 1.0.0
 */
public class DeptBean {
	Database db = new Database();//Database类里有封装的数据库操作方法
	
		ResultSet res=null;//ResultSet对象
		
		String id="";
	
/*
 * 	查询Dept表里的所有数据，并以"部门号-一级部门-二级部门"格式赋给一个字符串数组
 *	用于:(1)模块1【基本信息管理】部门管理,将数据加载到部门JComboBox组件里 
 *		 (2)模块2【人员调动管理】部门管理,将数据加载到新部门的JComboBox组件里
 */
	public String[] searchAllForDept(){
		
		String[] s = null;//字符串数组，用于接收部门信息
		
		int rows = 0;//Dept表里的行数
			
		int i = 0;//第i行数据
		
		String sql = "select * from Dept order by DeptID";//数据查询语句
		
		res = db.query(sql);//调用Database类里的查询方法
	
		try {
			//如果Dept表有数据，就调用Resultset类的getRow方法,获取行数
			if(res.last()){
				rows = res.getRow();
			}
			//如果行数为0,数组赋空值,否,查询部门信息
			if(rows == 0){
				s[0]="";
				s[1]="";
				s[2]="";
			}else{
				s = new String[rows];
				res.first();//获取第一行
				res.previous();//反向查询
				while(res.next()){
					s[i]=res.getString("DeptID")+"-"+res.getString("B_Dept")+"-"+
				res.getString("S_Dept");
					i++;//计数器累加
				}
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			db.close();//关闭数据库
		}
		return s;//返回数组
	}
	/*
	 * 查询部门信息
	 * 用于:(1)模块1【基本信息管理】的部门管理,将部门信息加载到表格里，以便于修改等操作
	 * 
	 * 注意:返回二维数组,数组的行列和表格的行列对应
	 */
	public String[][] select(){
		int i = 0;
		int rows = 0;
		String sql = "select DeptID,B_Dept,S_Dept from Dept order by DeptID";//查询语句
		String s[][] = null;//二维数组
		res = db.query(sql);//查询
		try {
			if(res.last()){
				rows = res.getRow();//获取行数
			}
			
			if(rows == 0){
				s = new String[1][3];
				s[0][0]="";
				s[0][1]="";
				s[0][2]="";
			}else{//行数不为0
				s = new String[rows][3];
				res.first();//第一行
				res.previous();//反向查询
				while(res.next()){
				
					s[i][0]=res.getString("DeptID");//部门号
					s[i][1]=res.getString("B_Dept");//一级部门
					s[i][2]=res.getString("S_Dept");//二级部门
					i++;//计数器累加
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();//关闭数据库
		}
		
		return s;//返回二维数组
	}
	/*
	 * 查询部门信息
	 * 用于:(1)用于模块2【人员调动管理】，模块3【人员考核管理】，模块4【劳资管理】，
	 * 分别加载部门信息到人员调动、人员考核、劳资管理的表格部门信息这一列
	 * 
	 */
	public String[][] searchMsg(){
		int i = 0;
		int rows = 0;
		String s[][]=null;
		
		//涉及2张表的查询,要通过外键进行连接
		String sql="select PersonID,Name,Sex,B_Dept,S_Dept,Salary,Assess,Dept.DeptID,Person.DeptID "
				+"from Dept,Person where Dept.DeptID = Person.DeptID order by PersonID";
		
		res = db.query(sql);
		
		try {
			if(res.last()){
				rows=res.getRow();//行数
			}
		
			if(rows == 0){
				s = new String[1][6];
				s[0][0]="";
				s[0][1]="";
				s[0][2]="";
				s[0][3]="";
				s[0][4]="";
				s[0][5]="";
				
			}else{
				s = new String[rows][6];
				res.first();//第一行
				res.previous();//反向查询
				while(res.next()){
					s[i][0]=res.getString("PersonID");
					s[i][1]=res.getString("Name");
					s[i][2]=res.getString("Sex");
					s[i][3]=res.getString("B_Dept")+"-"+res.getString("S_Dept");	
					s[i][4]=res.getString("Salary");//工资
					s[i][5]=res.getString("Assess");//考核信息
					i++;
				}
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	
	
	
/*
 * 删除部门信息
 * 用于:(1)模块1【基本信息管理】的部门管理，删除部门信息
 * 
 */

	String deptId;
	public void deleteDeptMsg(String deptId){
		this.deptId=deptId;
		String sql = "delete from Dept where DeptID="+deptId+"";//删除部门信息
		db.update(sql);
		
	}
	String b,s,i;
/*
 * 更新信息
 * 用于:(1)模块1【基本信息管理】的部门管理，修改部门信息
 * 
 */
	public void update(String i,String b,String s){
		this.i=i;
		this.b=b;
		this.s=s;
		String sql="update Dept set B_Dept='"+b+"',S_Dept='"+s+"' where DeptID="+i+"";
		
		db.update(sql);
		
	}
	
/*
 * 获取部门编号的最大值
 * 用于:(1)模块1【基本信息管理】的部门管理，获取新编号
 * 	
 */
	public int getNewId(){
		int i=0;
		String sql="select max(DeptID) from Dept";
		
		res=db.query(sql);
		
		try {
			if(res.next()){
				i=res.getInt(1)+1;//获取新的部门编号
			}else{
				i=1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	

}
