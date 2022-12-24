package tw.edu.nptu.G03;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

@SuppressWarnings("serial")
public class confirm extends JPanel {
	static boolean deleteState = false;
    static boolean cun;
    boolean lock = true;
    Frame fr;

    JPanel[] colorp = new JPanel[] { new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(),
                      new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel() };
    int[][] colorn = new int[][] { { 255, 255, 255 }, { 15, 235, 224 }, { 253, 216, 93 }, { 183, 131, 56 }, { 254, 213, 217 },
    	                           { 21, 14, 3 }, { 70, 34, 85 }, { 225, 220, 217 }, { 2, 52, 89 }, { 73, 142, 175 } };
    int[][] coloru = new int[][] { { 96, 150, 186 }, { 40, 54, 24 }, { 157, 129, 137 }, { 47, 24, 18 }, { 204, 211, 217 },
    	                           { 219, 177, 42 }, { 221, 255, 247 }, { 50, 67, 95 }, { 178, 165, 158 }, { 229, 187, 75 } };
    confirm(Frame f) {
        super();
        fr = f;
    }

    public JDialog warningWindow1() {
        final JDialog subWindow = new JDialog(fr, "警告", Dialog.ModalityType.DOCUMENT_MODAL);
        subWindow.setSize(250, 120);
        subWindow.setLocationRelativeTo(null);
        Container dialogContainer = subWindow.getContentPane();
        dialogContainer.setLayout(new BorderLayout());

        JPanel p0 = new JPanel();
        p0.setLayout(new FlowLayout());
        JLabel l1 = new JLabel("錯誤! 地圖已存在");
        l1.setFont(new Font("標楷體", Font.BOLD, 20));
        p0.add(l1);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("确定");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subWindow.setVisible(false);
            }
        });
        p1.add(okButton);

        dialogContainer.add(p0, BorderLayout.NORTH);
        dialogContainer.add(p1, BorderLayout.SOUTH);
        return subWindow;
    }

    public JDialog warningWindow2() {
        final JDialog subWindow = new JDialog(fr, "警告", Dialog.ModalityType.DOCUMENT_MODAL);
        subWindow.setSize(250, 120);
        subWindow.setLocationRelativeTo(null);
        Container dialogContainer = subWindow.getContentPane();
        dialogContainer.setLayout(new BorderLayout());

        JPanel p0 = new JPanel();
        p0.setLayout(new FlowLayout());
        JLabel l1 = new JLabel("錯誤! 地圖不存在");
        l1.setFont(new Font("標楷體", Font.BOLD, 20));
        p0.add(l1);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("确定");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subWindow.setVisible(false);
            }
        });
        p1.add(okButton);

        dialogContainer.add(p0, BorderLayout.NORTH);
        dialogContainer.add(p1, BorderLayout.SOUTH);
        return subWindow;
    }

    public JDialog warningWindow3() {
        final JDialog subWindow = new JDialog(fr, "警告", Dialog.ModalityType.DOCUMENT_MODAL);
        subWindow.setSize(250, 120);
        subWindow.setLocationRelativeTo(null);
        Container dialogContainer = subWindow.getContentPane();
        dialogContainer.setLayout(new BorderLayout());

        JPanel p0 = new JPanel();
        p0.setLayout(new FlowLayout());
        JLabel l1 = new JLabel("確定刪除?");
        l1.setFont(new Font("標楷體", Font.BOLD, 20));
        p0.add(l1);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("确定");
        JButton noButton = new JButton("取消");
        deleteState = true;
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteState = false;
                subWindow.setVisible(false);
                fr.activePage.units = new Vector<>();
                for (int i = 0 ; i <= 19 ; i++) {
                    for (int j = 0 ; j <= 19 ; j++) {
                        fr.activePage.mapArray[j][i] = 0;
                    }
                }
            }
        });
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subWindow.setVisible(false);
            }
        });
        p1.add(okButton);
        p1.add(noButton);

        dialogContainer.add(p0, BorderLayout.NORTH);
        dialogContainer.add(p1, BorderLayout.SOUTH);
        return subWindow;
    }

    public JDialog warningWindow4() {
        final JDialog subWindow = new JDialog(fr, "警告", Dialog.ModalityType.DOCUMENT_MODAL);
        subWindow.setSize(250, 120);
        subWindow.setLocationRelativeTo(null);
        Container dialogContainer = subWindow.getContentPane();
        dialogContainer.setLayout(new BorderLayout());

        JPanel p0 = new JPanel();
        p0.setLayout(new FlowLayout());
        JLabel l1 = new JLabel("已在編輯模式!");
        l1.setFont(new Font("標楷體", Font.BOLD, 20));
        p0.add(l1);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("确定");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subWindow.setVisible(false);
            }
        });
        p1.add(okButton);

        dialogContainer.add(p0, BorderLayout.NORTH);
        dialogContainer.add(p1, BorderLayout.SOUTH);
        return subWindow;
    }

    public JDialog pageenterWindow() {
        final JDialog subWindow = new JDialog(fr, "頁碼輸入", Dialog.ModalityType.DOCUMENT_MODAL);
        subWindow.setSize(320, 150);
        subWindow.setLocationRelativeTo(null);
        Container dialogContainer = subWindow.getContentPane();
        dialogContainer.setLayout(new BorderLayout());

        JPanel p0 = new JPanel();
        p0.setLayout(new FlowLayout());
        JLabel l1 = new JLabel("請輸入頁碼: (點擊文字框後再輸入)");
        l1.setFont(new Font("標楷體", Font.BOLD, 18));
        p0.add(l1);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JTextField tx1 = (new JTextField());
        tx1.setText("     請點擊輸入數值     ");
        tx1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	if (lock) {
            	    tx1.setText("");
            	    lock = !lock;
            	}
            }
        });
        p1.add(tx1);

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("确定");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		if ((Integer.valueOf(tx1.getText()) > 0) && (Integer.valueOf(tx1.getText()) <= fr.pages.size())) {
                		Toolbar.setpnum = Integer.valueOf(tx1.getText());
                		subWindow.setVisible(false);
                	} else {
                		tx1.setText("     數值範圍錯誤!     ");
                		lock = true;
                	}
            	} catch (NumberFormatException e1) {
            		tx1.setText("     資料輸入錯誤!     ");
            		lock = true;
            	}
            }
        });
        p2.add(okButton);

        dialogContainer.add(p0, BorderLayout.NORTH);
        dialogContainer.add(p1, BorderLayout.CENTER);
        dialogContainer.add(p2, BorderLayout.SOUTH);
        return subWindow;
    }

    public JDialog changecolorWindow() {
        final JDialog subWindow = new JDialog(fr, "變更背景顏色", Dialog.ModalityType.DOCUMENT_MODAL);
        subWindow.setSize(340, 270);

        int n = 7;
        for (int i = 0 ; i < colorn.length ; i++) {
            colorp[i].setBackground(new Color(colorn[i][0], colorn[i][1], colorn[i][2]));
            if (i == 5)
                n = 7;
            if (i < 5) {
                colorp[i].setBounds(n, 30, 45, 45);
            } else {
                colorp[i].setBounds(n, 108, 45, 45);
            }
            n += 66;
        }

        subWindow.setLocationRelativeTo(null);
        Container dialogContainer = subWindow.getContentPane();
        dialogContainer.setLayout(new BorderLayout());
        dialogContainer.setBackground(Color.GREEN);

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("确定");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subWindow.setVisible(false);
            }
        });
        p1.add(okButton);

        for (int i = 0 ; i < colorp.length ; i++) {
            dialogContainer.add(colorp[i]);
        }
        dialogContainer.add(p1, BorderLayout.SOUTH);

        colorp[0].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                unitcolor(0);
                fr.activePage.issaved = false;
            }
        });
        colorp[1].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                unitcolor(1);
                fr.activePage.issaved = false;
            }
        });
        colorp[2].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                unitcolor(2);
                fr.activePage.issaved = false;
            }
        });
        colorp[3].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                unitcolor(3);
                fr.activePage.issaved = false;
            }
        });
        colorp[4].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                unitcolor(4);
                fr.activePage.issaved = false;
            }
        });
        colorp[5].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                unitcolor(5);
                fr.activePage.issaved = false;
            }
        });
        colorp[6].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                unitcolor(6);
                fr.activePage.issaved = false;
            }
        });
        colorp[7].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                unitcolor(7);
                fr.activePage.issaved = false;
            }
        });
        colorp[8].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                unitcolor(8);
                fr.activePage.issaved = false;
            }
        });
        colorp[9].addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                unitcolor(9);
                fr.activePage.issaved = false;
            }
        });
        return subWindow;
    }

    public void unitcolor(int num) {
        fr.activePage.units.forEach((a) -> {
            for (int i = 0 ; i < MainPanel.h / fr.activePage.unit ; i++) {
                int x = 0 + i * fr.activePage.unit + 1;
                for (int j = 0 ; j < MainPanel.h / fr.activePage.unit ; j++) {
                    int y = 0 + j * fr.activePage.unit + 1;
                    if (a == fr.activePage.getComponentAt(x, y)) {
                        fr.activePage.getComponentAt(x, y)
                                .setBackground(new Color(coloru[num][0], coloru[num][1], coloru[num][2]));
                    }
                }
            }
        });
        cun = true;
        UnitPanel.addcheck_1 = true;
        UnitPanel.addcheck_2 = true;
        UnitPanel.addcheck_3 = true;
        fr.activePage.Initcolor(coloru[num][0], coloru[num][1], coloru[num][2]);
        fr.activePage.setBackground(colorp[num].getBackground());
        fr.repaint();
        fr.validate();
    }
}