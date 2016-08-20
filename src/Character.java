/*
 * Project    : Battleground
 * Class      : Character.java
 * Developers : Batuhan Erden & Emir Arditi
 */

import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.ImageIcon;

public abstract class Character {

	private BufferedImage CharacterPortrait, Environment;
	private BufferedImage QIcon, WIcon, EIcon, RIcon;
	private BufferedImage QMidIcon, WMidIcon, EMidIcon, RMidIcon;
	private String CharacterName, QName, WName, EName, RName;
	private int Health, QDamage, WDamage, EDamage, RDamage, OpponentCooldown;
	private int CountyCounter = 0;
	protected boolean hasHeroMissed = false;
	protected boolean hasOpponentMissed = false;
	protected boolean RUsed = false;

	/* ------------------------------------------------ FORMULA ------------------------------------------------
	 * All characters are equal because their Health Powers and Damages Power is adjusted by a MATH Formula..
	 * A formula in which Y is A Constant, U is Damage Power and H is Health Power.
	 * By this formula, a character with lower Health Power has higher Damage Power and visa versa.
	 * The exact formula is ---> Y = U * H
	 * The Constant (Y) for 1st (Q) spell is 2100,
	 * The Constant (Y) for 2nd (W) spell is 2800,
	 * The Constant (Y) for 3rd (E) spell is 4620,
	 * The Constant (Y) for 4th (Ultimate) spell is 7000.
	 * ---------------------------------------------------------------------------------------------------------
	 */

	public Character(String CharacterName, String QName, String WName, String EName, String RName, int Health, int QDamage, int WDamage, int EDamage, int RDamage) {
		this.CharacterName = CharacterName;
		this.QName = QName;
		this.WName = WName;
		this.EName = EName;
		this.RName = RName;
		this.Health = Health;
		this.QDamage = QDamage;
		this.WDamage = WDamage;
		this.EDamage = EDamage;
		this.RDamage = RDamage;
	}

	public void Q() { // Q (Weakest) Spell
		Random random = new Random();
		int r = random.nextInt(100);
		
		if (r <= 12) { // 13% MISS CHANCE
			BattlePhase.currentAttack.setText("You missed...");
			BattlePhase.mid.setIcon(new ImageIcon("img/Missed.png")); // Middle Icon
			hasHeroMissed = true;
			BattlePhase.critical1.setIcon(new ImageIcon("img/Empty.png"));
			BattlePhase.critical1.setToolTipText("");
		} else if (r >= 95) { // 5% CRITICAL HIT CHANCE [DOUBLE DAMAGE]
			BattlePhase.Opponent.setHealth(BattlePhase.Opponent.getHealth() - (getQDamage() * 2));
			BattlePhase.currentAttack.setText("You used: " + BattlePhase.myHero.getQName());
			BattlePhase.mid.setIcon(new ImageIcon(getQMidIcon())); // Middle Icon
			hasHeroMissed = false;
			CountyCounter++;
			BattlePhase.critical1.setIcon(new ImageIcon("img/Critical.png"));
			BattlePhase.critical1.setToolTipText("Your last spell was critical.");
			BattlePhase.crithit.setIcon(new ImageIcon("img/CritHit.png"));
		} else { // NORMAL ATTACK
			BattlePhase.Opponent.setHealth(BattlePhase.Opponent.getHealth() - getQDamage());
			BattlePhase.currentAttack.setText("You used: " + BattlePhase.myHero.getQName());
			BattlePhase.mid.setIcon(new ImageIcon(getQMidIcon())); // Middle Icon
			hasHeroMissed = false;
			CountyCounter++;
			BattlePhase.critical1.setIcon(new ImageIcon("img/Empty.png"));
			BattlePhase.critical1.setToolTipText("");
		}
		
		BattlePhase.isOpponent = true;
	}

