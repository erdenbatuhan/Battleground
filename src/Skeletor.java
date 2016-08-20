/*
 * Project    : Battleground
 * Class      : Skeletor.java
 * Developers : Batuhan Erden & Emir Arditi
 */

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Skeletor extends Character {

	public Skeletor() {
		super("Skeletor", "Bone Attack", "Eye Contact", "Mummify", "Graveyard", 93, 23, 30, 50, 75);
		
		try {
			setQIcon(ImageIO.read(new File("img/Q-Bone Attack.png")));
			setWIcon(ImageIO.read(new File("img/W-Eye Contact.png")));
			setEIcon(ImageIO.read(new File("img/E-Mummify.png")));
			setRIcon(ImageIO.read(new File("img/R-Graveyard.png")));
			setQMidIcon(ImageIO.read(new File("img/MIDDLE-Q-Bone Attack.png")));
			setWMidIcon(ImageIO.read(new File("img/MIDDLE-W-Eye Contact.png")));
			setEMidIcon(ImageIO.read(new File("img/MIDDLE-E-Mummify.png")));
			setRMidIcon(ImageIO.read(new File("img/MIDDLE-R-Graveyard.png")));
			setCharacterPortrait(ImageIO.read(new File("img/Skeletor.png")));
			setEnvironment(ImageIO.read(new File("img/Environment4.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}