/*
 * Project    : Battleground
 * Class      : BigBrother.java
 * Developers : Batuhan Erden & Emir Arditi
 */

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BigBrother extends Character {

	public BigBrother() {
		super("Big Brother", "Fist", "Charge", "Drown", "Starve", 300, 7, 9, 15, 23);
		
		try {
			setQIcon(ImageIO.read(new File("img/Q-Fist.png")));
			setWIcon(ImageIO.read(new File("img/W-Charge.png")));
			setEIcon(ImageIO.read(new File("img/E-Drown.png")));
			setRIcon(ImageIO.read(new File("img/R-Starve.png")));
			setQMidIcon(ImageIO.read(new File("img/MIDDLE-Q-Fist.png")));
			setWMidIcon(ImageIO.read(new File("img/MIDDLE-W-Charge.png")));
			setEMidIcon(ImageIO.read(new File("img/MIDDLE-E-Drown.png")));
			setRMidIcon(ImageIO.read(new File("img/MIDDLE-R-Starve.png")));
			setCharacterPortrait(ImageIO.read(new File("img/BigBrother.png")));
			setEnvironment(ImageIO.read(new File("img/Environment3.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}