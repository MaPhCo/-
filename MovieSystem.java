package org.yuan.example;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class LoginWindow{
	JTextField InputName,InputPassword;
	JButton Login,Register;
	JLabel UserName,PassWord;
	JFrame LoginWindow;
	LoginWindow(String s,String identity){
		InputName = new JTextField(10);
		InputPassword = new JPasswordField(10);
		Login = new JButton("登录");
		Register = new JButton("注册");
		UserName = new JLabel("账号");
		PassWord = new JLabel("密码");
		LoginWindow = new JFrame(s);
		Init();
		Add();
		setMyCommandListener(identity);
	}
	void Init() {
		LoginWindow.setBounds(500,250,488,512);
		LoginWindow.setVisible(true);
		LoginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void Add() {
		LoginWindow.setLayout(new FlowLayout());
		LoginWindow.add(UserName);
		LoginWindow.add(InputName);
		LoginWindow.add(PassWord);
		LoginWindow.add(InputPassword);
		LoginWindow.add(Login);
		LoginWindow.add(Register);
	}
	void setMyCommandListener(String Identity) {
		Login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					File file = new File("D:/影城系统/用户信息",InputName.getText()+".txt");
					if(Identity == "user") {file = new File("D:/影城系统/用户信息",InputName.getText()+".txt");}
					else if(Identity == "manage") {file = new File("D:/影城系统/经理信息",InputName.getText()+".txt");}
					else if(Identity == "administrator") {file = new File("D:/影城系统/管理员信息",InputName.getText()+".txt");}
					else if(Identity == "reception") {file = new File("D:/影城系统/前台信息",InputName.getText()+".txt");}
					//对按登录按钮做出的回应
					boolean cot = false;
					if(!file.exists()) cot = false;
					else{
						BufferedReader r = new BufferedReader(new FileReader(file));
						if(InputPassword.getText().equals(r.readLine())) cot = true;
					}
					if(cot) {
						JOptionPane.showMessageDialog(null, "登录成功！");
						if(Identity == "user") {
							UserWindow window2 = new UserWindow(InputName.getText());
							LoginWindow.setVisible(false);
						}
						else if(Identity == "manage"){
							ManageWindow window2 = new ManageWindow();
							LoginWindow.setVisible(false);
						}
						else if(Identity == "administrator") {
							AdministratorWindow window2 = new AdministratorWindow(InputName.getText());
							LoginWindow.setVisible(false);
						}
						else if(Identity == "reception") {
							ReceptionWindow window2 = new ReceptionWindow();
							LoginWindow.setVisible(false);
						}
					}else {
						JOptionPane.showMessageDialog(null, "用户名或者密码错误，登录失败！");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Register.addActionListener(new ActionListener() {//添加监听器
			//将用户名和密码写入文件中的操作
				public void actionPerformed(ActionEvent e) {
				try {
					File file = new File("D:/影城系统/用户信息",InputName.getText()+".txt");
					if(Identity == "user") file = new File("D:/影城系统/用户信息",InputName.getText()+".txt");
					else if(Identity == "manage") {file = new File("D:/影城系统/经理信息",InputName.getText()+".txt");}
					else if(Identity == "administrator") {file = new File("D:/影城系统/管理员信息",InputName.getText()+".txt");}
					else if(Identity == "reception") {file = new File("D:/影城系统/前台信息",InputName.getText()+".txt");}
					if(file.exists()) JOptionPane.showMessageDialog(null, "该用户已存在，请更换用户名和密码");
					else {
						file.createNewFile();
						BufferedWriter w = new BufferedWriter(new FileWriter(file));
						w.write(InputPassword.getText());
						w.flush();
						w.close();
						JOptionPane.showMessageDialog(null,"注册成功!");
						if(Identity == "user") {
							File file2 = new File("D:/影城系统/用户资料",InputName.getText()+".txt");
							BufferedWriter sbr = new BufferedWriter(new FileWriter(file2));
							int randomnum = (int)(Math.random()*1000+100);
							sbr.write(Integer.toString(randomnum)+'\n');
							sbr.write("铜牌用户"+'\n');
							sbr.write("2023.10.28"+'\n');
							sbr.flush();
							sbr.close();
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}
}

class TheFirstWindow{
	JButton user,manage,administrator,reception;
	JFrame ChoosingWindow;
	LoginWindow ChoosedWindow;
	TheFirstWindow(){
		user = new JButton("用户");
		manage = new JButton("经理");
		administrator = new JButton("管理员");
		reception = new JButton("前台");
		ChoosingWindow = new JFrame("登录系统");
		Init();
		Add();
		setMyCommandListener();
	}
	void Init() {
		ChoosingWindow.setBounds(500,250,488,512);
		ChoosingWindow.setVisible(true);
		ChoosingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void Add() {
		ChoosingWindow.setLayout(new FlowLayout());
		ChoosingWindow.add(user);
		ChoosingWindow.add(manage);
		ChoosingWindow.add(administrator);
		ChoosingWindow.add(reception);
	}
	void setMyCommandListener() {
		user.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ChoosedWindow = new LoginWindow("用户登录系统","user");
			}
		});
		manage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChoosedWindow = new LoginWindow("经理登录系统","manage");
			}
		});
		administrator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChoosedWindow  = new LoginWindow("管理员登录系统","administrator");
			}
		});
		reception.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChoosedWindow = new LoginWindow("前台登录系统","reception");
			}
		});
	}
}

public class MovieSystem{
	public static void main(String args[]) {
		TheFirstWindow windo1 = new TheFirstWindow();
	}
}