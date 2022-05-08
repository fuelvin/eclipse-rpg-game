package Assets;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.Handler;
import States.BattleState;
import States.GameState;

public class Monster {
	
	protected BufferedImage img;
	private int width;
	private int height;
	public static int baseHealth;
	public static int health;
	public static int deathState;
	private long lastTime, timer;
	private int money;
	private int xp;
	private Handler handler;
	
	private Text defeatText;
	private Text text;
	private Text rewardsText;
	private Text rewardsText2;
	private Description monsterDescription;
	private ArrayList<CoinAndXP> coinAndXP;
	
	private int x;
	private int y;
	
	public Monster(String name, BufferedImage img, int width, int height, int x, int y, int health, int attack, int level, Handler handler) {
		this.img = img;
		this.width = width;
		this.height = height;
		this.health = health;
		this.baseHealth = health;
		this.money = (level * 4) + (int)(Math.random() * (level * 3) + 1);
		this.xp = (level * 4) + (int)(Math.random() * (level * 3) + 1);
		deathState = 0;
		this.x = x;
		this.y = y;
		lastTime = System.currentTimeMillis();
		coinAndXP = new ArrayList<>();
		monsterDescription = new Description(0, name, health, baseHealth, level, 8, 10, handler);
		text = new Text("You encounter a " + name + "!", 68, 604, 4, 0);
		defeatText = new Text("You defeated the " + name + "!", 56, 654, 4, 0);
		rewardsText = new Text("You gain " + money + " coins!", 0, 654, 4, 0);
		rewardsText2 = new Text("You gain " + xp + " xp!", 0, 654, 4, 5);
		f = false;
		f2 = false;
	}
	
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		monsterDescription.tick();
		if(deathState == 1 && time > 10) {
			for(int i = 0; i < coinAndXP.size(); i++) {
				coinAndXP.get(i).tick();
			}
		}
		
		moveEffect();
	}
	
	
	private int amount = 0;
	private float opacity = 1;
	private int time = 0;
	private int time2 = 0;
	private int maxAmount = 15;
	private boolean f;
	private boolean f2;
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		Composite ogComposite = g2d.getComposite();
		
		if(!BattleState.showBars) {
			text.render(g);
		}
		if(deathState == 1 || deathState == 2) {
			time++;
			time2++;
			if(amount < maxAmount) {
				if(time2 > (50 / maxAmount)) {
					time2 = 0;
					coinAndXP.add(new CoinAndXP(x, y));
					amount++;
				}
			}
			for(int i = 0; i < coinAndXP.size(); i++) {
				coinAndXP.get(i).render(g);
			}
			
			if(time > 100) {
				reward(g);
			}
		} else if(deathState == 3) {
			if(!f) {
				GameState.coins += money;
				f = true;
			}
			rewardsText.render(g);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0));
			//BattleState.switchGameStates = true;
		} else if(deathState == 4) {
			if(!f2) {
				GameState.xp += xp;
				f2 = true;
			}
			rewardsText2.render(g);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0));
		}
		
		g.drawImage(img, x, y, width, height, null);
		g2d.setComposite(ogComposite);
		monsterDescription.render(g);
		
	}
	
	private void reward(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		deathState = 2;
		defeatText.render(g);
		//rewardsText.render(g);
		if(opacity <= 0.04) {
			opacity = 0;
		} else {
			opacity -= 0.04;
		}
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
	}
	
	public int getHealth() {
		return health;
	}
	
	
	
	int i = 0;
	private void moveEffect() {
		if(AttackBarManager.slash) {
			if(!AttackBarManager.critical) {
				if(i < 4) {
					if(timer > 60 && timer < 120) {
						y = 156;
						x = 444;
					} else if(timer > 120 && timer < 180) {
						y = 158;
						x = 435;
					} else if(timer > 180) {
						i++;
						x = 440;
						y = 160;
						timer = 0;
					}
				}
			} else {
				if(i < 5) {
					if(timer > 50 && timer < 100) {
						y = 150;
						x = 448;
					} else if(timer > 100 && timer < 150) {
						y = 152;
						x = 432;
					} else if(timer > 150) {
						i++;
						x = 440;
						y = 160;
						timer = 0;
					}
				}
			}
		} else {
			i = 0;
		}
	}
	
	
	
	
	
	

}
