package tw.edu.nptu.G03_1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameScore extends JFrame {
	JPanel p = new JPanel();
	JLabel one = new JLabel();
	JLabel two = new JLabel();
	JLabel three = new JLabel();
	JLabel four = new JLabel( );
	JLabel five = new JLabel();
	int a[]={0,0,0,0,0};
	FrameScore() {
		p.setPreferredSize(new Dimension(300, 370));
		p.setBackground(new Color(60, 60, 60));
		p.setLayout(null);
		this.setPreferredSize(new Dimension(300, 370));
		this.setTitle("記分板");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.add(p);
		this.pack();
		this.setLocation(1350, 500);
		this.setLayout(null);
		this.setVisible(true);
	}

	public void ShowUp(int a[]) {
		p.removeAll();
		p.validate();
		if (a[0]!=0){one.setText("第一名:" +a[0] +"分");}
		if (a[1]!=0){two.setText("第二名:" +a[1] +"分");}
		if (a[2]!=0){three.setText("第三名:" +a[2] +"分");}
		if (a[3]!=0){four.setText("第四名:" +a[3] +"分");}
		if (a[4]!=0){five.setText("第五名:" +a[4] +"分");}

		one.setForeground(Color.white);
		one.setBounds(40, 50, 300, 80);
		one.setFont(new Font("標楷體", Font.BOLD, 30));

		two.setForeground(Color.white);
		two.setBounds(40, 90, 300, 80);
		two.setFont(new Font("標楷體", Font.BOLD, 30));

		three.setForeground(Color.white);
		three.setBounds(40, 130, 300, 80);
		three.setFont(new Font("標楷體", Font.BOLD, 30));

		four.setForeground(Color.white);
		four.setBounds(40, 170, 300, 80);
		four.setFont(new Font("標楷體", Font.BOLD, 30));

		five.setForeground(Color.white);
		five.setBounds(40, 210, 300, 80);
		five.setFont(new Font("標楷體", Font.BOLD, 30));
		p.repaint();

		p.add(one);
		p.add(two);
		p.add(three);
		p.add(four);
		p.add(five);
		repaint();

	}

	public void ShowUp2(int flag) {
		p.removeAll();
		p.validate();
		JLabel one = new JLabel("恭喜玩家" + flag + " 獲勝");
		one.setForeground(Color.white);
		one.setBounds(40, 10, 300, 80);
		one.setFont(new Font("標楷體", Font.BOLD, 30));

		p.add(one);
		repaint();

	}
}
