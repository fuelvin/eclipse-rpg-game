package Assets;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import States.GameState;

public class XPBar {
	
	private int x;
	private int y;
	private Text healthText;
	
	public XPBar(int x, int y) {
		this.x = x;
		this.y = y;
		healthText = new Text(Player.health + "/" + Player.baseHealth, x + 152, y + 80, 4, 1);
	}
	
	public void tick() {
	}
	
	public void render(Graphics g) {
		drawXPBar(g);
	}
	
	private void drawXPBar(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int cc = 90;
		Color c = new Color(cc, cc, cc);
		g.setColor(c);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
		g.fillRect(112, 44, 276, 20);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		
		c = new Color(0, 0, 255);
		g.setColor(c);
		for(int i = 0; i < GameState.xp * 10; i++) {
			g.fillRect(x + 88 + i , y + 44, 2, 20);
		}

	}

	
	
	
	
	
	
	
	
	
	
	
	
}
