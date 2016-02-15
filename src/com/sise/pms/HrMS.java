package com.sise.pms;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 *项目名称:pms
 *类名:HrMS
 *类描述:创建一个登陆框
 *创建人:马增群
 *创建时间:2014年11月13日 下午10:03:55
 *修改人:
 *修改时间:
 *修改备注:
 *@version 1.0.0
 */
public class HrMS {
	JOptionPane op=new JOptionPane();
	JFrame f;
	JLabel label1;
	JLabel label2;
	JTextField user;//用户名
	JPasswordField pwd;//密码
	JButton exit;//退出
	JButton registerBtn;//登陆
	
	public HrMS(){
	
		int width=13;
		f=new JFrame();
		
		f.setLocation(300, 300);
		
		Image image=Toolkit.getDefaultToolkit().getImage("../pms/images/leaf.png");
	
		
		f.setIconImage(image);
		
		f.setTitle("人事管理系统登录框");
		
		f.setLayout(new GridLayout(3,1));//3行1列的布局
			
		JPanel p1=new JPanel();
		
		JPanel p2=new JPanel();
		
		JPanel p3=new JPanel();
		
		
		label1=new JLabel("用户名:");
		
		label2=new JLabel("密码:");
		
		user=new JTextField(width);
		user.setText("admin");
		
		pwd=new JPasswordField(width);//密码框
		pwd.setText("123");
		pwd.setEchoChar('*');
		
		ImageIcon image1=new ImageIcon("../pms/images/no.png");
		
		
		exit=new JButton("退出",image1);

		
		ImageIcon image2=new ImageIcon("../pms/images/login.png");
		
		registerBtn=new JButton("登录",image2);
		
		
		registerBtn.setSize(30, 30);
		
		//registerBtn.
		//单击退出，登录框关闭
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		//用户名admin,密码123
	//单击登录验证用户名和密码是否正确,若正确，弹出主界面,否提示密码
		registerBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(user.getText().equals("admin")&&pwd.getText().equals("123")){
					op.showMessageDialog(null, "登录成功!");//提示登录成功
					new HrMain();
					f.setVisible(false);//隐藏登录框
				}else{
					op.showMessageDialog(null, "请登录!您的密码:123");
				}
			}
		});

		
		p1.add(label1);
		
		p1.add(user);
		
		p2.add(label2);
		
		p2.add(pwd);
		
		p3.add(registerBtn);
		
		p3.add(exit);
		
		f.add(p1);
		f.add(p2);
		f.add(p3);
		
		f.setVisible(true);//设置可见性
		
		f.setSize(300,300);

      
	}
	
	
	public static void main(String[] args) {
	
			new HrMS();//创建对象
	
	}

	
	
}
