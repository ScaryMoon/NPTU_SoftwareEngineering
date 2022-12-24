package tw.edu.nptu.G03;

import java.awt.Color;
// import java.awt.*;
import java.awt.event.*;

//@SuppressWarnings("serial")
public class Unit1 extends UnitCom {
    Unit1 unit1 = this;

    Unit1(Page p) {
        super(p);
        this.setBackground(new Color(r1, g1, b1));
        this.setBounds((this.getSize().width - this.getSize().height) / 2, 220, p.unit, p.unit);

        this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
                if ((p.getMousePosition() != null) && (p.getMousePosition().x < MainPanel.h) && (p.getMousePosition().y < MainPanel.h)) {
                	if (isDragged == true) {
                        if (p.getComponentAt(p.unitP.x, p.unitP.y).getClass() == p.getClass()) {
                        	setLocation(((int) p.unitP.x / p.unit) * p.unit + 1, ((int) p.unitP.y / p.unit) * p.unit + 1);
                            p.add(unit1);
                            unit1.setBackground(new Color(p.r1, p.g1, p.b1));
                            if (isFirst) {
                            	p.units.add(unit1);
                            }
                            p.repaint();
                            p.validate();
                        }
                        if (arrcheck) {
                	       UnitPanel.addcheck_1 = false;
                	    } else {
                           UnitPanel.addcheck_1 = true;
                        }
                        isDragged = !isDragged;
                        p.issaved = false;
                	} else {
                		activeCom = Status.Selected;
                        unit1.drawOutLine(p.getGraphics());
                	}
                } else {
                	if (p.unitP.x != -1) {
                        if (p.getComponentAt(p.unitP.x, p.unitP.y).getClass() == p.getClass()) {
                            setLocation(((int) p.unitP.x / p.unit) * p.unit + 1, ((int) p.unitP.y / p.unit) * p.unit + 1);
                            p.add(unit1);
                            unit1.setBackground(new Color(p.r1, p.g1, p.b1));
                            if (isFirst) {
                            	p.units.add(unit1);
                            }
                            p.repaint();
                            p.validate();
                            if (arrcheck) {
                     	        UnitPanel.addcheck_1 = false;
                     	    } else {
                                UnitPanel.addcheck_1 = true;
                            }
                            isDragged = !isDragged;
                            p.issaved = false;
                        }
                	}
                }
            }

			public void mousePressed(MouseEvent e) {
                if (((((((int) p.getMousePosition().x / p.unit) * p.unit + 1) + p.unit - 1) / p.unit) - 1) > 19) {
                	p.unitP.x = -1;
                	isFirst = true;   //isFirst: 判斷是否初始拖曳元件(拖曳地圖元件區元件)，是:p.units新增元件 否:p.units不新增元件
                	arrcheck = false;
                } else {
                	isFirst = false;
                	arrcheck = true;  //arrcheck: 判斷是否只是在地圖內搬移元件，是:地圖元件區不新增元件 否:地圖元件區新增元件
                    activeCom = Status.unSelected;
                    p.com.originY1 = (p.com.getY() + p.com.getHeight() - p.unit) + 2;
                    p.com.originY1button = (p.com.originY1 + p.unit) - 2;  // 底下的y點記下
                    p.com.originY2button = p.com.getY();
                    p.com.originX = p.com.getX() - 1;
                    p.com.originX1 = p.com.getX() + p.com.getWidth() - p.unit + 2;
                    p.com.originX1button = (p.com.originX1 + p.unit) - 2;
                    p.com.originX2button = p.com.getX();
                    p.com.originY = p.com.getY() - 1;
                }
                p.com = unit1;
                p.repaint();
                p.validate();
            }
        });
    }
}