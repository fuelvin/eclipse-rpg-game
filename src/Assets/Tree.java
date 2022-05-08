package Assets;

import java.awt.Color;

import java.awt.Graphics;

import Game.Game;
import Game.Handler;
import Assets.Tile;

public class Tree extends Entity{
	private int type;
	public Tree(Handler handler, float x, float y, int type) {
		super(handler, x, y, Tile.TILEWIDTH * 3, Tile.TILEHEIGHT * 3);
		this.type = type;
		bounds.width = this.width / 2 + 30;
		bounds.height = this.height / 6 + 1;
		bounds.x = Tile.TILEWIDTH * 2 - bounds.width;
		bounds.y = Tile.TILEHEIGHT * 2;

	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.trees[type], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
		if(Game.showHitboxes) {
			g.setColor(Color.red);
			g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
		}
	}

}
