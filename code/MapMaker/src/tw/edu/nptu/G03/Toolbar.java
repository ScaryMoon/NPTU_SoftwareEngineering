package tw.edu.nptu.G03;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

//@SuppressWarnings("serial")
public class Toolbar extends JPanel {
    static int setpnum = 0;
    Frame mainframe;

    SaveDB connect2saveDB;
    LoadDB connect2loadDB;
    Toolbar(Frame frame) {
        super();
        mainframe = frame;
        JMenuBar menuBar = new JMenuBar();
        mainframe.setJMenuBar(menuBar);

        JMenu file = new JMenu("檔案");
        menuBar.add(file);
        JMenu page = new JMenu("頁面");
        menuBar.add(page);
        JMenu changepage = new JMenu("切換頁面");
        menuBar.add(changepage);

        JMenuItem save = new JMenuItem("儲存");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if (mainframe.pages.size() == 0) {
                    confirm con = new confirm(mainframe);
                    final JDialog errDialog = con.warningWindow2();
                    errDialog.setVisible(true);
            	} else {
            		mainframe.activePage.issaved = true;
            		// 要儲存到資料庫的資料: 地圖元件座標、元件顏色、背景顏色等
                    connect2saveDB=new SaveDB(mainframe.activePage);
            		System.out.println("儲存成功!");
                    System.out.println(frame.mainp.getBackground());
            	}
            }
        });
        file.add(save);

        JMenuItem out = new JMenuItem("匯出");
        out.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (mainframe.pages.size() > 0) {
                	mainframe.activePage.issaved = true;
                	mainframe.activePage.repaint();
                	mainframe.activePage.validate();
                    for (int i = 0 ; i <= 19 ; i++) {
                        for (int j = 0 ; j <= 19 ; j++) {
                        	mainframe.activePage.mapArray[i][j] = 0;
                        }
                    }
                    try {  // -----------------------------------------------------------------------------匯出
                        int Ur = 0, Ug = 0, Ub = 0, Pr = 0, Pg = 0, Pb = 0;
                        FileWriter f = new FileWriter("myFile.map");
                        for (int i = 0 ; i <= 19 ; i++) {
                            for (int j = 0 ; j <= 19 ; j++) {
                                if (mainframe.activePage.getComponentAt(j * mainframe.activePage.unit + 17, i * mainframe.activePage.unit + 17)
                                        .getClass() != mainframe.activePage.getClass()) {
                                                    Ur=mainframe.activePage.getComponentAt(j * mainframe.activePage.unit + 17, i * mainframe.activePage.unit + 17).getBackground().getRed();
                                                    Ug=mainframe.activePage.getComponentAt(j * mainframe.activePage.unit + 17, i * mainframe.activePage.unit + 17).getBackground().getGreen();
                                                    Ub=mainframe.activePage.getComponentAt(j * mainframe.activePage.unit + 17, i * mainframe.activePage.unit + 17).getBackground().getBlue();
                                                    mainframe.activePage.mapArray[i][j] = 1;
                                    f.write("1");
                                } else{
                                    Pr=mainframe.activePage.getBackground().getRed();
                                    Pg=mainframe.activePage.getBackground().getGreen();
                                    Pb=mainframe.activePage.getBackground().getBlue();
                                    f.write("0");    
                                }

                                    
                                System.out.print(mainframe.activePage.mapArray[i][j]);
                            }
                            System.out.println();
                            f.write("\n");
                        }

                        f.write(Integer.toString(Ur));
                        f.write(" ");
                        f.write(Integer.toString(Ug));
                        f.write(" ");
                        f.write(Integer.toString(Ub));
                        f.write(" ");
                        f.write(Integer.toString(Pr));
                        f.write(" ");
                        f.write(Integer.toString(Pg));
                        f.write(" ");
                        f.write(Integer.toString(Pb));

                        System.out.println();
                        f.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        file.add(out);

        JMenuItem load = new JMenuItem("載入");
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (mainframe.pages.size() == 0) {
                    connect2loadDB=new LoadDB(mainframe,mainframe.activePage);
                } else {
                    confirm con = new confirm(mainframe);
                    final JDialog errDialog = con.warningWindow1();
                    errDialog.setVisible(true);
                }
            }
        });
        file.add(load);

        JMenuItem create = new JMenuItem("新增");
        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
				if (mainframe.activePage2 != null) {
					mainframe.remove(mainframe.activePage2);
					mainframe.activePage2 = null;
				}
				if (mainframe.activePage != null) {
					mainframe.remove(mainframe.activePage);
				}

				mainframe.activePage = new Page(mainframe);
				mainframe.add(mainframe.activePage);
				mainframe.pages.add(mainframe.activePage);
				StatusPanel.currpage = StatusPanel.pagenum = mainframe.pages.size();
				mainframe.activePage.addcheck = true;
            	mainframe.validate();
            	mainframe.repaint();
            }
        });
        page.add(create);

        JMenuItem delete = new JMenuItem("刪除");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if (mainframe.activePage != null) {
                    confirm con = new confirm(mainframe);
                    final JDialog modelDialog = con.warningWindow3();
                    modelDialog.setVisible(true);
                    if (confirm.deleteState == false) {
                    	int cpindex = mainframe.pages.indexOf(mainframe.activePage);
    					Page otherPage = null;
    					if(cpindex != 0) {
    						StatusPanel.currpage = cpindex;
    						otherPage = mainframe.pages.get(cpindex - 1);
    						if(otherPage != null) {
    							mainframe.remove(mainframe.activePage);
    							mainframe.activePage = otherPage;
    							mainframe.pages.remove(cpindex);
    							mainframe.add(otherPage);
    							StatusPanel.pagenum = mainframe.pages.size();
    		            	    mainframe.validate();
    		            	    mainframe.repaint();
    						}
    					} else {
						    mainframe.remove(mainframe.activePage);
    						if (mainframe.pages.size() == 1) {
						    	mainframe.activePage = null;
						    	mainframe.pages.remove(cpindex);
						    	mainframe.add(mainframe.mainp);
						    	mainframe.activePage2 = mainframe.mainp;
						    } else if (mainframe.pages.size() > 1) {
						    	mainframe.activePage = mainframe.pages.get(cpindex + 1);
					    		mainframe.add(mainframe.pages.get(cpindex + 1));
					    		mainframe.pages.remove(cpindex);
						     	StatusPanel.pagenum = mainframe.pages.size();
						    }
	            	        mainframe.validate();
	            	        mainframe.repaint();
						}
                    }
            	} else {
                    confirm con = new confirm(mainframe);
                    final JDialog modelDialog = con.warningWindow2();
                    modelDialog.setVisible(true);
            	}
            }
        });
        page.add(delete);

        JMenuItem edit = new JMenuItem("編輯");
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if (mainframe.pages.size() > 0) {
                    if (mainframe.activePage.status == Status.Actived) {
                        confirm con = new confirm(mainframe);
                        final JDialog errDialog = con.warningWindow4();
                        errDialog.setVisible(true);
                    } else if (mainframe.activePage.status == Status.InActived) {
                    	mainframe.activePage.addcheck = true;
                        UnitPanel.addcheck_1 = true;
                        UnitPanel.addcheck_2 = true;
                        UnitPanel.addcheck_3 = true;
                        mainframe.activePage.status = Status.Actived;
	            	    repainted(mainframe);
                    }
            	} else {
                    confirm con = new confirm(mainframe);
                    final JDialog errDialog = con.warningWindow2();
                    errDialog.setVisible(true);
            	}
            }
        });
        page.add(edit);

        JMenuItem bgc = new JMenuItem("改變背景顏色");
        bgc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if (mainframe.pages.size() != 0) {
                    confirm con = new confirm(mainframe);
                    final JDialog bgcDialog = con.changecolorWindow();
                    bgcDialog.setVisible(true);
                } else {
                    confirm con = new confirm(mainframe);
                    final JDialog errDialog = con.warningWindow2();
                    errDialog.setVisible(true);
                }
            }
        });
        page.add(bgc);

        JMenuItem prevp = new JMenuItem("前一頁");
        prevp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if (mainframe.pages.size() > 1) {
					int cpindex = mainframe.pages.indexOf(mainframe.activePage);
					Page prevPage = null;
					if(cpindex != 0) {
						StatusPanel.currpage = cpindex;
						prevPage = mainframe.pages.get(cpindex - 1);
						if(prevPage != null) {
							mainframe.remove(mainframe.activePage);
							mainframe.add(prevPage);
							mainframe.activePage = prevPage;
		            	    mainframe.validate();
		            	    mainframe.repaint();
						}
					}
            	} else {
                    confirm con = new confirm(mainframe);
                    final JDialog errDialog = con.warningWindow2();
                    errDialog.setVisible(true);
            	}
            }
        });
        changepage.add(prevp);

        JMenuItem nextp = new JMenuItem("下一頁");
        nextp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if (mainframe.pages.size() > 1) {
					int cpindex = mainframe.pages.indexOf(mainframe.activePage);
					Page nextPage = null;
					if(cpindex < mainframe.pages.size() - 1) {
						StatusPanel.currpage = cpindex + 2;
						nextPage = mainframe.pages.get(cpindex + 1);
						if(nextPage != null) {
							mainframe.remove(mainframe.activePage);
							mainframe.add(nextPage);
							mainframe.activePage = nextPage;
		            	    mainframe.validate();
		            	    mainframe.repaint();
						}
					}
            	} else {
                    confirm con = new confirm(mainframe);
                    final JDialog errDialog = con.warningWindow2();
                    errDialog.setVisible(true);
            	}
            }
        });
        changepage.add(nextp);

        JMenuItem firstp = new JMenuItem("首頁");
        firstp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if (mainframe.pages.size() > 0) {
    				StatusPanel.currpage = 1;
    				Page firsPage = null;
    				firsPage = mainframe.pages.get(0);
    				mainframe.remove(mainframe.activePage);
    				mainframe.add(firsPage);
    				mainframe.activePage = firsPage;
                    mainframe.validate();
                	mainframe.repaint();
            	} else {
                    confirm con = new confirm(mainframe);
                    final JDialog errDialog = con.warningWindow2();
                    errDialog.setVisible(true);
            	}
            }
        });
        changepage.add(firstp);

        JMenuItem finalp = new JMenuItem("末頁");
        finalp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if (mainframe.pages.size() > 0) {
    				StatusPanel.currpage = mainframe.pages.size();
    				Page endPage = null;
    				endPage = mainframe.pages.get(mainframe.pages.size() - 1);
    				mainframe.remove(mainframe.activePage);
    				mainframe.add(endPage);
    				mainframe.activePage = endPage;
                    mainframe.validate();
                	mainframe.repaint();
            	} else {
                    confirm con = new confirm(mainframe);
                    final JDialog errDialog = con.warningWindow2();
                    errDialog.setVisible(true);
            	}
            }
        });
        changepage.add(finalp);

        JMenuItem setp = new JMenuItem("指定頁數");
        setp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if (mainframe.pages.size() > 0) {
                    confirm con = new confirm(mainframe);
                    final JDialog setpDialog = con.pageenterWindow();
                    setpDialog.setVisible(true);

                    if (setpnum != 0) {
        				StatusPanel.currpage = setpnum;
        				Page assignPage = null;
        				assignPage = mainframe.pages.get(setpnum - 1);
        				mainframe.remove(mainframe.activePage);
        				mainframe.add(assignPage);
        				mainframe.activePage = assignPage;
                        mainframe.validate();
                    	mainframe.repaint();
                    }
            	} else {
                    confirm con = new confirm(mainframe);
                    final JDialog errDialog = con.warningWindow2();
                    errDialog.setVisible(true);
            	}
            }
        });
        changepage.add(setp);
    }

    public void repainted(Frame frame) {
    	frame.activePage.removeAll();
    	frame.activePage.validate();
    	frame.activePage.repaint();
    }
}