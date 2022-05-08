package Assets;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class CoinAndXP {
	
	private int x;
	private int y;
	private int type;
	private int type2;
	private float xVel;
	private float yVel;
	private float opacity = 1;
	
	private float randSpeed;

	public CoinAndXP(int x, int y) {
		this.x = 500;
		this.y = 200;
		type = (int)Math.round(Math.random());
		type2 = (int)Math.round(Math.random());
		if(type2 == 0){
			xVel = (int)(Math.random() * 3 + 1);
		} else {
			xVel = (int)(Math.random() * 3 + 1) * -1;
		}
		randSpeed = (int)(Math.random() * 99 + 1) / 100;
		yVel = 10;
	}
	
	public void tick() {
		x += xVel;
		y -= yVel;
		if(type2 == 0) {
			xVel += randSpeed;
		} else {
			xVel -= randSpeed * -1;
		}
		yVel -= 0.4f;
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(opacity >= 0.017) {
			opacity -= 0.017;
		} else {
			opacity = 0;
		}
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		
		if(type == 0) {
			g.drawImage(Assets.coin, x, y, 8 * 4, 8 * 4, null);
		} else {
			g.drawImage(Assets.xp, x, y, 10 * 4, 8 * 4, null);
		}
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