	public void W() { // W (Average) Skill
		Random random = new Random();
		int r = random.nextInt(100);
		
		if (r <= 16) { // 17% MISS CHANCE
			BattlePhase.currentAttack.setText("You missed...");
			BattlePhase.mid.setIcon(new ImageIcon("img/Missed.png")); // Middle Icon
			hasHeroMissed = true;
			BattlePhase.critical1.setIcon(new ImageIcon("img/Empty.png"));
			BattlePhase.critical1.setToolTipText("");
		} else if (r >= 95) { // 5% CRITICAL HIT CHANCE [DOUBLE DAMAGE]
			BattlePhase.Opponent.setHealth(BattlePhase.Opponent.getHealth() - (getWDamage() * 2));
			BattlePhase.currentAttack.setText("You used: " + BattlePhase.myHero.getWName());
			BattlePhase.mid.setIcon(new ImageIcon(getWMidIcon())); // Middle Icon
			hasHeroMissed = false;
			CountyCounter++;
			BattlePhase.critical1.setIcon(new ImageIcon("img/Critical.png"));
			BattlePhase.critical1.setToolTipText("Your last spell was critical.");
			BattlePhase.crithit.setIcon(new ImageIcon("img/CritHit.png"));
		} else { // NORMAL ATTACK
			BattlePhase.Opponent.setHealth(BattlePhase.Opponent.getHealth() - getWDamage());
			BattlePhase.currentAttack.setText("You used: " + BattlePhase.myHero.getWName());
			BattlePhase.mid.setIcon(new ImageIcon(getWMidIcon())); // Middle Icon
			hasHeroMissed = false;
			CountyCounter++;
			BattlePhase.critical1.setIcon(new ImageIcon("img/Empty.png"));
			BattlePhase.critical1.setToolTipText("");
		}
		
		BattlePhase.isOpponent = true;
	}

	public void E() { // E (Strongest) Skill
		Random random = new Random();
		int r = random.nextInt(100);
		
		if (r <= 28) { // 29% MISS CHANCE
			BattlePhase.currentAttack.setText("You missed...");
			BattlePhase.mid.setIcon(new ImageIcon("img/Missed.png")); // Middle Icon
			hasHeroMissed = true;
			BattlePhase.critical1.setIcon(new ImageIcon("img/Empty.png"));
			BattlePhase.critical1.setToolTipText("");
		} else if (r >= 95) { // 5% CRITICAL HIT CHANCE [DOUBLE DAMAGE]
			BattlePhase.Opponent.setHealth(BattlePhase.Opponent.getHealth() - (getEDamage() * 2));
			BattlePhase.currentAttack.setText("You used: " + BattlePhase.myHero.getEName());
			BattlePhase.mid.setIcon(new ImageIcon(getEMidIcon())); // Middle Icon
			hasHeroMissed = false;
			CountyCounter++;
			BattlePhase.critical1.setIcon(new ImageIcon("img/Critical.png"));
			BattlePhase.critical1.setToolTipText("Your last spell was critical.");
			BattlePhase.crithit.setIcon(new ImageIcon("img/CritHit.png"));
		} else { // NORMAL ATTACK
			BattlePhase.Opponent.setHealth(BattlePhase.Opponent.getHealth() - getEDamage());
			BattlePhase.currentAttack.setText("You used: " + BattlePhase.myHero.getEName());
			BattlePhase.mid.setIcon(new ImageIcon(getEMidIcon())); // Middle Icon
			hasHeroMissed = false;
			CountyCounter++;
			BattlePhase.critical1.setIcon(new ImageIcon("img/Empty.png"));
			BattlePhase.critical1.setToolTipText("");
		}
		
		BattlePhase.isOpponent = true;
	}

