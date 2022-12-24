package tw.edu.nptu.G03;

import java.awt.*;

import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JTextField;

//@SuppressWarnings("serial")
public class UnitPanel extends JPanel {
    int r = 240, g = 240, b = 240;
    int x = 0;
    int y = 0;
    int z = MainPanel.h;
    int z1 = MainPanel.h;

    static boolean addcheck_1, addcheck_2, addcheck_3;

    Page p;
    UnitPanel unitpanel = this;
    JTextField textFieldX = new JTextField();
    JTextField textFieldY = new JTextField();
    JTextField textFieldW = new JTextField();
    JTextField textFieldH = new JTextField();

    UnitPanel(Page panel) {
        p = panel;

        int height = panel.getPreferredSize().height;
        int width = panel.getPreferredSize().width;
        this.setBounds(height, 140, width, height);
        this.setBackground(new Color(r, g, b));
        this.setLayout(null);

        textFieldX.addKeyListener(new MyKey());
        textFieldY.addKeyListener(new MyKey());
        textFieldH.addKeyListener(new MyKey());
        textFieldW.addKeyListener(new MyKey());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (p.status == Status.Actived) {
            g.setColor(Color.black);
            g.setFont(new Font("標楷體", Font.BOLD, 21));
            g.drawString("地圖元件區", (this.getSize().width - this.getSize().height) / 4 + 25, 20);

            if (confirm.cun) {
                this.removeAll();
                confirm.cun = !confirm.cun;
            }
            if (p.com != null) {
                g.drawString("X", (this.getSize().width - this.getSize().height) / 4 - 30, 67);

                textFieldX.setBounds((this.getSize().width - this.getSize().height) / 4, 45, 160, 28);
                int x = p.com.getX();
                String xx = Integer.toString(x);
                textFieldX.setText(xx);

                g.drawString("Y", (this.getSize().width - this.getSize().height) / 4 - 30, 107);
                textFieldY.setBounds((this.getSize().width - this.getSize().height) / 4, 85, 160, 28);
                int y = p.com.getY();
                String yy = Integer.toString(y);
                textFieldY.setText(yy);

                g.drawString("W", (this.getSize().width - this.getSize().height) / 4 - 30, 147);
                textFieldW.setBounds((this.getSize().width - this.getSize().height) / 4, 125, 160, 28);
                int w = p.com.getWidth();
                String ww = Integer.toString(w);
                textFieldW.setText(ww);

                g.drawString("H", (this.getSize().width - this.getSize().height) / 4 - 30, 187);
                textFieldH.setBounds((this.getSize().width - this.getSize().height) / 4, 165, 160, 28);
                int h = p.com.getHeight();
                String hh = Integer.toString(h);
                textFieldH.setText(hh);

                textFieldX.setBackground(Color.GRAY);
                textFieldY.setBackground(Color.GRAY);
                textFieldW.setBackground(Color.GRAY);
                textFieldH.setBackground(Color.GRAY);
                textFieldX.setEnabled(false);
                textFieldY.setEnabled(false);
                textFieldW.setEnabled(false);
                textFieldH.setEnabled(false);
                this.add(textFieldX);
                this.add(textFieldY);
                this.add(textFieldW);
                this.add(textFieldH);
            }

            g.drawString("正方形", (this.getSize().width - this.getSize().height) / 4, 250);
            if (addcheck_1) {
                Unit1 unit1 = new Unit1(p);
                unit1.setBounds((this.getSize().width - this.getSize().height) / 2, 220, p.unit - 1, p.unit - 1);
                this.add(unit1);
                addcheck_1 = !addcheck_1;
            }

            g.drawString("長方形1", (this.getSize().width - this.getSize().height) / 4, 320);
            if (addcheck_2) {
                Unit2 unit2 = new Unit2(p);
                unit2.setBounds((this.getSize().width - this.getSize().height) / 2, 290, p.unit - 1,
                        (p.unit - 1) * 2 + 1);
                this.add(unit2);
                addcheck_2 = !addcheck_2;
            }

            g.drawString("長方形2", (this.getSize().width - this.getSize().height) / 4, 420);
            if (addcheck_3) {
                Unit3 unit3 = new Unit3(p);
                unit3.setBounds((this.getSize().width - this.getSize().height) / 2, 390, (p.unit - 1) * 2 + 1,
                        p.unit - 1);
                this.add(unit3);
                addcheck_3 = !addcheck_3;
            }

            this.setBackground(new Color(115, 255, 129));
        } else if (p.status == Status.InActived) {
            g.setColor(Color.black);
            g.setFont(new Font("標楷體", Font.BOLD, 21));
            g.drawString("點擊頁面 > 編輯即可開始編輯", 25, 20);
        }
    }

    public class MyKey extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if ((e.getKeyCode() == 10)) {  //enter = 10
                p.com.setBounds(((int) (Integer.parseInt(textFieldX.getText()) / p.unit)) * p.unit,
                                ((int) (Integer.parseInt(textFieldY.getText()) / p.unit)) * p.unit,
                                ((int) (Integer.parseInt(textFieldW.getText()) / p.unit)) * p.unit,
                                ((int) (Integer.parseInt(textFieldH.getText()) / p.unit)) * p.unit);
                p.repaint();
            }
        }
    }
}