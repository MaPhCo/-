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

public class ManageWindow{
	JFrame ManageWindow;
	JTextArea TextShow;
	JMenuBar Menubar;
	JMenu menu,menu1,menu2;
	JMenuItem item1,item2,item3,item4,item5,item6,item7,item8,item9,item10;
	ManageWindow(){
		ManageWindow = new JFrame("经理");
		TextShow = new JTextArea(30,40);
		Menubar = new JMenuBar();
		menu = new JMenu("菜单");
		menu1 = new JMenu("影片管理");
		menu2 = new JMenu("排片管理");
		item1 = new JMenuItem("列出所有正在上映的影片");
		item2 = new JMenuItem("添加影片的信息");
		item3 = new JMenuItem("修改电影的信息");
		item4 = new JMenuItem("删除影片的信息");
		item5 = new JMenuItem("查询影片的信息");
		item6 = new JMenuItem("增加场次");
		item7 = new JMenuItem("修改场次");
		item8 = new JMenuItem("删除场次");
		item9 = new JMenuItem("列出所有场次信息");
		item10 = new JMenuItem("退出登录");
		Add();
		Init();
		setMyCommandListener();
	}	
	void Init(){
		ManageWindow.setBounds(500,250,488,512);
		ManageWindow.setVisible(true);
		ManageWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	void Add() {
		ManageWindow.setLayout(new FlowLayout());
		menu.add(menu1);
		menu.add(menu2);
		menu.add(item10);
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menu1.add(item4);
		menu1.add(item5);
		menu2.add(item6);
		menu2.add(item7);
		menu2.add(item8);
		menu2.add(item9);
		Menubar.add(menu);
		ManageWindow.setJMenuBar(Menubar);
		ManageWindow.add(TextShow);
	}
	void setMyCommandListener() {
		item10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageWindow.setVisible(false);
			}
		});
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
				JTextField text,text1,text2,text3;
				JButton button = new JButton("确定");
				window3.setLayout(new FlowLayout());
				window3.add(new JLabel("请输入添加的电影的名字:"));
				window3.add(text = new JTextField(10));
				window3.add(new JLabel("请输入添加的电影的导演:"));
				window3.add(text1 = new JTextField(10));
				window3.add(new JLabel("请输入添加的电影的主演:"));
				window3.add(text2 = new JTextField(10));
				window3.add(new JLabel("请输入添加的电影的时长"));
				window3.add(text3 = new JTextField(10));
				window3.add(button);
				window3.setBounds(500,250,244,256);
				window3.setVisible(true);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							File file = new File("D:/影城系统/电影信息",text.getText()+".txt");
							file.createNewFile();
							BufferedWriter w = new BufferedWriter(new FileWriter(file));
							w.write("导演："+text1.getText()+'\n');
							w.write("主演："+text2.getText()+'\n');
							w.write("时长："+text3.getText()+'\n');
							w.flush();
							w.close();
							JOptionPane.showMessageDialog(null, "添加影片成功！");
							window3.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
		item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame window3 = new JFrame();
				JTextField text,text1,text2,text3;
				JButton button = new JButton("确定");
				window3.setLayout(new FlowLayout());
				window3.add(new JLabel("请输入修改的电影的名字:"));
				window3.add(text = new JTextField(10));
				window3.add(new JLabel("请输入修改的电影的导演:"));
				window3.add(text1 = new JTextField(10));
				window3.add(new JLabel("请输入修改的电影的主演:"));
				window3.add(text2 = new JTextField(10));
				window3.add(new JLabel("请输入修改的电影的时长"));
				window3.add(text3 = new JTextField(10));
				window3.add(button);
				window3.setBounds(500,250,244,256);
				window3.setVisible(true);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							File file = new File("D:/影城系统/电影信息",text.getText()+".txt");
							if(!file.exists()) 	JOptionPane.showMessageDialog(null, "添加失败！请检查片名是否正确");
							else {
								BufferedWriter w = new BufferedWriter(new FileWriter(file));
								w.write("导演："+text1.getText()+'\n');
								w.write("主演："+text2.getText()+'\n');
								w.write("时长："+text3.getText()+'\n');
								w.flush();
								w.close();
								JOptionPane.showMessageDialog(null, "修改影片成功！");
								window3.setVisible(false);
							}
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
				JTextField MovieName = new JTextField(10);
				JLabel label = new JLabel("请输入要删除的电影名字");
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
								file.delete();
								JOptionPane.showMessageDialog(null, "删除成功！");
							}
							window3.setVisible(false);
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
				JLabel label = new JLabel("请输入要查询的电影名字");
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
								JOptionPane.showMessageDialog(null, "查询成功！");
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
						button[i] = new JButton("增加场次");
						window3.add(MovieLabel);
						window3.add(button[i]);
						window3.add(new JLabel("             "));
						button[i].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								JFrame window4 = new JFrame(s);
								JTextField text = new JTextField(10);
								JButton addbutton = new JButton("确定");
								window4.setLayout(new FlowLayout());
								window4.add(new JLabel(" 请输入要增加的放映厅序号:"));
								window4.add(text);
								window4.add(addbutton);
								addbutton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										JOptionPane.showMessageDialog(null, "增加成功！");
										window4.setVisible(false);
										try {
											File file2 = new File("D:/影城系统/放映厅信息",s+".txt");
											BufferedWriter sbr = new BufferedWriter(new FileWriter(file2,true));
											sbr.write(text.getText());
											sbr.newLine();
											sbr.flush();
											sbr.close();
										}catch(Exception e1) {
											e1.printStackTrace();
										}
									}
								});
								window4.setBounds(500,250,244,256);
								window4.setVisible(true);
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
		item7.addActionListener(new ActionListener() {
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
						button[i] = new JButton("修改场次");
						window3.add(MovieLabel);
						window3.add(button[i]);
						window3.add(new JLabel("             "));
						button[i].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								JFrame window4 = new JFrame(s);
								JTextField text1 = new JTextField(10);
								JTextField text2 = new JTextField(10);
								JButton addbutton = new JButton("确定");
								window4.setLayout(new FlowLayout());
								window4.add(new JLabel(" 请输入修改的放映厅序号:"));
								window4.add(text1);
								window4.add(new JLabel(" 请输入修改后的放映厅序号"));
								window4.add(text2);
								window4.add(addbutton);
								addbutton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										window4.setVisible(false);
										try {
											File file2 = new File("D:/影城系统/放映厅信息",s+".txt");
											BufferedReader br = new BufferedReader(new FileReader(file2));
											String s;
											String []str = new String[10];
											int count=0;
											boolean flag = false;
											while((s=br.readLine())!=null) {
												if(s.equals(text1.getText())) {
													flag = true;
													str[count++] = text2.getText();
												}
												else 	str[count++] = s;
											}
											br.close();
											if(flag) {
												BufferedWriter sbr = new BufferedWriter(new FileWriter(file2));
												for(int i=0;i<str.length;i++) {
													if(str[i]==null) break;
													sbr.write(str[i]);
													sbr.newLine();
													sbr.flush();
												}
												sbr.close();
												JOptionPane.showMessageDialog(null, "修改成功！");
											}
											else JOptionPane.showMessageDialog(null, "未找到要修改的放映厅！！");
										}catch(Exception e1) {
											e1.printStackTrace();
										}
									}
								});
								window4.setBounds(500,250,244,256);
								window4.setVisible(true);
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
		item8.addActionListener(new ActionListener() {
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
						button[i] = new JButton("修改场次");
						window3.add(MovieLabel);
						window3.add(button[i]);
						window3.add(new JLabel("             "));
						button[i].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								JFrame window4 = new JFrame(s);
								JTextField text = new JTextField(10);
								JButton addbutton = new JButton("确定");
								window4.setLayout(new FlowLayout());
								window4.add(new JLabel(" 请输入要删除的放映厅序号:"));
								window4.add(text);
								window4.add(addbutton);
								addbutton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										window4.setVisible(false);
										try {
											File file2 = new File("D:/影城系统/放映厅信息",s+".txt");
											BufferedReader br = new BufferedReader(new FileReader(file2));
											String s;
											String []str = new String[10];
											int count=0;
											boolean flag = false;
											while((s=br.readLine())!=null) {
												if(s.equals(text.getText())) {
													flag = true;
													continue;
												}
												str[count++] = s;
											}
											br.close();
											if(flag) {
												BufferedWriter sbr = new BufferedWriter(new FileWriter(file2));
												for(int i=0;i<str.length;i++) {
													if(str[i]==null) break;
													sbr.write(str[i]);
													sbr.newLine();
													sbr.flush();
												}
												sbr.close();
												JOptionPane.showMessageDialog(null, "删除成功！");
											}
											else JOptionPane.showMessageDialog(null, "未找到要删除的放映厅！");
										}catch(Exception e1) {
											e1.printStackTrace();
										}
									}
								});
								window4.setBounds(500,250,244,256);
								window4.setVisible(true);
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
		item9.addActionListener(new ActionListener() {
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
						button[i] = new JButton("查询场次");
						window3.add(MovieLabel);
						window3.add(button[i]);
						window3.add(new JLabel("             "));
						button[i].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								JFrame window4 = new JFrame(s);
								JTextArea textshow = new JTextArea(9,15);
								window4.setLayout(new FlowLayout());
								window4.add(new JLabel(s+" 放映场次如下:"));
								window4.add(textshow);
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
								window4.setBounds(500,250,244,256);
								window4.setVisible(true);
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
	}
}

