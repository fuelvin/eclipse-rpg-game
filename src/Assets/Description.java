package Assets;

import java.awt.Graphics;

import Game.Handler;

public class Description {
	private Text nameText;
	private String name;
	private int level;
	private int x;
	private int y;
	private Level levelDescription;
	private HealthBar healthBar;
	private XPBar xpBar;
	private int baseHealth;
	private int health;
	private int type;
	private Handler handler;
	
	public Description(int type, String name, int health, int baseHealth, int level, int x, int y, Handler handler) {
		nameText = new Text(name, x - 4, y + 22, 4, -1);
		this.handler = handler;
		this.type = type;
		this.name = name;
		this.level = level;
		this.x = x;
		this.y = y;
		this.health = health;
		this.baseHealth = baseHealth;
		levelDescription = new Level(type, level, x, y);
		healthBar = new HealthBar(type, x, y, handler);
		xpBar = new XPBar(x, y);
	}
	
	public Description(int type, String name, int health, int baseHealth, int level, Handler handler) {
		this(type, name, health, baseHealth, level, 0, 0, handler);
	}
	
	public void tick() {
		healthBar.tick();
	}
	
	public void render(Graphics g) {
		if(type == 0 || type == 1) {
			g.drawImage(Assets.enemyDescription, x, y, 102 * 4, 33 * 4, null);
			levelDescription.render(g);
			nameText.render(g);
			healthBar.render(g);
		} else if(type == 2) {
			xpBar.render(g);
			healthBar.render(g);
			g.drawImage(Assets.playerDescription, 0, 0, 105 * 4, 38 * 4, null);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