	public void R() { // R (Ultimate) Skill
		if (CountyCounter >= 3) {
			Random random = new Random();
			int r = random.nextInt(100);
			
			if (r <= 19) {// 20% MISS CHANCE
				BattlePhase.currentAttack.setText("You missed...");
				BattlePhase.mid.setIcon(new ImageIcon("img/Missed.png")); // Middle Icon
				hasHeroMissed = true;
				BattlePhase.critical1.setIcon(new ImageIcon("img/Empty.png"));
				BattlePhase.critical1.setToolTipText("");
			} else if (r >= 95) { // 5% CRITICAL HIT CHANCE [DOUBLE DAMAGE]
				BattlePhase.Opponent.setHealth(BattlePhase.Opponent.getHealth() - (getRDamage() * 2));
				BattlePhase.currentAttack.setText("You used: " + BattlePhase.myHero.getRName());
				BattlePhase.mid.setIcon(new ImageIcon(getRMidIcon())); // Middle Icon
				hasHeroMissed = false;
				BattlePhase.critical1.setIcon(new ImageIcon("img/Critical.png"));
				BattlePhase.critical1.setToolTipText("Your last spell was critical.");
				BattlePhase.crithit.setIcon(new ImageIcon("img/CritHit.png"));
			} else { // NORMAL ATTACK
				BattlePhase.Opponent.setHealth(BattlePhase.Opponent.getHealth() - getRDamage());
				BattlePhase.currentAttack.setText("You used: " + BattlePhase.myHero.getRName());
				BattlePhase.mid.setIcon(new ImageIcon(getRMidIcon())); // Middle Icon
				hasHeroMissed = false;
				BattlePhase.critical1.setIcon(new ImageIcon("img/Empty.png"));
				BattlePhase.critical1.setToolTipText("");
			}
			
			CountyCounter = 0;
			BattlePhase.isOpponent = true;
		}
	}

