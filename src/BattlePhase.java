/*
 * Project    : Battleground
 * Class      : BattlePhase.java
 * Developers : Batuhan Erden & Emir Arditi
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class BattlePhase extends JPanel {

	protected static final double OPPONENT_EXTRA_POWER = 1.35; // +35% Health Power
	protected static Character myHero, Opponent;
	protected static int myHero_MAXHEALTH, Opponent_MAXHEALTH;
	protected static JLabel Cooldown1, Cooldown2, Cooldown3, mid, critheal;
	protected static JLabel shield1, shield2, critical1, critical2, crithit;
	protected static JLabel currentAttack = new JLabel();
	protected static boolean isOpponent = false;
	protected static MyHealth myhealth;
	protected static OpponentHealth opponenthealth;
	protected static Battle battle;
	protected static hitAnimation animation;
	protected static Victory victory;
	protected static boolean STOP_THREADS;

	public BattlePhase() {
		Main.isCounter = false; // stop Counter().
		STOP_THREADS = false; // run BattlePhase()'s threads.
		myHero = Main.characters[0]; // YOU.
		Opponent = Main.characters[1]; // OPPONENT.
		myHero_MAXHEALTH = myHero.getHealth();
		Opponent.setHealth((int) (Opponent.getHealth() * OPPONENT_EXTRA_POWER));
		Opponent_MAXHEALTH = Opponent.getHealth();
		// ============================================================ MY HER0
		setLayout(null); // Free
		JButton button1 = new JButton(new ImageIcon(myHero.getQIcon())); // Q
		button1.setBounds(24, 384, 75, 75);
		button1.addMouseListener(new AttackModifier(0));
		JButton button2 = new JButton(new ImageIcon(myHero.getWIcon())); // W
		button2.setBounds(24, 462, 75, 75);
		button2.addMouseListener(new AttackModifier(1));
		JButton button3 = new JButton(new ImageIcon(myHero.getEIcon())); // E
		button3.setBounds(24, 540, 75, 75);
		button3.addMouseListener(new AttackModifier(2));
		JButton button4 = new JButton(new ImageIcon(myHero.getRIcon())); // R
		button4.setBounds(118, 412, 175, 175);
		button4.addMouseListener(new AttackModifier(3));
		button1.setToolTipText("Name: " + myHero.getQName() + ", Damage: " + myHero.getQDamage() + ", Miss Chance: 13%"); // DESCRIPTION
		button2.setToolTipText("Name: " + myHero.getWName() + ", Damage: " + myHero.getWDamage() + ", Miss Chance: 17%"); // DESCRIPTION
		button3.setToolTipText("Name: " + myHero.getEName() + ", Damage: " + myHero.getEDamage() + ", Miss Chance: 29%"); // DESCRIPTION
		
		if (myHero instanceof ArtificialIntelligence) // for Heal Spell
			button4.setToolTipText("Name: " + myHero.getRName() + ", HP: +" + myHero.getRDamage() + ", Miss Chance: 40%"); // DESCRIPTION
		else
			button4.setToolTipText("Name: " + myHero.getRName() + ", Damage: " + myHero.getRDamage() + ", Miss Chance: 20%"); // DESCRIPTION
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		// =========================================================== OPPONENT
		JLabel label1 = new JLabel(new ImageIcon(Opponent.getQIcon())); // Q
		label1.setBounds(900, 384, 75, 75);
		JLabel label2 = new JLabel(new ImageIcon(Opponent.getWIcon())); // W
		label2.setBounds(900, 462, 75, 75);
		JLabel label3 = new JLabel(new ImageIcon(Opponent.getEIcon())); // E
		label3.setBounds(900, 540, 75, 75);
		JLabel label4 = new JLabel(new ImageIcon(Opponent.getRIcon())); // R
		label4.setBounds(706, 412, 175, 175);
		label1.setToolTipText("Name: " + Opponent.getQName() + ", Damage: " + Opponent.getQDamage() + ", Miss Chance: 13%"); // DESCRIPTION
		label2.setToolTipText("Name: " + Opponent.getWName() + ", Damage: " + Opponent.getWDamage() + ", Miss Chance: 17%"); // DESCRIPTION
		label3.setToolTipText("Name: " + Opponent.getEName() + ", Damage: " + Opponent.getEDamage() + ", Miss Chance: 29%"); // DESCRIPTION
		
		if (Opponent instanceof ArtificialIntelligence) // for Heal Spell
			label4.setToolTipText("Name: " + Opponent.getRName() + ", HP: +" + Opponent.getRDamage() + ", Miss Chance: 40%"); // DESCRIPTION
		else
			label4.setToolTipText("Name: " + Opponent.getRName() + ", Damage: " + Opponent.getRDamage() + ", Miss Chance: 20%"); // DESCRIPTION
		
		add(label1);
		add(label2);
		add(label3);
		add(label4);
		// ========================================================== COOLDOWNS
		Cooldown1 = new JLabel(new ImageIcon("img/FALSE.png"));
		Cooldown1.setBounds(119, 591, 15, 15);
		Cooldown2 = new JLabel(new ImageIcon("img/FALSE.png"));
		Cooldown2.setBounds(139, 591, 15, 15);
		Cooldown3 = new JLabel(new ImageIcon("img/FALSE.png"));
		Cooldown3.setBounds(159, 591, 15, 15);
		
		add(Cooldown1);
		add(Cooldown2);
		add(Cooldown3);
		// ============================================================ ADD-ONS
		mid = new JLabel(new ImageIcon("img/Skill Circle.png"));
		mid.setBounds(350, 175, 300, 300);
		shield1 = new JLabel(new ImageIcon("img/Empty.png"));
		shield1.setBounds(250, 85, 50, 50);
		shield2 = new JLabel(new ImageIcon("img/Empty.png"));
		shield2.setBounds(700, 85, 50, 50);
		critical1 = new JLabel(new ImageIcon("img/Empty.png"));
		critical1.setBounds(20, 85, 50, 50);
		critical2 = new JLabel(new ImageIcon("img/Empty.png"));
		critical2.setBounds(930, 85, 50, 50);
		crithit = new JLabel(new ImageIcon("img/Empty.png"));
		crithit.setBounds(715, 149, 250, 153);
		critheal = new JLabel(new ImageIcon("img/Empty.png"));
		critheal.setBounds(715, 149, 150, 150);
		Font font = new Font("Harrington", 8, 25); // New Font
		currentAttack.setBounds(310, -25, 380, 200);
		currentAttack.setHorizontalAlignment(SwingConstants.CENTER);
		currentAttack.setFont(font);
		
		add(mid);
		add(shield1);
		add(shield2);
		add(critical1);
		add(critical2);
		add(crithit);
		add(critheal);
		add(currentAttack);
		// ============================================================== BUFFS
		JLabel myHerotp = new JLabel(new ImageIcon("img/Teleport.png"));
		myHerotp.setBounds(10, 55, 15, 15);
		myHerotp.setToolTipText("Just teleported from Heroes' Cave.");
		add(myHerotp);
		JLabel Opponentp = new JLabel(new ImageIcon("img/Teleport.png"));
		Opponentp.setBounds(974, 55, 15, 15);
		Opponentp.setToolTipText("Just teleported from Heroes' Cave.");
		add(Opponentp);
		JLabel myHerobuff = new JLabel(new ImageIcon("img/CriticalChance.png"));
		myHerobuff.setBounds(30, 55, 15, 15);
		myHerobuff.setToolTipText("Critical Buff: +5% Critical Chance.");
		add(myHerobuff);
		JLabel Opponentbuff = new JLabel(new ImageIcon("img/CriticalChance.png"));
		Opponentbuff.setBounds(954, 55, 15, 15);
		Opponentbuff.setToolTipText("Critical Buff: +5% Critical Chance.");
		add(Opponentbuff);
		JLabel OppoHP = new JLabel(new ImageIcon("img/HPbuff.png"));
		OppoHP.setBounds(934, 55, 15, 15);
		OppoHP.setToolTipText("HP Buff: +" + (int) (100 * (OPPONENT_EXTRA_POWER - 1)) + "% Health Bonus.");
		add(OppoHP);
		// ============================================================ THREADS
		myhealth = new MyHealth();
		myhealth.start();
		opponenthealth = new OpponentHealth();
		opponenthealth.start();
		battle = new Battle();
		battle.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage img = null;
		img = Opponent.getEnvironment();
		g.drawImage(img, 0, 0, 999, 650, null); // Battle Environment
		img = myHero.getCharacterPortrait();
		g.drawImage(img, 310, 75, -300, 300, null); // {YOU} Hero Itself
		img = Opponent.getCharacterPortrait();
		g.drawImage(img, 690, 75, 300, 300, null); // {OPPONENT} Hero Itself
	}

	class MyHealth extends Thread {
		JLabel healthlabel;
		JLabel label;
		JProgressBar progress;
		int initialHealth;
		int life;

		MyHealth() {
			healthlabel = new JLabel(String.valueOf(myHero.getHealth()));
			label = new JLabel(myHero.getChracterName() + "'s HP: " + String.valueOf(myHero.getHealth()) + "/" + myHero_MAXHEALTH + "  (YOU)");
			initialHealth = myHero.getHealth();
			label.setBounds(10, -55, 300, 150);
			progress = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
			progress.setBounds(10, 30, 300, 20);
			progress.setValue(100);
			progress.setOpaque(true);
			progress.setBackground(Color.RED);
			progress.setStringPainted(true);
			add(label);
			add(progress);
		}

		public void run() {
			while (STOP_THREADS == false) { // To Stop Threads
				life = Integer.valueOf(healthlabel.getText());
				
				while (myHero.getHealth() < life && life > 0) {
					life--;
					healthlabel.setText(String.valueOf(life));
					label.setText(myHero.getChracterName() + "'s HP: " + String.valueOf(life) + "/" + myHero_MAXHEALTH + "  (YOU)");
					progress.setValue(100 * life / initialHealth);
					
					try {
						sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				while (myHero.getHealth() > life && life > 0 && life < myHero_MAXHEALTH) { // For Heal Skill
					life++;
					healthlabel.setText(String.valueOf(life));
					label.setText(myHero.getChracterName() + "'s HP: " + String.valueOf(life) + "/" + myHero_MAXHEALTH + "  (YOU)");
					progress.setValue(100 * life / initialHealth);
					
					try {
						sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

	class OpponentHealth extends Thread {
		JLabel healthlabel;
		JLabel label;
		JProgressBar progress;
		int initialHealth;
		int life;

		OpponentHealth() {
			healthlabel = new JLabel(String.valueOf(Opponent.getHealth()));
			label = new JLabel(Opponent.getChracterName() + "'s HP: " + String.valueOf(Opponent.getHealth()) + "/" + Opponent_MAXHEALTH + "  (OPPONENT)");
			initialHealth = Opponent.getHealth();
			label.setBounds(690, -55, 300, 150);
			progress = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
			progress.setBounds(690, 30, 300, 20);
			progress.setValue(100);
			progress.setOpaque(true);
			progress.setBackground(Color.RED);
			progress.setStringPainted(true);
			add(healthlabel);
			add(label);
			add(progress);
		}

		public void run() {
			while (STOP_THREADS == false) { // To Stop Threads
				life = Integer.valueOf(healthlabel.getText());
				
				while (Opponent.getHealth() < life && life > 0) {
					life--;
					healthlabel.setText(String.valueOf(life));
					label.setText(Opponent.getChracterName() + "'s HP: " + String.valueOf(life) + "/" + Opponent_MAXHEALTH + "  (OPPONENT)");
					progress.setValue((100 * life) / initialHealth);
					
					try {
						sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				while (Opponent.getHealth() > life && life > 0 && life < Opponent_MAXHEALTH) { // For Heal Skill
					life++;
					healthlabel.setText(String.valueOf(life));
					label.setText(Opponent.getChracterName() + "'s HP: " + String.valueOf(life) + "/" + Opponent_MAXHEALTH + "  (OPPONENT)");
					progress.setValue(100 * life / initialHealth);
					
					try {
						sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	class AttackModifier extends MouseAdapter {

		int skill;

		AttackModifier(int skill) {
			this.skill = skill;
		}

		public void mousePressed(MouseEvent e) {
			if (isOpponent == false) {
				if (skill == 0)
					myHero.Q();
				if (skill == 1)
					myHero.W();
				if (skill == 2)
					myHero.E();
				if (skill == 3)
					myHero.R();

			}
		}
	}

	class Battle extends Thread {

		public void run() {
			while (myHero.getHealth() > 0 && Opponent.getHealth() > 0 && STOP_THREADS == false) {
				isOpponent = false;
				mid.setIcon(new ImageIcon("img/Skill Circle.png"));
				// It is your turn...
				currentAttack.setText("It is your turn...");
				
				while (!isOpponent) { // Player's thinking time (Infinite Loop)
					System.out.print("");
					// Just for the proper maintenance of the code.
				}
				
				animation = new hitAnimation(myHero);
				animation.start();
				cooldownGenerator();
				
				if (Opponent.getHealth() > 0) {
					try {
						sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					critheal.setLocation(765, 150); // Critical Heal
					crithit.setLocation(35, 149); // Critical Hit
					critheal.setIcon(new ImageIcon("img/Empty.png"));
					crithit.setIcon(new ImageIcon("img/Empty.png"));
					mid.setIcon(new ImageIcon("img/Skill Circle.png"));
					// It is opponent's turn...
					currentAttack.setText("It is opponent's turn...");
					
					try {
						sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					Opponent.opponentAttack();
					animation = new hitAnimation(Opponent);
					animation.start();
					
					try {
						sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					critheal.setLocation(85, 150); // Critical Heal
					crithit.setLocation(715, 149); // Critical Hit
					critheal.setIcon(new ImageIcon("img/Empty.png"));
					crithit.setIcon(new ImageIcon("img/Empty.png"));
				} else {
					try {
						sleep(2500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					JLabel youwin = new JLabel(new ImageIcon("img/Won.png"));
					youwin.setBounds(250, 315, 50, 50);
					youwin.setToolTipText("Winner Award.");
					add(youwin); // Trophy.
					currentAttack.setText("VICTORY!!"); // Victory!
					victory = new Victory(myHero);
					victory.start();
					repaint();
				}
			}
			
			if (myHero.getHealth() <= 0) { // Defeat!
				try {
					sleep(500); // 2000 + 500 = 2500
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				JLabel opwins = new JLabel(new ImageIcon("img/Won.png"));
				opwins.setBounds(700, 315, 50, 50);
				opwins.setToolTipText("Winner Award.");
				add(opwins); // Trophy.
				currentAttack.setText("DEFEAT!!");
				victory = new Victory(Opponent);
				victory.start();
				repaint();
			}
		}
	}

	public void cooldownGenerator() { // Paints the cooldown boxes
		if (myHero.getCountyCounter() == 1)
			Cooldown1.setIcon(new ImageIcon("img/TRUE.png"));
		if (myHero.getCountyCounter() == 2)
			Cooldown2.setIcon(new ImageIcon("img/TRUE.png"));
		if (myHero.getCountyCounter() == 3)
			Cooldown3.setIcon(new ImageIcon("img/TRUE.png"));
		if (myHero.getCountyCounter() == 0) {
			Cooldown1.setIcon(new ImageIcon("img/FALSE.png"));
			Cooldown2.setIcon(new ImageIcon("img/FALSE.png"));
			Cooldown3.setIcon(new ImageIcon("img/FALSE.png"));
		}
	}

	public void shieldPainter() { // Paints shields
		if (myHero.hasHeroMissed == true) {
			shield2.setIcon(new ImageIcon("img/Shield.png"));
			shield2.setToolTipText("Opponent dodged/blocked your last spell.");
		}
		if (myHero.hasHeroMissed == false) {
			shield2.setIcon(new ImageIcon("img/Empty.png"));
			shield2.setToolTipText("");
		}
		if (Opponent.hasOpponentMissed == true) {
			shield1.setIcon(new ImageIcon("img/Shield.png"));
			shield1.setToolTipText("You dodged/blocked opponent's last spell.");
		}
		if (Opponent.hasOpponentMissed == false) {
			shield1.setIcon(new ImageIcon("img/Empty.png"));
			shield1.setToolTipText("");
		}
	}

	class hitAnimation extends Thread {

		Character Attacker;
		int dx = 350;
		int dy = 175;

		public hitAnimation(Character Attacker) {
			this.Attacker = Attacker;
		}

		public void run() {
			if (STOP_THREADS == false) { // To Stop Threads
				shieldPainter();
				if (Attacker.hasHeroMissed == false && Attacker.hasOpponentMissed == false) {
					if (Attacker instanceof ArtificialIntelligence && Attacker.RUsed == true) {
						for (int j = 0; j < 15; j++) {
							dy -= (j / 2);
							if (Attacker == Opponent)
								dx += j;
							else
								dx -= j;
							
							mid.setLocation(dx, dy);
							
							try {
								sleep(40);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						for (int k = 0; k < 15; k++) {
							dy += (k / 2);
							
							if (Attacker == Opponent)
								dx -= k;
							else
								dx += k;
							mid.setLocation(dx, dy);
							
							try {
								sleep(40);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						Attacker.RUsed = false;
					} else {
						for (int j = 0; j < 15; j++) {
							dy -= (j / 2);
							
							if (Attacker == Opponent)
								dx -= j;
							else
								dx += j;
							
							mid.setLocation(dx, dy);
							
							try {
								sleep(40);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
						for (int k = 0; k < 15; k++) {
							dy += (k / 2);
							
							if (Attacker == Opponent)
								dx += k;
							else
								dx -= k;
							
							mid.setLocation(dx, dy);
							
							try {
								sleep(40);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	class Victory extends Thread {

		Character winner;
		int x = 350;
		int y = 175;

		public Victory(Character winner) {
			this.winner = winner;
		}

		public void run() {
			if (STOP_THREADS == false) { // To Stop Threads
				for (int i = 0; i < 15; i++) {
					y -= i;
					mid.setLocation(x, y);
					
					try {
						sleep(40);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 0; j < 26; j++) {
					if (winner == Opponent)
						x -= j;
					else
						x += j;
					
					mid.setLocation(x, y);
					
					try {
						sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				if (winner == Opponent)
					x -= 15;
				else
					x += 15;
				
				y += 5;
				mid.setLocation(x, y);
				
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				mid.setIcon(new ImageIcon("img/RIP.png")); // RIP !!
				mid.setToolTipText("Dead...");
				JButton newgame = new JButton("New Game");
				newgame.setBounds(400, 487, 200, 100);
				newgame.setFont(new Font("Harrington", 5, 30));
				newgame.setToolTipText("New Game.");
				newgame.addActionListener(new NewGame());
				add(newgame);
				repaint();
			}
		}
	}

	class NewGame implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Main.frame.dispose();
			STOP_THREADS = true; // Makes threads stop.
			try {
				new Main();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}