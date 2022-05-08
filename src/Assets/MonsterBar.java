package Assets;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import States.BattleState;

public class MonsterBar {
	
	private float width;
	private float speed;
	private float x;
	private float y;
	private int id;
	private long lastTime, timer;
	private boolean flag = true;
	public static final int MONSTER_BAR_HEIGHT = 48;
	private int randomTimer;
	private boolean destroyed;
	public static boolean touchingBar;
	private Text redDamageText;
	
	public MonsterBar(float minWidth, float maxWidth, float minSpeed, float maxSpeed, int minTimer, int maxTimer, int id) {
		this.width = (float)(minWidth + (Math.random() * (maxWidth - minWidth)));
		this.speed = (float)(minSpeed + (Math.random() * (maxSpeed - minSpeed)));
		this.x = 700;
		this.y = 671;
		this.id = id;
		randomTimer = (int)(minTimer * (id + 1) + (Math.random() * (maxTimer - minTimer)));
		redDamageText = new Text("-20", 90, 640, 4, 3);
	}
	
	
	private boolean f3;
	public void tick() {
		if(timer > randomTimer) {
			if(!f3) {
				f3 = true;
				Bar.barsShown++;
			}
		}
	}
	
	public static boolean justDestroyed;
	private boolean touchedEnd;
	private int i = 0;
	private boolean f;
	private boolean f2;
	private boolean pressed;
	private boolean draw;
	private float opacity;
	public void render(Graphics g) {
		//System.out.println(touchingBar);
		if(!destroyed) {
			if(flag) {
				flag = false;
				lastTime = System.currentTimeMillis();
			}
			if(timer > randomTimer) {
				if(!((AttackBarManager.xVel >= x && AttackBarManager.xVel <= x + width) && AttackBarManager.pressed)) {
					if(AttackBarManager.xVel >= x && AttackBarManager.xVel <= x + width) {
						touchingBar = true;
						Bar.ids[id] = true;
					} else {
						Bar.ids[id] = false;
					}
					
					if(!AttackBarManager.hitPause) {
						x -= speed;
						if(x < 100) {
							destroyed = true;
							touchedEnd = true;
						}
					}
					drawSquare(g);
				} else {
					touchingBar = true;
					Bar.ids[id] = true;
					if(AttackBarManager.maxId == this.id) {
						if(AttackBarManager.canCheckId) {
							pressed = true;
						}
					} else {
						drawSquare(g);
					}
				}
			} else {
				if(AttackBarManager.hitPause) {
					lastTime = System.currentTimeMillis();
					timer += System.currentTimeMillis() - lastTime;
				} else {
					timer += System.currentTimeMillis() - lastTime;
					lastTime = System.currentTimeMillis();
				}
			}
						
		} else if(touchedEnd) {
			if(i < 50) {
				redDamageText.render(g);
				i++;
				redDamageText.setY(640 - i / 2);
				if(!f) {
					f = true;
					Player.health -= 20;
				}
			} else {
				f = false;
				Bar.barsLeft--;
				if(Bar.barsLeft == 0) {
					BattleState.playerAttack = true;
					BattleState.showBars = false;
					AttackBarManager.xVel = 104;
					AttackBarManager.milliSecondsPassed = 0;
				}
				touchedEnd = false;
			}
		}
		
		checkPressed(g);
		

	}
	
	private void checkPressed(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		if(pressed) {
			if(draw) {
				drawSquare(g);
			}
			
			if(!f2) {
				opacity = 1;
				draw = true;
				i = 0;
				f2 = true;
				redDamageText = new Text("Blocked!", (int)this.x, 635, 4, -1);
				//width -= tempWidth / 10;
				destroyed = true;
				justDestroyed = true;
				AttackBarManager.id = 0;
				AttackBarManager.maxId = -1;
				Bar.ids[id] = false;
			}
			if(i < 30) {
				draw = false;
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
				redDamageText.render(g);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
				i++;
				redDamageText.setY(635 - i / 2);
				opacity -= 0.0333f;
			} else {
				Bar.barsLeft--;
				f2 = false;
				pressed = false;
				i = 0;
			}
		
	}
	}
	
	private void drawSquare(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.orange);
		g.fillRect((int)x, (int)y, (int)width, MONSTER_BAR_HEIGHT);
		
		Stroke oldS = g2.getStroke();
		Stroke s = new BasicStroke(2);
		g2.setStroke(s);
		g2.setColor(Color.black);
		g2.drawRect((int)x, (int)y, (int)width, MONSTER_BAR_HEIGHT);
		g2.setStroke(oldS);
	}
	
	public int getId() {
		return this.id;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
