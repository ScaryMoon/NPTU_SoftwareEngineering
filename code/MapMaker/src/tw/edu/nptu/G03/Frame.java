package tw.edu.nptu.G03;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame {
    Toolbar topbar;
    MainPanel mainp;
    Page activePage = null;
    MainPanel activePage2 = null;
    Vector<Page> pages;

    Frame() {
        topbar = new Toolbar(this);
        this.add(topbar);

        mainp = new MainPanel(this);
        this.add(mainp);

        activePage2 = mainp;
        pages = new Vector<Page>();

        this.setTitle("MapMaker");
    	this.addKeyListener(new MyKeyAdapter());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
        	if (activePage != null) {
                if (activePage.units != null) {
                    if ((e.getKeyCode() == 127) && (UnitCom.activeCom == Status.Selected)) {
                        int index = activePage.units.indexOf(activePage.com);
                        activePage.units.remove(index);
                        activePage.remove(activePage.com);
                        activePage.repaint();
                        activePage.validate();
                    }
                    if (e.getKeyChar() == 'a') {
                        System.out.println(activePage.getComponentAt(getMousePosition()));
                    }
                }
        	}
        }
    }
}