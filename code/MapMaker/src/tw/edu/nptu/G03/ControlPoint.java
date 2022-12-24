package tw.edu.nptu.G03;

import java.awt.event.*;
import javax.swing.JPanel;

//@SuppressWarnings("serial")
public class ControlPoint extends JPanel {
    ControlPoint(Page panel) {
        panel.E.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if ((panel.getMousePosition() != null) && (panel.getMousePosition().x < MainPanel.h - 10)) {
                	panel.unitP.x = panel.getMousePosition().x;
                    if (((int) (panel.unitP.x / panel.unit) * panel.unit - panel.com.originX2button + panel.unit) > 0) {
                        panel.unitP.x = panel.getMousePosition().x;
                        panel.com.setBounds(panel.com.originX2button, panel.com.getY(),
                                            (int) (panel.unitP.x / panel.unit) * panel.unit - panel.com.originX2button + panel.unit,
                                            panel.com.getHeight());
                        panel.repaint();
                    }
                }
            }
        });

        panel.E.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                panel.E.setCursor(panel.E_Cursor);
            }

            public void mouseExited(MouseEvent e) {
                panel.E.setCursor(panel.default_Cursor);
            }
        });

        panel.W.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if ((panel.getMousePosition() != null) && (panel.getMousePosition().x > 10)) {
                	panel.unitP.x = panel.getMousePosition().x;
                    if (((panel.com.originX1 - (int) (panel.unitP.x / panel.unit) * panel.unit) - 1) > 0) {
                        panel.com.setBounds((((int) (panel.unitP.x / panel.unit)) * panel.unit) + 1, panel.com.getY(),
            		                        (panel.com.originX1button - (int) (panel.unitP.x / panel.unit) * panel.unit) - 1,
                		                    panel.com.getHeight());
                        panel.repaint();
                    }
                }
            }
        });

        panel.W.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                panel.W.setCursor(panel.W_Cursor);
            }

            public void mouseExited(MouseEvent e) {
                panel.W.setCursor(panel.default_Cursor);
            }
        });

        panel.S.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if ((panel.getMousePosition() != null) && (panel.getMousePosition().y < MainPanel.h - 10)) {
                    panel.unitP.y = panel.getMousePosition().y;
                    if (((int) (panel.unitP.y / panel.unit) * panel.unit - panel.com.originY2button + panel.unit) > 0) {
                        panel.com.setBounds(panel.com.getX(), panel.com.originY2button,
                                panel.com.getWidth(),
                                (int) (panel.unitP.y / panel.unit) * panel.unit - panel.com.originY2button + panel.unit);
                        panel.repaint();
                    }
                }
            }
        });

        panel.S.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                panel.S.setCursor(panel.S_Cursor);
            }

            public void mouseExited(MouseEvent e) {
                panel.S.setCursor(panel.default_Cursor);
            }
        });

        panel.N.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if ((panel.getMousePosition() != null) && (panel.getMousePosition().x > 10)) {
                    panel.unitP.y = panel.getMousePosition().y;
                    if (((panel.com.originY1button - (int) (panel.unitP.y / panel.unit) * panel.unit) - 1) > 0) {
                        panel.com.setBounds(panel.com.getX(), (((int) (panel.unitP.y / panel.unit)) * panel.unit) + 1,
                                panel.com.getWidth(),
                                (panel.com.originY1button - (int) (panel.unitP.y / panel.unit) * panel.unit) - 1);
                        panel.repaint();
                    }
                }
            }
        });

        panel.N.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                panel.N.setCursor(panel.N_Cursor);
            }

            public void mouseExited(MouseEvent e) {
                panel.N.setCursor(panel.default_Cursor);
            }
        });

        // 東南
        panel.SE.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if ((panel.getMousePosition() != null) && (panel.getMousePosition().x < MainPanel.h - 10) && (panel.getMousePosition().y < MainPanel.h - 10)) {
                	panel.unitP.x = panel.getMousePosition().x;
                    panel.unitP.y = panel.getMousePosition().y;
                    if ((((int) (panel.unitP.x / panel.unit) * panel.unit - panel.com.originX2button + panel.unit) > 0) &&
                    	(((int) (panel.unitP.y / panel.unit) * panel.unit - panel.com.originY2button + panel.unit) > 0)) {
                        panel.com.setBounds(panel.com.getX(), panel.com.originY2button,
                		        (int) (panel.unitP.x / panel.unit) * panel.unit - panel.com.originX2button + panel.unit,
                		        (int) (panel.unitP.y / panel.unit) * panel.unit - panel.com.originY2button + panel.unit);
                        panel.repaint();
                    }
                }
            }
        });

        panel.SE.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                panel.SE.setCursor(panel.SE_Cursor);
            }

            public void mouseExited(MouseEvent e) {
                panel.SE.setCursor(panel.default_Cursor);
            }
        });

        // 西南
        panel.SW.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if ((panel.getMousePosition() != null) && (panel.getMousePosition().x > 10) && (panel.getMousePosition().y < MainPanel.h - 10)) {
                	panel.unitP.x = panel.getMousePosition().x;
                    panel.unitP.y = panel.getMousePosition().y;
                    if ((((panel.com.originX1button - (int) (panel.unitP.x / panel.unit) * panel.unit) - 1) > 0) &&
                    	((int) (panel.unitP.y / panel.unit) * panel.unit - panel.com.originY2button + panel.unit) > 0) {
                        panel.com.setBounds((((int) (panel.unitP.x / panel.unit)) * panel.unit) + 1, panel.com.getY(),
    		                                (panel.com.originX1button - (int) (panel.unitP.x / panel.unit) * panel.unit) - 1,
    		                                (int) (panel.unitP.y / panel.unit) * panel.unit - panel.com.originY2button + panel.unit);
                        panel.repaint();
                    }
                }
            }
        });

        panel.SW.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                panel.SW.setCursor(panel.SW_Cursor);
            }

            public void mouseExited(MouseEvent e) {
                panel.SW.setCursor(panel.default_Cursor);
            }
        });

        // 東北
        panel.NE.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if ((panel.getMousePosition() != null) && (panel.getMousePosition().x < MainPanel.h - 10) && (panel.getMousePosition().y > 10)) {
                	panel.unitP.x = panel.getMousePosition().x;
                    panel.unitP.y = panel.getMousePosition().y;
                    if ((((int) (panel.unitP.x / panel.unit) * panel.unit - panel.com.originX2button + panel.unit) > 0) &&
                    	(((panel.com.originY1button - (int) (panel.unitP.y / panel.unit) * panel.unit) - 1) > 0)) {
                            panel.com.setBounds(panel.com.getX(), (((int) (panel.unitP.y / panel.unit)) * panel.unit) + 1,
                    		                    (int) (panel.unitP.x / panel.unit) * panel.unit - panel.com.originX2button + panel.unit,
                  		                        (panel.com.originY1button - (int) (panel.unitP.y / panel.unit) * panel.unit) - 1);
                        panel.repaint();
                    }
                }
            }
        });

        panel.NE.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                panel.NE.setCursor(panel.NE_Cursor);
            }

            public void mouseExited(MouseEvent e) {
                panel.NE.setCursor(panel.default_Cursor);
            }
        });

        //西北
        panel.NW.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if ((panel.getMousePosition() != null) && (panel.getMousePosition().x > 10) && (panel.getMousePosition().y > 10)) {
                	panel.unitP.x = panel.getMousePosition().x;
                    panel.unitP.y = panel.getMousePosition().y;
                    if ((((panel.com.originX1button - (int) (panel.unitP.x / panel.unit) * panel.unit) - 1) > 0) &&
                    	(((panel.com.originY1button - (int) (panel.unitP.y / panel.unit) * panel.unit) - 1) > 0)) {
                        panel.com.setBounds((((int) (panel.unitP.x / panel.unit)) * panel.unit) + 1,
            		                        (((int) (panel.unitP.y / panel.unit)) * panel.unit) + 1,
                		                    (panel.com.originX1button - (int) (panel.unitP.x / panel.unit) * panel.unit) - 1,
                		                    (panel.com.originY1button - (int) (panel.unitP.y / panel.unit) * panel.unit) - 1);
                        panel.repaint();
                    }
                }
            }
        });

        panel.NW.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                panel.NW.setCursor(panel.NW_Cursor);
            }

            public void mouseExited(MouseEvent e) {
                panel.NW.setCursor(panel.default_Cursor);
            }
        });
    }
}