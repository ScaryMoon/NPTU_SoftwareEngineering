package tw.edu.nptu.G03_1;

import javax.swing.JFrame;

public class Frame extends JFrame {
	public Panel p = new Panel();
	// public testing p = new testing();

	Frame() {
		this.setTitle("G03_Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(p.getSize());
		System.out.println(this.getSize());
		this.setResizable(false);
		this.add(p);
		this.pack();
		this.setLocationRelativeTo(null);
		p.setLayout(null);
		this.setLayout(null);
		this.setVisible(true);
	}
}
