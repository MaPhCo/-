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

public class UserWindow{
	JFrame UserWindow;
	JTextArea TextShow;
	JMenuBar Menubar;
	JMenu menu1,menu2;
	JMenuItem item1,item2,item3,item4,item5,item6,item7,item8;
	UserWindow(String Account){
		UserWindow = new JFrame("用户");
		TextShow = new JTextArea(30,40);
		Menubar = new JMenuBar();
		menu1 = new JMenu("菜单");
		menu2 = new JMenu("购票");
		item1 = new JMenuItem("修改密码");
		item2 = new JMenuItem("查看所有电影放映信息");
		item3 = new JMenuItem("查看指定电影放映信息");
		item4 = new JMenuItem("购票");
		item5 = new JMenuItem("付账");
		item6 = new JMenuItem("取票");
		item7 = new JMenuItem("查看购票历史");
		item8 = new JMenuItem("退出登录");
		Add();
		Init();
		setMyCommandListener(Account);
	}	
	void Init(){
		UserWindow.setBounds(500,250,488,512);
		UserWindow.setVisible(true);
		UserWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void Add() {
		UserWindow.setLayout(new FlowLayout());
		menu1.add(item1);
		menu1.add(menu2);
		menu1.add(item8);
		menu2.add(item2);
		menu2.add(item3);
		menu2.add(item4);
		menu2.add(item5);
		menu2.add(item6);
		menu2.add(item7);
		Menubar.add(menu1);
		UserWindow.setJMenuBar(Menubar);
		UserWindow.add(TextShow);
	}
	void setMyCommandListener(String Account) {
		item8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserWindow.setVisible(false);
			}
		});
		item1.addActionListener(new ActionListener(){
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
							File file = new File("D:/影城系统/用户信息",Account+".txt");
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
		item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame window3 = new JFrame();
				JTextField MovieName = new JTextField(10);
				JLabel label = new JLabel("请输入要查找的电影名字");
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
							File file = new File("D:/影城系统/电影信息",MovieName.getText()+".txt");
							if(!file.exists()) JOptionPane.showMessageDialog(null, "未查询到相关电影信息!");
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
		item4.addActionListener(new ActionListener() {
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
								JButton buy = new JButton("购买");
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
								ps.add(new JLabel("o表示空座，x表示占座。   请输入行号:"));
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
											File file4 = new File("D:/影城系统/购票信息",Account+".txt");
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
											JOptionPane.showMessageDialog(null, "购票成功！");
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
		item5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int TicketNumber = 0;
				String []Tickets = new String[100];
				try {
					File file = new File("D:/影城系统/购票信息",Account+".txt");
					BufferedReader r = new BufferedReader(new FileReader(file));
					String s;
					while((s=r.readLine())!=null) {
						Tickets[TicketNumber++] = s;
					}
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				JFrame window2 = new JFrame("付账系统");
				String num = Integer.toString(TicketNumber);
				JLabel label = new JLabel("您当前有"+num+"张票未支付                                                                  ");
				JLabel[] labels = new JLabel[TicketNumber];
				JButton[] buttons = new JButton[TicketNumber];
				window2.setLayout(new FlowLayout());
				window2.add(label);
				for(int i=0;i<TicketNumber;i++) {
					String target = Tickets[i];
					int Number = TicketNumber-1;
					labels[i] = new JLabel("影票编号:"+Tickets[i]+"                      ");
					buttons[i] = new JButton("支付");
					window2.add(labels[i]);
					window2.add(buttons[i]);
					window2.add(new JLabel("                                            "));
					buttons[i].addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							try {
								JOptionPane.showMessageDialog(null, "支付成功！");
								window2.setVisible(false);
								File file2 = new File("D:/影城系统/购票信息",Account+".txt");
								File file3 = new File("D:/影城系统/影票信息",Account+".txt");
								BufferedWriter w = new BufferedWriter(new FileWriter(file3,true));
								w.write(target);
								w.newLine();
								w.flush();
								w.close();
								BufferedReader br = new BufferedReader(new FileReader(file2));
								String []strs = new String[Number];
								String content;
								int count = 0;
								while((content=br.readLine())!=null) {
									if(!content.equals(target)) {strs[count++] = content;}
								}
								br.close();
								BufferedWriter sbr = new BufferedWriter(new FileWriter(file2));
								for(int i=0;i<strs.length;i++) {
									sbr.write(strs[i]);
									sbr.newLine();
									sbr.flush();
								}
								sbr.close();
							}catch(Exception e1) {
								e1.printStackTrace();
							}
						}
					});
				}
				window2.setBounds(500,250,488,512);
				window2.setVisible(true);
			}
		});
		item6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame window2 = new JFrame("取票系统");
				JLabel label = new JLabel("请输入影票编号取票");
				JTextField text = new JTextField(10);
				JButton button = new JButton("取票");
				window2.setLayout(new FlowLayout());
				window2.add(label);
				window2.add(text);
				window2.add(button);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String target = text.getText();
						try {
							File file = new File("D:/影城系统/影票信息",Account+".txt");
							BufferedReader r = new BufferedReader(new FileReader(file));
							String s,str;
							boolean flag = false;
							while((s=r.readLine())!=null) {
								char []ch = new char[5];
								for(int i=0;i<5;i++) {
									ch[i] = s.charAt(i);
								}
								str = new String(ch);
								if(target.equals(str)) flag = true;
							}
							if(flag) JOptionPane.showMessageDialog(null, "取票成功！");
							else JOptionPane.showMessageDialog(null, "编号错误！");
						}catch(Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				window2.setBounds(500,250,488,512);
				window2.setVisible(true);
			}
		});
		item7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame window2 = new JFrame();
				JButton button = new JButton("查询购票历史信息");
				JTextArea textshow = new JTextArea(9,30);
				window2.setLayout(new FlowLayout());
				window2.add(button);
				window2.add(textshow);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							File file = new File("D:/影城系统/影票信息",Account+".txt");
							BufferedReader r = new BufferedReader(new FileReader(file));
							String s;
							int count = 1;
							while((s=r.readLine())!=null) {
								textshow.append("第"+Integer.toString(count)+"次购票:   "+s+'\n');
								count++;
							}
							r.close();
						}catch(Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				window2.setBounds(500,250,488,512);
				window2.setVisible(true);
			}
		});
	}
}


