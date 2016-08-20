/*
 * Project    : Battleground
 * Class      : TheThing.java
 * Developers : Batuhan Erden & Emir Arditi
 */

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TheThing extends Character {

	public TheThing() {
		super("The Thing", "Summon Djinn", "Summon Undead", "Summon Ghost", "Summon Dalek", 200, 11, 14, 23, 35);
		
		try {
			setQIcon(ImageIO.read(new File("img/Q-Summon Djinn.png")));
			setWIcon(ImageIO.read(new File("img/W-Summon Undead.png")));
			setEIcon(ImageIO.read(new File("img/E-Summon Ghost.png")));
			setRIcon(ImageIO.read(new File("img/R-Summon Dalek.png")));
			setQMidIcon(ImageIO.read(new File("img/MIDDLE-Q-Summon Djinn.png")));
			setWMidIcon(ImageIO.read(new File("img/MIDDLE-W-Summon Undead.png")));
			setEMidIcon(ImageIO.read(new File("img/MIDDLE-E-Summon Ghost.png")));
			setRMidIcon(ImageIO.read(new File("img/MIDDLE-R-Summon Dalek.png")));
			setCharacterPortrait(ImageIO.read(new File("img/TheThing.png")));
			setEnvironment(ImageIO.read(new File("img/Environment6.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}