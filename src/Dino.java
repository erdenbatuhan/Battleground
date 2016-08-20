/*
 * Project    : Battleground
 * Class      : Dino.java
 * Developers : Batuhan Erden & Emir Arditi
 */

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Dino extends Character {

	public Dino() {
		super("Dino", "Roar", "Tail Attack", "Stomp", "Extinction", 117, 18, 24, 39, 60);
		
		try {
			setQIcon(ImageIO.read(new File("img/Q-Roar.png")));
			setWIcon(ImageIO.read(new File("img/W-Tail Attack.png")));
			setEIcon(ImageIO.read(new File("img/E-Stomp.png")));
			setRIcon(ImageIO.read(new File("img/R-Extinction.png")));
			setQMidIcon(ImageIO.read(new File("img/MIDDLE-Q-Roar.png")));
			setWMidIcon(ImageIO.read(new File("img/MIDDLE-W-Tail Attack.png")));
			setEMidIcon(ImageIO.read(new File("img/MIDDLE-E-Stomp.png")));
			setRMidIcon(ImageIO.read(new File("img/MIDDLE-R-Extinction.png")));
			setCharacterPortrait(ImageIO.read(new File("img/Dino.png")));
			setEnvironment(ImageIO.read(new File("img/Environment2.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}