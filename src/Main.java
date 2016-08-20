/*
 * Project    : Battleground
 * Class      : Main.java
 * Developers : Batuhan Erden & Emir Arditi
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {

	protected static JFrame frame;
	protected static JPanel panel;
	protected static JLabel text;
	protected static Character FirstChosen, SecondChosen;
	protected static Character[] characters = { FirstChosen, SecondChosen };
	private int ChosenCounter = 0;
	protected static Counter counter;
	protected static boolean isCounter;

	public static void main(String[] args) throws Exception {
		new Main();
	}
	
	public Main() throws Exception {
		isCounter = true; // start Counter().
		frame = new JFrame();
		frame.setSize(999, 650);
		FirstChosen = null;
		SecondChosen = null;
		Font font = new Font("Arial", 8, 35);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		panel = new JPanel();
		text = new JLabel("Please choose your Hero:");
		text.setFont(font);
		panel.setLayout(new GridLayout(2, 3));
		JButton button1 = new JButton(new ImageIcon("img/Engineer1.png"));
		button1.setName("Engineer");
		JButton button2 = new JButton(new ImageIcon("img/Dino1.png"));
		button2.setName("Dino");
		JButton button3 = new JButton(new ImageIcon("img/BigBrother1.png"));
		button3.setName("BigBrother");
		JButton button4 = new JButton(new ImageIcon("img/Skeletor1.png"));
		button4.setName("Skeletor");
		JButton button5 = new JButton(new ImageIcon("img/AI1.png"));
		button5.setName("AI");
		JButton button6 = new JButton(new ImageIcon("img/TheThing1.png"));
		button6.setName("TheThing");
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);
		panel.add(button6);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(text, BorderLayout.NORTH);
		button1.addMouseListener(new CharacterSelecter());
		button2.addMouseListener(new CharacterSelecter());
		button3.addMouseListener(new CharacterSelecter());
		button4.addMouseListener(new CharacterSelecter());
		button5.addMouseListener(new CharacterSelecter());
		button6.addMouseListener(new CharacterSelecter());
		frame.setResizable(false);
		frame.setVisible(true);
	}

	class CharacterSelecter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton button = (JButton) e.getSource();
				if (button.getName().equals("Engineer"))
					characters[ChosenCounter] = new Engineer();
				if (button.getName().equals("Dino"))
					characters[ChosenCounter] = new Dino();
				if (button.getName().equals("BigBrother"))
					characters[ChosenCounter] = new BigBrother();
				if (button.getName().equals("Skeletor"))
					characters[ChosenCounter] = new Skeletor();
				if (button.getName().equals("AI"))
					characters[ChosenCounter] = new ArtificialIntelligence();
				if (button.getName().equals("TheThing"))
					characters[ChosenCounter] = new TheThing();
			}
			
			if (ChosenCounter == 1) {
				text.setText("Your Hero: " + characters[0].getChracterName() + ", Your Opponent: " + characters[1].getChracterName());
				frame.remove(panel);
				frame.repaint();
				
				counter = new Counter();
				counter.start();
			}
			
			if (ChosenCounter == 0)
				text.setText("Your Hero: " + characters[ChosenCounter].getChracterName() + ", Please choose the Opponent:");
			
			ChosenCounter++;
		}
	}
}