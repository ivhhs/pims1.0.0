package com.sise.pms;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/*
 * 
 */
/**
 *项目名称:pms
 *类名:HistrjnBean
 *类描述:实现对历史流水相关信息进行数据库操作的功能，包括增加流水信息、提供多种方式的查询等
 *创建人:马增群
 *创建时间:2014年11月13日 下午10:03:45
 *修改人:
 *修改时间:
 *修改备注:
 *@version 1.0.0
 */
public class HistrjnBean {
	
	Database db = new Database();
	
	ResultSet res = null;
	
	String id;
	
	String s1,s2,s3,s4,s5,s6,s7;
	
	/*
	 * 人员调动历史查询
	 * 用于:(1)模块2【人员调动管理】调动历史查询，将Histrjn表的人员调动信息加载到表格里
	 * 
	 */
	public String[][] transferHis(){
		String s[][] = null;
		int i = 0;
		int rows = 0;
		String sql = "select JourNo,Name,OldInfo,NewInfo,ChgTime,RegDate,FromAcc,Person.PersonID,Histrjn.PersonID "
				+ "from Person,Histrjn where Person.PersonID = Histrjn.PersonID and FromAcc = '人员调动' order by JourNo";
		res = db.query(sql);
		
		DeptBean dbean = new DeptBean();
		
		
		try {
			if(res.last()){
				rows = res.getRow();//获取行数
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
					s[i][0]=res.getString("JourNo");
					s[i][1]=res.getString("Name");
					s[i][2]=res.getString("OldInfo");
					s[i][3]=res.getString("NewInfo");
					s[i][4]=res.getString("ChgTime");
					s[i][5]=res.getString("RegDate");
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
	 * 人员考核历史查询
	 * 用于:(1)模块3【人员考核管理】考核历史查询，将Histrjn表的人员考核信息加载到表格里
	 * 
	 */
	public String[][] assessHis(){
		String s[][] = null;
		int i = 0;
		int rows = 0;
		String sql = "select JourNo,Name,OldInfo,NewInfo,ChgTime,RegDate,FromAcc,Person.PersonID,Histrjn.PersonID "
				+ "from Person,Histrjn where Person.PersonID = Histrjn.PersonID and FromAcc = '人员考核' order by JourNo";
		res = db.query(sql);
		try {
			if(res.last()){
				rows = res.getRow();
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
					s[i][0]=res.getString("JourNo");
					s[i][1]=res.getString("Name");
					s[i][2]=res.getString("OldInfo");
					s[i][3]=res.getString("NewInfo");
					s[i][4]=res.getString("ChgTime");
					s[i][5]=res.getString("RegDate");
					i++;
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();//关闭数据库
		}
		return s;//返回
	}
	
	
	
	/*
	 * 劳资分配历史查询
	 * 用于:(1)模块4【劳资分配管理】劳资分配历史查询，将Histrjn表的劳资分配信息加载到表格里
	 * 
	 */
	public String[][] salaryHis(){
		String s[][] = null;//二维数组
		int i = 0;
		int rows = 0;
		//涉及2张表的查询,要通过外键进行连接
		String sql = "select JourNo,Name,OldInfo,NewInfo,ChgTime,RegDate,FromAcc,Person.PersonID,Histrjn.PersonID "
				+ "from Person,Histrjn where Person.PersonID = Histrjn.PersonID and FromAcc = '劳资分配' order by JourNo";
		res = db.query(sql);
		try {
			if(res.last()){
				rows = res.getRow();
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
					s[i][0]=res.getString("JourNo");
					s[i][1]=res.getString("Name");
					s[i][2]=res.getString("OldInfo");
					s[i][3]=res.getString("NewInfo");
					s[i][4]=res.getString("ChgTime");
					s[i][5]=res.getString("RegDate");
					i++;
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.close();
		}
		return s;
	}
	
	String personId;

/*
 * 统计Histrjn表里的流水号数量
 * 用于:(1)模块2【人员调动管理】人员调动,获取原来部门的编号
 * 
 */
public int countJourNo(){
	int no=1;
	String sql="select max(JourNo) from Histrjn ";
	res=db.query(sql);
	
	try {
		if(res.next()){
			no=res.getInt(1)+1;
		}else{
			no=1;
		}
	} catch (SQLException e) {
	
		e.printStackTrace();
	}
	return no;
}

/*
 * 添加查询历史信息
 * 用于:(1)模块2【劳资分配管理】、模块3【人员考核管理】、模块4【劳资管理】分别将
 * 将Histrjn表的相应信息加载到对应历史查询的表格里
 * 
 */
public void add(String s1, String s2,String s3,String s4,String s5,String s6,String s7){
	this.s1=s1;
	this.s2=s2;
	this.s3=s3;
	this.s4=s4;
	this.s5=s5;
	this.s6=s6;
	this.s7=s7;
	
	String sql="insert into Histrjn(JourNo,FromAcc,OldInfo,NewInfo,RegDate,ChgTime,PersonID) values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"')";
	
	db.update(sql);

	
}


/*
 * 统计修改次数
 * 用于：(1)模块2【人员调动管理】的人员调动，获取修改的历史次数
 * 
 */
public int countChgTime(String s2,String s7){
	this.s2=s2;
	this.s7=s7;
	int i=1;
	
	String sql="select max(ChgTime) from Histrjn where FromAcc='"+s2+"' and PersonID="+s7+"";
	
	res=db.query(sql);
	
	try {
		if(res.next()){
			i=res.getInt(1)+1;
		}else{
			i=1;
		}
	} catch (SQLException e) {
	
		e.printStackTrace();
	}finally{
		db.close();
	}
	
	return i;
}

	
}