	public void opponentAttack() { // OPPONENT'S INTELLIGENCE
		Random random = new Random();
		
		if (OpponentCooldown == 3) { // OPPONENT USING ITS "R" ULTIMATE SKILL
			Random inner_random = new Random();
			int r = inner_random.nextInt(100);
			
			if (r <= 19) { // 20% MISS CHANCE
				BattlePhase.currentAttack.setText("Opponent missed...");
				BattlePhase.mid.setIcon(new ImageIcon("img/Missed.png")); // Middle Icon
				hasOpponentMissed = true;
				BattlePhase.critical2.setIcon(new ImageIcon("img/Empty.png"));
				BattlePhase.critical2.setToolTipText("");
			} else if (r >= 95) { // 5% CRITICAL HIT CHANCE [DOUBLE DAMAGE]
				BattlePhase.myHero.setHealth(BattlePhase.myHero.getHealth() - (getRDamage() * 2));
				BattlePhase.currentAttack.setText("Opponent used: " + BattlePhase.Opponent.getRName());
				BattlePhase.mid.setIcon(new ImageIcon(BattlePhase.Opponent.getRMidIcon())); // Middle Icon
				hasOpponentMissed = false;
				BattlePhase.critical2.setIcon(new ImageIcon("img/Critical.png"));
				BattlePhase.critical2.setToolTipText("Opponent's last spell was critical.");
				BattlePhase.crithit.setIcon(new ImageIcon("img/CritHit.png"));
			} else { // NORMAL ATTACK
				BattlePhase.myHero.setHealth(BattlePhase.myHero.getHealth() - getRDamage());
				BattlePhase.currentAttack.setText("Opponent used: " + BattlePhase.Opponent.getRName());
				BattlePhase.mid.setIcon(new ImageIcon(BattlePhase.Opponent.getRMidIcon())); // Middle Icon
				hasOpponentMissed = false;
				BattlePhase.critical2.setIcon(new ImageIcon("img/Empty.png"));
				BattlePhase.critical2.setToolTipText("");
			}
			
			OpponentCooldown = 0;
		} else {
			int r = random.nextInt(3);
			
			if (r == 0) { // OPPONENT USING ITS "Q" SKILL
				Random inner_random = new Random();
				int q = inner_random.nextInt(100);
				
				if (q <= 12) { // 13% MISS CHANCE
					hasOpponentMissed = true;
					BattlePhase.currentAttack.setText("Opponent missed...");
					BattlePhase.mid.setIcon(new ImageIcon("img/Missed.png")); // Middle Icon
					hasOpponentMissed = true;
					BattlePhase.critical2.setIcon(new ImageIcon("img/Empty.png"));
					BattlePhase.critical2.setToolTipText("");
				} else if (q >= 95) { // 5% CRITICAL HIT CHANCE [DOUBLE DAMAGE]
					BattlePhase.myHero.setHealth(BattlePhase.myHero.getHealth() - (getQDamage() * 2));
					BattlePhase.currentAttack.setText("Opponent used: " + BattlePhase.Opponent.getQName());
					BattlePhase.mid.setIcon(new ImageIcon(BattlePhase.Opponent.getQMidIcon())); // Middle Icon
					hasOpponentMissed = false;
					OpponentCooldown++;
					BattlePhase.critical2.setIcon(new ImageIcon("img/Critical.png"));
					BattlePhase.critical2.setToolTipText("Opponent's last spell was critical.");
					BattlePhase.crithit.setIcon(new ImageIcon("img/CritHit.png"));
				} else { // NORMAL ATTACK
					BattlePhase.myHero.setHealth(BattlePhase.myHero.getHealth() - getQDamage());
					BattlePhase.currentAttack.setText("Opponent used: " + BattlePhase.Opponent.getQName());
					BattlePhase.mid.setIcon(new ImageIcon(BattlePhase.Opponent.getQMidIcon())); // Middle Icon
					hasOpponentMissed = false;
					OpponentCooldown++;
					BattlePhase.critical2.setIcon(new ImageIcon("img/Empty.png"));
					BattlePhase.critical2.setToolTipText("");
				}
			}
			
			if (r == 1) { // OPPONENT USING ITS "W" SKILL
				Random inner_random = new Random();
				int w = inner_random.nextInt(100);
				
				if (w <= 16) { // 17% MISS CHANCE
					hasOpponentMissed = true;
					BattlePhase.currentAttack.setText("Opponent missed...");
					BattlePhase.mid.setIcon(new ImageIcon("img/Missed.png")); // Middle Icon
					hasOpponentMissed = true;
					BattlePhase.critical2.setIcon(new ImageIcon("img/Empty.png"));
					BattlePhase.critical2.setToolTipText("");
				} else if (w >= 95) { // 5% CRITICAL HIT CHANCE [DOUBLE DAMAGE]
					BattlePhase.myHero.setHealth(BattlePhase.myHero.getHealth() - (getWDamage() * 2));
					BattlePhase.currentAttack.setText("Opponent used: " + BattlePhase.Opponent.getWName());
					BattlePhase.mid.setIcon(new ImageIcon(BattlePhase.Opponent.getWMidIcon())); // Middle Icon
					hasOpponentMissed = false;
					OpponentCooldown++;
					BattlePhase.critical2.setIcon(new ImageIcon("img/Critical.png"));
					BattlePhase.critical2.setToolTipText("Opponent's last spell was critical.");
					BattlePhase.crithit.setIcon(new ImageIcon("img/CritHit.png"));
				} else { // NORMAL ATTACK
					BattlePhase.myHero.setHealth(BattlePhase.myHero.getHealth() - getWDamage());
					BattlePhase.currentAttack.setText("Opponent used: " + BattlePhase.Opponent.getWName());
					BattlePhase.mid.setIcon(new ImageIcon(BattlePhase.Opponent.getWMidIcon())); // Middle Icon
					hasOpponentMissed = false;
					OpponentCooldown++;
					BattlePhase.critical2.setIcon(new ImageIcon("img/Empty.png"));
					BattlePhase.critical2.setToolTipText("");
				}
			}
			
			if (r == 2) { // OPPONENT USING ITS "E" SKILL
				Random inner_random = new Random();
				int e = inner_random.nextInt(100);
				
				if (e <= 28) { // 29% MISS CHANCE
					hasOpponentMissed = true;
					BattlePhase.currentAttack.setText("Opponent missed...");
					BattlePhase.mid.setIcon(new ImageIcon("img/Missed.png")); // Middle Icon
					hasOpponentMissed = true;
					BattlePhase.critical2.setIcon(new ImageIcon("img/Empty.png"));
					BattlePhase.critical2.setToolTipText("");
				} else if (e >= 95) { // 5% CRITICAL HIT CHANCE [DOUBLE DAMAGE]
					BattlePhase.myHero.setHealth(BattlePhase.myHero.getHealth() - (getEDamage() * 2));
					BattlePhase.currentAttack.setText("Opponent used: " + BattlePhase.Opponent.getEName());
					BattlePhase.mid.setIcon(new ImageIcon(BattlePhase.Opponent.getEMidIcon())); // Middle Icon
					hasOpponentMissed = false;
					OpponentCooldown++;
					BattlePhase.critical2.setIcon(new ImageIcon("img/Critical.png"));
					BattlePhase.critical2.setToolTipText("Opponent's last spell was critical.");
					BattlePhase.crithit.setIcon(new ImageIcon("img/CritHit.png"));
				} else { // NORMAL ATTACK
					BattlePhase.myHero.setHealth(BattlePhase.myHero.getHealth() - getEDamage());
					BattlePhase.currentAttack.setText("Opponent used: " + BattlePhase.Opponent.getEName());
					BattlePhase.mid.setIcon(new ImageIcon(BattlePhase.Opponent.getEMidIcon())); // Middle Icon
					hasOpponentMissed = false;
					OpponentCooldown++;
					BattlePhase.critical2.setIcon(new ImageIcon("img/Empty.png"));
					BattlePhase.critical2.setToolTipText("");
				}
			}
		}
	}
	
