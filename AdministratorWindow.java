package org.yuan.example;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdministratorWindow{
	JFrame AdministratorWindow;
	JTextArea TextShow;
	JMenuBar Menubar;
	JMenu menu,menu1,menu2;
	JMenuItem item1,item2,item3,item4,item5,item6;
	AdministratorWindow(String Account){
		AdministratorWindow = new JFrame("管理员");
		TextShow = new JTextArea(30,40);
		Menubar = new JMenuBar();
		menu = new JMenu("菜单");
		menu1 = new JMenu("密码管理");
		menu2 = new JMenu("用户管理");
		item1 = new JMenuItem("修改自身密码");
		item2 = new JMenuItem("重置指定用户的密码");
		item3 = new JMenuItem("列出所有用户信息");
		item4 = new JMenuItem("删除用户信息");
		item5 = new JMenuItem("查询用户信息");
		item6 = new JMenuItem("退出登录");
		Add();
		Init();
		setMyCommandListener(Account);
	}	
	void Init(){
		AdministratorWindow.setBounds(500,250,488,512);
		AdministratorWindow.setVisible(true);
		AdministratorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void Add() {
		AdministratorWindow.setLayout(new FlowLayout());
		menu.add(menu1);
		menu.add(menu2);
		menu.add(item6);
		menu1.add(item1);
		menu1.add(item2);
		menu2.add(item3);
		menu2.add(item4);
		menu2.add(item5);
		Menubar.add(menu);
		AdministratorWindow.setJMenuBar(Menubar);
		AdministratorWindow.add(TextShow);
	}
	void setMyCommandListener(String Account) {
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ChangePasswordWindow = new JFrame("修改密码");
				JLabel Label = new JLabel("请输入您的新密码");
				JTextField NewPassword = new JTextField(10);
				JButton Button = new JButton("确定");
				ChangePasswordWindow.setLayout(new FlowLayout());
				ChangePasswordWindow.setBounds(500,250,244,256);
				ChangePasswordWindow.setVisible(true);
				ChangePasswordWindow.add(Label);
				ChangePasswordWindow.add(NewPassword);
				ChangePasswordWindow.add(Button);
				Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							File file = new File("D:/影城系统/管理员信息",Account+".txt");
							file.delete();
							file.createNewFile();
							BufferedWriter w = new BufferedWriter(new FileWriter(file));
							w.write(NewPassword.getText());
							w.flush();
							w.close();
							JOptionPane.showMessageDialog(null, "修改密码成功！");
							ChangePasswordWindow.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ChangePasswordWindow = new JFrame("修改密码");
				JTextField NewPassword = new JTextField(10);
				JTextField UserAccount = new JTextField(10);
				JButton Button = new JButton("确定");
				ChangePasswordWindow.setLayout(new FlowLayout());
				ChangePasswordWindow.setBounds(500,250,244,256);
				ChangePasswordWindow.setVisible(true);
				ChangePasswordWindow.add(new JLabel("请输入要更改面的用户名"));
				ChangePasswordWindow.add(UserAccount);
				ChangePasswordWindow.add( new JLabel("请输入该用户的新密码"));
				ChangePasswordWindow.add(NewPassword);
				ChangePasswordWindow.add(Button);
				Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							File file = new File("D:/影城系统/用户信息",UserAccount.getText()+".txt");
							if(!file.exists())	JOptionPane.showMessageDialog(null, "未查找到该用户！");
							else {
								file.delete();
								file.createNewFile();
								BufferedWriter w = new BufferedWriter(new FileWriter(file));
								w.write(NewPassword.getText());
								w.flush();
								w.close();
								JOptionPane.showMessageDialog(null, "修改密码成功！");
								ChangePasswordWindow.setVisible(false);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
		item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File divfile = new File("D:/影城系统/用户资料");
					File[] file = divfile.listFiles();
					BufferedReader r = new BufferedReader(new FileReader(file[0]));
					for(int i=0;i<file.length;i++) {
						r = new BufferedReader(new FileReader(file[i]));
						String s = file[i].getName();
						String s1 = s.replace(".txt","");
						TextShow.append("用户名:"+s1+'\n');
						int count = 1;
						while((s=r.readLine())!=null) {
							if(count==1) TextShow.append("用户ID:"+s+'\n');
							if(count==2) TextShow.append("用户级别:"+s+'\n');
							if(count==3) TextShow.append("用户注册时间:"+s+'\n');
							count++;
						}
						TextShow.append("----------------------------------------------"+'\n');
					}
					r.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ChangePasswordWindow = new JFrame("删除用户");
				JTextField UserAccount = new JTextField(10);
				JButton Button = new JButton("确定");
				ChangePasswordWindow.setLayout(new FlowLayout());
				ChangePasswordWindow.setBounds(500,250,244,256);
				ChangePasswordWindow.setVisible(true);
				ChangePasswordWindow.add( new JLabel("请输入要删除的用户"));
				ChangePasswordWindow.add(UserAccount);
				ChangePasswordWindow.add(Button);
				Button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							File file = new File("D:/影城系统/用户资料",UserAccount.getText()+".txt");
							if(!file.exists())	JOptionPane.showMessageDialog(null, "未查询到相关用户！");
							else {
								file.delete();
								JOptionPane.showMessageDialog(null, "删除成功！");
							}
							ChangePasswordWindow.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
		item5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame window3 = new JFrame();
				JTextField MovieName = new JTextField(10);
				JLabel label = new JLabel("请输入要查询的用户");
				JButton button = new JButton("确定");
				window3.setLayout(new FlowLayout());
				window3.add(label);
				window3.add(MovieName);
				window3.add(button);
				window3.setBounds(500,250,244,256);
				window3.setVisible(true);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							File file = new File("D:/影城系统/用户资料",MovieName.getText()+".txt");
							if(!file.exists()) JOptionPane.showMessageDialog(null, "未查询到相关用户!");
							else {
								BufferedReader r = new BufferedReader(new FileReader(file));
								String s = file.getName();
								String s1 = s.replace(".txt","");
								TextShow.append(s1+'\n');
								while((s=r.readLine())!=null) {
									TextShow.append(s+'\n');
								}
								TextShow.append("----------------------------------------------"+'\n');
								JOptionPane.showMessageDialog(null, "查找成功！");
								r.close();
							}
							window3.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
		item6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministratorWindow.setVisible(false);
			}
		});
		
	}
}

