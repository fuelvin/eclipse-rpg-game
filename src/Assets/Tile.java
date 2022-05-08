package Assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.Game;
import Game.Handler;
import Assets.Assets;

public class Tile {
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new Tile(Assets.grass, 0, false, false);
	public static Tile rockTile = new Tile(Assets.rock, 1, false, false);
	
	public static Tile ledgeTopLeft = new Tile(Assets.ledgeTopLeft, 3, true, true);
	public static Tile ledgeTopMiddle = new Tile(Assets.ledgeTopMiddle, 4, true, false);
	public static Tile ledgeTopRight = new Tile(Assets.ledgeTopRight, 5, true, true);
	
	public static Tile ledgeTile = new Tile(Assets.ledgeMiddleLeft, 6, true, false);
	public static Tile ledgeMiddleMiddle = new Tile(Assets.ledgeMiddleMiddle, 7, true, false);
	public static Tile middleRightLedge = new Tile(Assets.ledgeMiddleRight, 8, true, false);
	
	public static Tile leftCornerLedge = new Tile(Assets.leftCornerLedge, 9, true, true);
	public static Tile bottomMiddleLedge = new Tile(Assets.bottomMiddleLedge, 10, true, false);
	public static Tile bottomRightLedge = new Tile(Assets.bottomRightLedge, 11, true, true);
	
	public static Tile ledgeCornerTopRight = new Tile(Assets.ledgeCornerTopRight, 14, true, false);
	public static Tile ledgeCornerTopLeft = new Tile(Assets.ledgeCornerTopLeft, 15, true, false);
	
	public static Tile ledgeMiddle = new Tile(Assets.ledge, 12, true, false);
	public static Tile grassLedge = new Tile(Assets.grassLedge, 13, true, false);
	
	public static Tile bush = new Tile(Assets.bush, 2, false, false);
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	protected boolean isSolid;
	protected boolean isFront;

	public Tile(BufferedImage texture, int id, boolean isSolid, boolean isFront) {
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
		this.isSolid = isSolid;
		this.isFront = isFront;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public boolean front() {
		return isFront;
	}
	
	public int getId() {
		return id;
	}

	
}
