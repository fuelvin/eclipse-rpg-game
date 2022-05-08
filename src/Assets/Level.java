package Assets;

import java.awt.Graphics;

public class Level {
	
	private int level;
	private int x;
	private int y;
	private Text levelText;
	private int type;
	
	public Level(int type, int level, int x, int y) {
		this.type = type;
		this.level = level;
		this.x = x;
		this.y = y;
		levelText = new Text(level + "", x + 368, y + 22, 4, 1);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.monsterLevel, x + 304, y + 14, 92, 40, null);
		levelText.render(g);
	}

}