	/* ----------------------------- GETTERS & SETTERS ----------------------------- */

	public BufferedImage getCharacterPortrait() {
		return CharacterPortrait;
	}

	public BufferedImage getEnvironment() {
		return Environment;
	}

	public String getChracterName() {
		return CharacterName;
	}

	public int getHealth() {
		return Health;
	}

	public BufferedImage getQIcon() {
		return QIcon;
	}

	public BufferedImage getWIcon() {
		return WIcon;
	}

	public BufferedImage getEIcon() {
		return EIcon;
	}

	public BufferedImage getRIcon() {
		return RIcon;
	}

	public BufferedImage getQMidIcon() {
		return QMidIcon;
	}

	public BufferedImage getWMidIcon() {
		return WMidIcon;
	}

	public BufferedImage getEMidIcon() {
		return EMidIcon;
	}

	public BufferedImage getRMidIcon() {
		return RMidIcon;
	}

	public String getQName() {
		return QName;
	}

	public String getWName() {
		return WName;
	}

	public String getEName() {
		return EName;
	}

	public String getRName() {
		return RName;
	}

	public int getQDamage() {
		return QDamage;
	}

	public int getWDamage() {
		return WDamage;
	}

	public int getEDamage() {
		return EDamage;
	}

	public int getRDamage() {
		return RDamage;
	}

	public int getCountyCounter() {
		return CountyCounter;
	}

	public void setCharacterPortrait(BufferedImage CharacterPortrait) {
		this.CharacterPortrait = CharacterPortrait;
	}

	public void setEnvironment(BufferedImage Environment) {
		this.Environment = Environment;
	}

	public void setQIcon(BufferedImage QIcon) {
		this.QIcon = QIcon;
	}

	public void setWIcon(BufferedImage WIcon) {
		this.WIcon = WIcon;
	}

	public void setEIcon(BufferedImage EIcon) {
		this.EIcon = EIcon;
	}

	public void setRIcon(BufferedImage RIcon) {
		this.RIcon = RIcon;
	}

	public void setQMidIcon(BufferedImage qMidIcon) {
		QMidIcon = qMidIcon;
	}

	public void setWMidIcon(BufferedImage wMidIcon) {
		WMidIcon = wMidIcon;
	}

	public void setEMidIcon(BufferedImage eMidIcon) {
		EMidIcon = eMidIcon;
	}

	public void setRMidIcon(BufferedImage rMidIcon) {
		RMidIcon = rMidIcon;
	}

	public void setHealth(int Health) {
		this.Health = Health;
	}

	public void setCountyCounter(int CountyCounter) {
		this.CountyCounter = CountyCounter;
	}
}