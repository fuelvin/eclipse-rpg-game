package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import States.BattleState;

public abstract class UIObject {

	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	protected boolean moved;

	public UIObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int) x, (int) y, width, height);
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract void onClick();

	public void onMouseMove(MouseEvent e) {
		moved = true;
		if (bounds.contains(e.getX(), e.getY())) {
			hovering = true;
		} else {
			moved = false;
			hovering = false;
		}
	}

	public void onMouseRelease(MouseEvent e) {
		if ((hovering || moved) && e.getButton() == MouseEvent.BUTTON1) {
			onClick();
		}
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHieght() {
		return height;
	}

	public void setHieght(int hieght) {
		this.height = hieght;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}

}
