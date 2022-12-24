package tw.edu.nptu.G03;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.JPanel;

//@SuppressWarnings("serial")
public class Page extends JPanel {
    int r = 250, b = 250, g = 250;
    final int unit = 35;
    Page panel = this;
    StatusPanel statusP;
    UnitPanel unitP;
    Frame frame;
    Status status = Status.InActived;
    UnitCom com = null;
    int r1, g1, b1;
    boolean addcheck = true;
    boolean issaved = false;  // 判斷地圖有無存檔(若存完檔後地圖有更動為false)
    int[][] mapArray = new int[20][20];  // 儲存地圖資料
    Vector<UnitCom> units = new Vector<UnitCom>();

    JPanel E = new JPanel(), W = new JPanel(), S = new JPanel(), N = new JPanel(),
      	   NE = new JPanel(), NW = new JPanel(), SE = new JPanel(), SW = new JPanel();
    Cursor E_Cursor, W_Cursor, S_Cursor, N_Cursor, NE_Cursor, NW_Cursor, SE_Cursor, SW_Cursor, default_Cursor;

    Page(Frame f) {
        super();
        frame = f;
        this.setPreferredSize(new Dimension(MainPanel.w, MainPanel.h + 1));
        Initcolor(96, 150, 186);
        this.setBackground(new Color(r, g, b));
        this.setLayout(null);

        default_Cursor = new Cursor(Cursor.DEFAULT_CURSOR);
        E_Cursor = new Cursor(Cursor.E_RESIZE_CURSOR);
        W_Cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
        S_Cursor = new Cursor(Cursor.S_RESIZE_CURSOR);
        N_Cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
        SE_Cursor = new Cursor(Cursor.SE_RESIZE_CURSOR);
        SW_Cursor = new Cursor(Cursor.SW_RESIZE_CURSOR);
        NE_Cursor = new Cursor(Cursor.NE_RESIZE_CURSOR);
        NW_Cursor = new Cursor(Cursor.NW_RESIZE_CURSOR);

        panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	if (UnitCom.activeCom == Status.Selected) {
                	UnitCom.activeCom = Status.unSelected;
                    com.CPsetvisiable(false);
                    panel.repaint();
                    panel.validate();
            	}
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0 ; i <= MainPanel.h / unit ; i++) {
            g.drawLine(i * unit, 0, i * unit, MainPanel.h);
            g.drawLine(0, i * unit, MainPanel.h, i * unit);
        }
        if (addcheck) {
            statusP = new StatusPanel(panel);
            this.add(statusP);
            unitP = new UnitPanel(panel);
            this.add(unitP);
            addcheck = !addcheck;
        }
        E.setBackground(Color.red);
        this.add(E);
        W.setBackground(Color.red);
        this.add(W);
        S.setBackground(Color.red);
        this.add(S);
        N.setBackground(Color.red);
        this.add(N);
        SW.setBackground(Color.red);
        this.add(SE);
        NW.setBackground(Color.red);
        this.add(SW);
        NE.setBackground(Color.red);
        this.add(NE);
        SE.setBackground(Color.red);
        this.add(NW);
        panel.E.setVisible(false);
        panel.W.setVisible(false);
        panel.S.setVisible(false);
        panel.N.setVisible(false);
        panel.SE.setVisible(false);
        panel.SW.setVisible(false);
        panel.NE.setVisible(false);
        panel.NW.setVisible(false);
        new ControlPoint(this);
        //System.out.println("Count: " + this.getComponentCount());
    }

    public void Initcolor(int r2, int g2, int b2) {
        r1 = r2;
        g1 = g2;
        b1 = b2;
    }

    public void repaint(int x, int y, int width, int height) {}
}