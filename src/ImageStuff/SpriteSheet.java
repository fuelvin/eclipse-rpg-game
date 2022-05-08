package ImageStuff;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		//returns new image of the x and y area specified
		return sheet.getSubimage(x, y, width, height); 
	}
}
