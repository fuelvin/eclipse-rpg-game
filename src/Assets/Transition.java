package Assets;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

public class Transition {
	private int i = 0;
	private int milliSecondsPassed;
	private Timer timer;
	private TimerTask task;
	public static boolean canStart;
	
	
	public Transition() {
		canStart = false;
		milliSecondsPassed = 0;
		timer = new Timer();
		task = new TimerTask() {
			public void run() {
				milliSecondsPassed++;
				//System.out.println(milliSecondsPassed);
			}
		};
		timer.scheduleAtFixedRate(task, 1, 1);
	}

	public void render(Graphics g) {
		//System.out.println(milliSecondsPassed);
			if(milliSecondsPassed > 400) {
				g.setColor(Color.white);
				g.fillRect(0, 0, 800, 800);
			}
			if(milliSecondsPassed > 475) {
				g.dispose();
				milliSecondsPassed = 0;
				i++;
			}
			if(i == 3) {
				i = 0;
				timer.cancel();
				task.cancel();
				canStart = true;
			}
	}
}
