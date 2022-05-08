package Assets;


import java.awt.Color;

import java.awt.Graphics;
import java.util.ArrayList;
import Game.Handler;
import States.BattleState;

public class Bar {
	public static int randomX = (int)(Math.random() * 540 + 150);
	public static int randomX2 = (int)(Math.random() * 540 + 150);
	public static int height = 670;
	public static int barsLeft;
	public static int barsShown;
	private int v = 0;
	private int x = 0;
	private int barWidth;
	private int barHeight;
	private ArrayList<MonsterBar> monsterBars;
	public static int monsterBarWidth = 40;
	public static boolean[] ids = new boolean[100];
	
	public Bar(Handler handler) {
		barWidth = 1;
		barHeight = 50;
		monsterBars = new ArrayList<>();
	}
	
	private boolean render;
	public void tick() {
		if(BattleState.playerAttack) {
			render = false;
		} else if(!BattleState.playerAttack && !render){
			render = true;
			monsterBars.clear();
			barsLeft = 0;
			barsShown = 0;
			for(int i = 0; i < 3; i++) {
				barsLeft++;
				monsterBars.add(new MonsterBar(60, 85, 1.5f, 2.7f, 1000, 2500, i));
				randomX2 = (int)(Math.random() * (600 - monsterBarWidth - 2) + 100);
			}
		}
	}

	public void render(Graphics g) {
		if(Monster.deathState < 2) {
			if(BattleState.playerAttack) {
				playerBarRender(g);
				Color c = new Color(0, 0, 0);
				g.setColor(c);
				g.drawImage(Assets.critBar, randomX - 6, height, 12, 50, null);
			} else {
				enemyBarRender(g);
			}
			g.drawImage(Assets.bar, 100, height - 1, 150 * 4, 13 * 4, null);
			x = 0;
			v = 0;
			
			if(!BattleState.playerAttack) {
				MonsterBar.touchingBar = false;
				for(MonsterBar e: monsterBars) {
					e.render(g);
				}
				if(!MonsterBar.touchingBar) {
					AttackBarManager.id = 0;
				}
			}
		}
	}
	
	private void enemyBarRender(Graphics g) {
		Color c = new Color(255, 0, 0);
		g.setColor(c);
		g.fillRect(100, height, 600, barHeight);
	}
	
	private void playerBarRender(Graphics g) {
		for(int i = 0; i < 10; i++) {
			drawBar(g, v, 255, 0);
			v += 10;
			x++;
		}
		for(int i = 0; i < 52; i++) {
			drawBar(g, v, 255, 0);
			v += 3;
			x++;
		}
		v = 0;
		for(int i = 0; i < 155; i++) {
			drawBar(g, 255, 255 - v, 0);
			v += 1;
			x++;
		}
		for(int i = 0; i < 50; i++) {
			drawBar(g, 255, 255 - v, 0);
			v += 2;
			x++;
		}
		int j = Math.max(800 - (randomX - x), 200 - (randomX - x));
		for(int i = 0; i < j; i++) {
			drawBar(g, 255, 0, 0);
			x++;
		}
	}
	
	private void drawBar(Graphics g, int r, int gr, int b) {
		Color c = new Color(r, gr, b);
		g.setColor(c);
		g.fillRect(randomX - x, height, barWidth, barHeight);
		g.fillRect(randomX + x, height, barWidth, barHeight);
	}
	
}
