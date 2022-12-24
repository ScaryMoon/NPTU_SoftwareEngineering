package tw.edu.nptu.G03;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

//@SuppressWarnings("serial")
public abstract class UnitCom extends JPanel {
    Page panel;
    static Status activeCom;
    int r1, g1, b1;
    int offset = 3;

    int originY1, originX1;
    int originY1button, originY2button, originX1button, originX2button;
    int originX, originY;

    boolean isDragged = false;  // 檢查是否只對元件按一下
    boolean arrcheck;           // 判斷是否只是在地圖內搬移元件
    boolean isFirst;            // 檢查第一次從元件清單拖曳元件時add unit

    UnitCom(Page p) {
        super();

        panel = p;
        r1 = p.r1;
        g1 = p.g1;
        b1 = p.b1;

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                p.unitP.z = p.getMousePosition().x;
                p.unitP.z1 = p.getMousePosition().y;
                if (p.unitP.z > MainPanel.h) {
                    p.unitP.z = 800;
                    p.unitP.z1 = 800;
                }
                panel.setFocusable(true);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Graphics g = p.getGraphics();
                if ((p.getMousePosition() != null) && (p.getMousePosition().x < MainPanel.h) && (p.getMousePosition().y < MainPanel.h)) {
                	isDragged = true;
            	    if ((p.getMousePosition().x < MainPanel.h - p.com.getWidth() + p.unit - 1)) {
                		p.unitP.x = p.getMousePosition().x;
                	} else {
                		p.unitP.x += MainPanel.h - p.unitP.x - p.com.getWidth();
                	}
                	if ((p.getMousePosition().y < MainPanel.h - p.com.getHeight() + p.unit - 1)) {
                		p.unitP.y = p.getMousePosition().y;
                	} else {
                		p.unitP.y += MainPanel.h - p.unitP.y - p.com.getHeight();
                	}

                    if (((int) p.unitP.x / p.unit) * p.unit == p.unitP.z
                            && ((int) p.unitP.y / p.unit) * p.unit == p.unitP.z1
                            && (p.getComponentAt(p.unitP.x, p.unitP.y).getClass() == p.getClass())) {
                        p.remove(p.getComponentAt(p.unitP.x, p.unitP.y));
                        g.setColor(new Color(p.r1, p.g1, p.b1));
                        g.fillRect(((int) p.unitP.x / p.unit) * p.unit + 1, ((int) p.unitP.y / p.unit) * p.unit + 1,
                                   getWidth(), getHeight());
                    }
                    if ((((int) p.unitP.x / p.unit) * p.unit != p.unitP.z
                            || ((int) p.unitP.y / p.unit) * p.unit != p.unitP.z1)
                            && (p.getComponentAt(p.unitP.x, p.unitP.y).getClass() == p.getClass())) {
                        g.setColor(new Color(p.r1, p.g1, p.b1));
                        g.fillRect(((int) p.unitP.x / p.unit) * p.unit + 1, ((int) p.unitP.y / p.unit) * p.unit + 1,
                                   getWidth(), getHeight());
                        for (int i = 0 ; i < MainPanel.h / p.unit ; i++) {  //背景是什麼顏色就塗回去
                            for (int j = 0 ; j < MainPanel.h / p.unit ; j++) {
                                g.setColor(p.getBackground());
                                g.fillRect(i * p.unit + 1, j * p.unit + 1, p.unit - 1, p.unit - 1);
                            }
                        }
                        for (int i = 0 ; i <= MainPanel.h / p.unit ; i++) {
                            g.setColor(Color.black);
                            g.drawLine(i * p.unit, 0, i * p.unit, MainPanel.h);
                            g.drawLine(0, i * p.unit, MainPanel.h, i * p.unit);
                        }
                    }
                    p.unitP.z = ((int) p.unitP.x / p.unit) * p.unit;
                    p.unitP.z1 = ((int) p.unitP.y / p.unit) * p.unit;

                    CPsetvisiable(false); 
                    p.units.forEach(n -> {  // 元件一直刷新
                        n.repaint();
                    });
                    p.repaint(0, 0, MainPanel.h, MainPanel.h);
                    p.validate();
                    UnitCom.this.repaint();
                    UnitCom.this.validate();
                }
            }
        });
        activeCom = Status.unSelected;
    }

    public void drawOutLine(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(getX() - 1, getY() - 1, getWidth() + 1, getHeight() + 1);

        CPsetvisiable(true);
        panel.N.setBounds(((getLocation().x + getLocation().x + getWidth())) / 2 - offset, getLocation().y - offset,
                          offset * 2, offset * 2);
        panel.E.setBounds((getLocation().x + getWidth()) - offset, (getLocation().y * 2 + getHeight()) / 2 - offset,
                          offset * 2, offset * 2);
        panel.S.setBounds(((getLocation().x + getLocation().x + getWidth())) / 2 - offset,
                          getLocation().y + getHeight() - offset, offset * 2, offset * 2);
        panel.W.setBounds((getLocation().x) - offset, (getLocation().y * 2 + getHeight()) / 2 - offset, offset * 2, offset * 2);
        panel.NW.setBounds((getLocation().x - offset), getLocation().y - offset, offset * 2, offset * 2);
        panel.NE.setBounds((getLocation().x + getWidth() - offset), getLocation().y - offset, offset * 2, offset * 2);
        panel.SW.setBounds((getLocation().x - offset), getLocation().y + getHeight() - offset, offset * 2, offset * 2);
        panel.SE.setBounds((getLocation().x + getWidth() - offset), getLocation().y + getHeight() - offset, offset * 2, offset * 2);
    }

    public void clearSelect(Graphics g, int x, int y, int w, int h) {
        CPsetvisiable(false);
        panel.repaint();
        panel.validate();
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                g.setColor(panel.getBackground());
                g.drawRect(x - 1, y - 1, w + 1, h + 1);
                g.setColor(Color.black);
                for (int i = 0 ; i <= MainPanel.h / panel.unit ; i++) {  //20x20=700px
                    g.drawLine(i * panel.unit, 0, i * panel.unit, MainPanel.h);
                    g.drawLine(0, i * panel.unit, MainPanel.h, i * panel.unit);
                }
            }
        });
    }

    public void CPsetvisiable(boolean a) {
        panel.E.setVisible(a);
        panel.W.setVisible(a);
        panel.S.setVisible(a);
        panel.N.setVisible(a);
        panel.SE.setVisible(a);
        panel.SW.setVisible(a);
        panel.NE.setVisible(a);
        panel.NW.setVisible(a);
    }
}