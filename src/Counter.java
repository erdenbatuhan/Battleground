/*
 * Project    : Battleground
 * Class      : Counter.java
 * Developers : Batuhan Erden & Emir Arditi
 */

import java.awt.Font;
import javax.swing.JLabel;

public class Counter extends Thread {

	protected static JLabel label;

	public Counter() {
		Font font = new Font("Arial", 8, 50);
		label = new JLabel();
		label.setFont(font);
		label.setHorizontalAlignment(JLabel.CENTER);
	}

	public void run() {
		if (Main.isCounter == true) {
			Main.frame.add(label);
			Main.frame.repaint();
			Main.frame.revalidate();
			
			for (int i = 5; i > 0; i--) {
				label.setText("The Match Begins in: " + i);
				Main.frame.add(label);
				Main.frame.repaint();
				try {
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			BattlePhase phase = new BattlePhase();
			Main.frame.remove(label);
			Main.frame.remove(Main.text);
			Main.frame.repaint();
			Main.frame.add(phase);
			Main.frame.revalidate();
		}
	}
}