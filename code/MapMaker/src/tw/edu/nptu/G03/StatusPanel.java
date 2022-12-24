package tw.edu.nptu.G03;

import java.awt.*;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
    int r = 240, b = 240, g = 240;
    Page p;
    Frame frame;
    static int pagenum, currpage;

    StatusPanel(Page panel) {
        p = panel;
        int height = p.getPreferredSize().height;
        int width = p.getPreferredSize().width;
        this.setBounds(height, 0, width - height, 140);
        this.setBackground(new Color(r, g, b));
        this.setLayout(null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);
        g.setFont(new Font("標楷體", Font.BOLD, 25));
        if (p.status == Status.InActived) {
            g.drawString("目前狀態:非編輯模式", 45, 75);
        } else if (p.status == Status.Actived) {
            g.drawString("目前狀態:編輯模式", 45, 75);
        }
        g.setColor(Color.black);
        g.setFont(new Font("", Font.PLAIN, 18));
        g.drawString("<Page: " + currpage + "/" + pagenum + ">", 115, 30);
        if (p.issaved == true) {
            g.setColor(Color.green);
            g.setFont(new Font("", Font.PLAIN, 20));
            g.drawString("O", 10, 30);
        } else {
            g.setColor(Color.red);
            g.setFont(new Font("", Font.PLAIN, 20));
            g.drawString("X", 10, 30);
        }
    }
}