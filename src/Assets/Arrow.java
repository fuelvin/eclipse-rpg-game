package Assets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import Game.ClickListener;
import Game.UIImageButton;
import Game.UIObject;
import States.BattleState;

public class Arrow extends UIObject{
	private int i = 0;
	private float arrowX = 0;
	private ClickListener clicker;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	protected boolean moved;
	protected BufferedImage[] images;

	public Arrow(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.clicker = clicker;
		this.images = images;
		bounds = new Rectangle((int)x, (int)y, width, height);
		
	}

	public void tick() {	
		i++;
		if(i <= 15) {
			arrowX += 0.2f;
		} else if(i <= 30) {
			arrowX -= 0.2f;
		} else {
			i = 0;
		}
	}

	public void render(Graphics g) {
		g.drawImage(Assets.arrow[0], (int)(x + arrowX), (int)y, width, height, null);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}
	
	public void onMouseMove(MouseEvent e) {
		moved = true;
		if(bounds.contains(e.getX(), e.getY())) {
			hovering = true;
		} else {
			moved = false;
			hovering = false;
		}
	}
	
	public void onMouseRelease(MouseEvent e) {
		if((hovering || moved) && e.getButton() == MouseEvent.BUTTON1) {
			onClick();
		}
	}

}
