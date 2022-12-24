package tw.edu.nptu.G03;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {

	static final int w = 1040;
	static final int h = 700;

	MainPanel(Frame frame) {
		super();
		this.setPreferredSize(new Dimension(w, h + 1));
		this.setBackground(Color.white);
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.setFont(new Font("標楷體", Font.BOLD, 30));
        g.drawString("簡易地圖設計貪吃蛇編輯器 v0.0.1", w / 4 + 20, 50);
    }
}