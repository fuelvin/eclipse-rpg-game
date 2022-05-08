package Assets;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.Game;
import Game.Handler;
import Game.World;
import ImageStuff.Animation;
import States.BattleState;
import States.GameState;
import States.State;
import Assets.Tile;

public class Player extends Creature {

	private Animation animDown, animUp, animLeft, animRight;
	public static int dir = 1;
	public static float xPosition;
	public static float yPosition;
	public static int health;
	public static int baseHealth;
	public static int level;
	public static String name;

	private boolean flag;
	private boolean flag2;
	private boolean flag3;
	private int a;
	private int b;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.PLAYER_WIDTH, Creature.PLAYER_HEIGHT);

		bounds.x = 0;
		bounds.height = 35;
		bounds.y = PLAYER_HEIGHT - bounds.height - 1;
		bounds.width = 43;

		health = 100;
		baseHealth = 100;
		level = 1;
		name = "Fuelvin";

		animDown = new Animation(120, Assets.player_down);
		animUp = new Animation(120, Assets.player_up);
		animLeft = new Animation(120, Assets.player_left);
		animRight = new Animation(120, Assets.player_right);

	}

	@Override
	public void tick() {
		if (!Game.flag2) {
			animDown.tick();
			animUp.tick();
			animRight.tick();
			animLeft.tick();
			getInput();
			move();
			checkEncounter();
		}
		handler.getGameCamera().centerOnEntity(this);
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (handler.getMouseManager().isRightPressed() && !flag) {
			flag = true;
			if (this.speed == 16.0f) {
				this.speed = 4.0f;
			} else {
				this.speed = 16.0f;
			}
		} else if (!handler.getMouseManager().isRightPressed() && flag) {
			flag = false;
		}

		if (handler.getKeymanager().q && !flag2) {
			flag2 = true;
			Game.showHitboxes = !Game.showHitboxes;
		} else if (!handler.getKeymanager().q && flag2) {
			flag2 = false;
		}

		if (handler.getKeymanager().up || handler.getKeymanager().Up) {
			yMove = -speed;
			dir = 1;
			return;
		}
		if (handler.getKeymanager().down || handler.getKeymanager().Down) {
			yMove = speed;
			dir = 0;
			return;
		}
		if (handler.getKeymanager().left || handler.getKeymanager().Left) {
			xMove = -speed;
			dir = 2;
			return;
		}
		if (handler.getKeymanager().right || handler.getKeymanager().Right) {
			xMove = speed;
			dir = 3;
			return;
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		if (Game.showHitboxes) {
			g.setColor(Color.red);
			g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
					(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
		}
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else if (yMove > 0) {
			return animDown.getCurrentFrame();
		} else {
			if (dir == 0) {
				animDown.setIndex(0);
				return animDown.getCurrentFrame();
			} else if (dir == 1) {
				animUp.setIndex(0);
				return animUp.getCurrentFrame();
			} else if (dir == 2) {
				animLeft.setIndex(0);
				return animLeft.getCurrentFrame();
			} else {
				animRight.setIndex(1);
				return animRight.getCurrentFrame();
			}
		}
	}

	private void checkEncounter() {
		World w = handler.getWorld();
		if ((w.getTile(w.getSpawnX() + ((int) Creature.xPosition) / 64,
				w.getSpawnY() + ((int) Creature.yPosition) / 64) == Tile.bush) && !BattleState.encounterFlag
				&& Math.random() >= 0.99) {
			a = w.getSpawnX() + ((int) Creature.xPosition) / 64;
			b = w.getSpawnY() + ((int) Creature.yPosition) / 64;
			if (!flag3) {
				flag3 = true;
				Game.flag = true;
			}
		} else if (BattleState.encounterFlag) {
			if (a != w.getSpawnX() + ((int) Creature.xPosition) / 64
					|| b != w.getSpawnY() + ((int) Creature.yPosition) / 64) {
				BattleState.encounterFlag = false;
				flag3 = false;
			}
		}
	}

}
