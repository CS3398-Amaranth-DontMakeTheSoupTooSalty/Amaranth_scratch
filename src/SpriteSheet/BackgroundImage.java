package SpriteSheet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class BackgroundImage {
	Image bgImage;
	
	public BackgroundImage(Image image) {
		this.bgImage = image;
	}
	
	public void drawBackground(Graphics g) {
		g.drawImage(bgImage, 0, 0, null);
	}

}
