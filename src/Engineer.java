/*
 * Project    : Battleground
 * Class      : Engineer.java
 * Developers : Batuhan Erden & Emir Arditi
 */

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Engineer extends Character {

	public Engineer() {
		super("Engineer", "Wrench", "Integral", "Triplet", "Re-Programme", 140, 15, 20, 33, 50);
		
		try {
			setQIcon(ImageIO.read(new File("img/Q-Wrench.png")));
			setWIcon(ImageIO.read(new File("img/W-Integral.png")));
			setEIcon(ImageIO.read(new File("img/E-Triplet.png")));
			setRIcon(ImageIO.read(new File("img/R-Reprogramme.png")));
			setQMidIcon(ImageIO.read(new File("img/MIDDLE-Q-Wrench.png")));
			setWMidIcon(ImageIO.read(new File("img/MIDDLE-W-Integral.png")));
			setEMidIcon(ImageIO.read(new File("img/MIDDLE-E-Triplet.png")));
			setRMidIcon(ImageIO.read(new File("img/MIDDLE-R-Reprogramme.png")));
			setCharacterPortrait(ImageIO.read(new File("img/Engineer.png")));
			setEnvironment(ImageIO.read(new File("img/Environment1.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}