package Assets;

import Game.Handler;

import Game.KeyManager;
import Assets.Tile;

public abstract class Creature extends Entity{
	
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 4.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;
	public static final int PLAYER_WIDTH = 48;
	public static final int PLAYER_HEIGHT = 80;

	protected int health;
	protected float speed;
	public static float xMove, yMove;
	public static float xPosition, yPosition;
	public static boolean collided = false;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		
	}
	
	public void move() {
		if(xMove != 0 && !checkEntityCollisions(xMove, 0f)) {
			moveX();
		}
		if(yMove != 0 && !checkEntityCollisions(0f, yMove)) {
			moveY();
		}
	}
	
	public void moveX() {
		if(xMove > 0) {
			collided = false;
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
				x += xMove;
				xPosition += xMove;
				//System.out.println("x added to xMove:" + x + " xPosition=" + xPosition);
			} else {
				x = tx * Tile.TILEWIDTH + bounds.x - bounds.width - 1;
			}
		} else if(xMove < 0) {	
			collided = false;
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
				x += xMove;
				xPosition += xMove;
				//System.out.println("x added to xMove:" + x + " xPosition=" + xPosition);
			} else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
	}
	
	public void moveY() {
		if(yMove < 0) {
			collided = false;
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
				yPosition += yMove;
				//System.out.println("y added to yMove:" + y + " yPosition=" + yPosition);
			} else {
				collided = true;
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}
		} else if(yMove > 0) {
			collided = false;
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
				yPosition += yMove;
				//System.out.println("y added to yMove:" + y + " yPosition=" + yPosition);
			} else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
				collided = true;
			}
			
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		if(handler.getWorld().getTile(x, y).isSolid()) {
			collided = true;
		}
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	
//	public float getxMove() {
//		return xMove;
//	}
//
//	public void setxMove(float xMove) {
//		this.xMove = xMove;
//	}
//
//	public float getyMove() {
//		return yMove;
//	}
//
//	public void setyMove(float yMove) {
//		this.yMove = yMove;
//	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}



	
	
}
