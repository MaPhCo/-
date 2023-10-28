package org.yuan.example;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReceptionWindow{
	JFrame ReceptionWindow;
	JTextArea TextShow;
	JMenuBar Menubar;
	JMenu menu,menu1,menu2;
	JMenuItem item1,item2,item3,item4,item5,item6,item7,item8,item9,item10;
	ReceptionWindow(){
		ReceptionWindow = new JFrame("前台");
		TextShow = new JTextArea(30,40);
		Menubar = new JMenuBar();
		menu = new JMenu("菜单");
		item1 = new JMenuItem("列出所有正在上映影片的信息");
		item2 = new JMenuItem("列出指定电影和场次的信息");
		item3 = new JMenuItem("售票功能");
		item4 = new JMenuItem("退出登录");
		Add();
		Init();
		setMyCommandListener();
	}	
	void Init(){
		ReceptionWindow.setBounds(500,250,488,512);
		ReceptionWindow.setVisible(true);
		ReceptionWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void Add() {
		ReceptionWindow.setLayout(new FlowLayout());
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		menu.add(item4);
		Menubar.add(menu);
		ReceptionWindow.setJMenuBar(Menubar);
		ReceptionWindow.add(TextShow);
	}
	void setMyCommandListener() {
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File divfile = new File("D:/影城系统/电影信息");
					File[] file = divfile.listFiles();
					BufferedReader r = new BufferedReader(new FileReader(file[0]));
					for(int i=0;i<file.length;i++) {
						r = new BufferedReader(new FileReader(file[i]));
						String s = file[i].getName();
						String s1 = s.replace(".txt","");
						TextShow.append(s1+'\n');
						while((s=r.readLine())!=null) {
							TextShow.append(s+'\n');
						}
						TextShow.append("----------------------------------------------"+'\n');
					}
					r.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame window3 = new JFrame();
				JLabel label = new JLabel("电影列表:                                                    ");
				JButton []button = new JButton[10];
				window3.setLayout(new FlowLayout());
				window3.add(label);
				try {
					File divfile = new File("D:/影城系统/电影信息");
					File[] file = divfile.listFiles();
					BufferedReader r = new BufferedReader(new FileReader(file[0]));
					for(int i=0;i<file.length;i++) {
						String s = file[i].getName().replace(".txt", "");
						JLabel MovieLabel = new JLabel(s);
						button[i] = new JButton("查看");
						window3.add(MovieLabel);
						window3.add(button[i]);
						window3.add(new JLabel("             "));
						button[i].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								window3.setVisible(false);
								JFrame window4 = new JFrame("购票系统");
								JPanel p = new JPanel();
								JPanel ps = new JPanel();
								JLabel label[][] = new JLabel[10][10];
								GridLayout grid = new GridLayout(10,10);
								p.setLayout(grid);
								try {
									File file2 = new File("D:/影城系统/电影座次",s+".txt");
									BufferedReader r2 = new BufferedReader(new FileReader(file2));
									int t = 0;
									String str;
									while((str=r2.readLine())!=null) {
										char[] lines = str.toCharArray();
										for(int i=0;i<10;i++) {label[t][i] = new JLabel(lines[i]+"");p.add(label[t][i]);}
										t++;
									}
								}catch(Exception e1) {
									e1.printStackTrace();
								}
								window4.add(ps,BorderLayout.NORTH);
								window4.add(p,BorderLayout.CENTER);
								window4.setVisible(true);
								window4.setBounds(500,250,488,512);
								
								JFrame window5 = new JFrame(s);
								JTextArea textshow = new JTextArea(9,15);
								window5.setLayout(new FlowLayout());
								window5.add(new JLabel(s+" 放映场次如下:"));
								window5.add(textshow);
								try {
									File file2 = new File("D:/影城系统/放映厅信息",s+".txt");
									BufferedReader br = new BufferedReader(new FileReader(file2));
									String str;
									while((str=br.readLine())!=null) {
										textshow.append(str+'\n');
									}
									br.close();
								}catch(Exception e1) {
									e1.printStackTrace();
								}
								window5.setBounds(500,250,244,256);
								window5.setVisible(true);
								
							}
						});
					}
					r.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				window3.setBounds(500,250,244,256);
				window3.setVisible(true);
			}
		});
		item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame window3 = new JFrame();
				JLabel label = new JLabel("电影列表:                                                    ");
				JButton []button = new JButton[10];
				window3.setLayout(new FlowLayout());
				window3.add(label);
				try {
					File divfile = new File("D:/影城系统/电影信息");
					File[] file = divfile.listFiles();
					BufferedReader r = new BufferedReader(new FileReader(file[0]));
					for(int i=0;i<file.length;i++) {
						String s = file[i].getName().replace(".txt", "");
						JLabel MovieLabel = new JLabel(s);
						button[i] = new JButton("购票");
						window3.add(MovieLabel);
						window3.add(button[i]);
						window3.add(new JLabel("             "));
						button[i].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								window3.setVisible(false);
								JFrame window4 = new JFrame("购票系统");
								JPanel p = new JPanel();
								JPanel ps = new JPanel();
								JTextField L = new JTextField(3);
								JTextField R = new JTextField(3);
								JTextField text = new JTextField(10);
								JButton buy = new JButton("售票");
								JLabel label[][] = new JLabel[10][10];
								GridLayout grid = new GridLayout(10,10);
								p.setLayout(grid);
								try {
									File file2 = new File("D:/影城系统/电影座次",s+".txt");
									BufferedReader r2 = new BufferedReader(new FileReader(file2));
									int t = 0;
									String str;
									while((str=r2.readLine())!=null) {
										char[] lines = str.toCharArray();
										for(int i=0;i<10;i++) {label[t][i] = new JLabel(lines[i]+"");p.add(label[t][i]);}
										t++;
									}
								}catch(Exception e1) {
									e1.printStackTrace();
								}
								ps.add(new JLabel("请输入用户名"));
								ps.add(text);
								ps.add(new JLabel("请输入行号:"));
								ps.add(L);
								ps.add(new JLabel("请输入列号:"));
								ps.add(R);
								ps.add(buy);
								buy.addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent e) {
										char []line = L.getText().toCharArray();
										char []row = R.getText().toCharArray();
										int l,r;
										if(line.length == 1) l = line[0]-'0';
										else l = 10*(line[0]-'0')+line[1]-'0';
										if(line.length == 1) r = row[0] - '0';
										else r = 10*(line[0]-'0')+line[1]-'0';
										if(label[l][r].getText().equals("x")) JOptionPane.showMessageDialog(null, "该座位已被占，请重新选择");
										else {
										window4.setVisible(false);
										try {
											File file3 = new File("D:/影城系统/电影座次",s+".txt");
											File file4 = new File("D:/影城系统/影票信息",text.getText()+".txt");
											BufferedReader br = new BufferedReader(new FileReader(file3));
											BufferedWriter w = new BufferedWriter(new FileWriter(file4,true));
											//生成影票编号
											int randomnum = (int)(Math.random()*100000+10000);
											w.write(Integer.toString(randomnum)+"   片名:"+s);
											w.newLine();
											w.flush();
											w.close();
											String []strs = new String[10];
											String content;
											int count = 0;
											while((content=br.readLine())!=null) {
												if(count==l) {
												char []ch = content.toCharArray();
												for(int i=0;i<ch.length;i++) {
													if(i == r) ch[i]='x';
												}
												String str = new String(ch);
												strs[count++] = str;
												}
												else strs[count++] = content;
											}
											br.close();
											BufferedWriter sbr = new BufferedWriter(new FileWriter(file3));
											for(int i=0;i<strs.length;i++) {
												sbr.write(strs[i]);
												sbr.newLine();
												sbr.flush();
											}
											sbr.close();
											JOptionPane.showMessageDialog(null, "购买成功！编号为:"+Integer.toString(randomnum));
										}catch(Exception e1) {
											e1.printStackTrace();
										}
										
										}
									}
								});
								window4.add(ps,BorderLayout.NORTH);
								window4.add(p,BorderLayout.CENTER);
								window4.setVisible(true);
								window4.setBounds(500,250,488,512);
							}
						});
					}
					r.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				window3.setBounds(500,250,244,256);
				window3.setVisible(true);
			}
		});
		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReceptionWindow.setVisible(false);
			}
		});
		
	}
}