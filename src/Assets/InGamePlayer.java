package Assets;

import java.awt.Color;
import java.awt.Graphics;

import Game.Handler;

public class InGamePlayer {
	
	Description description;
	Handler handler;
	
	public InGamePlayer(Handler handler) {
		this.handler = handler;
		description = new Description(1, Player.name, Player.health, Player.baseHealth, Player.level, 392, 396, handler);
	}
	
	public void tick() {
		description.tick();
	}
	
	public void render(Graphics g) {
		description.render(g);
		Color c = new Color(184, 184, 184);
		g.setColor(c);
		//g.fillRect(396, 516, 400, 12);
	}

}
