package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Assets.Creature;
import Assets.Player;

public class KeyManager implements KeyListener{
	
	private boolean[] keys;
	public boolean up, down, left, right;
	public boolean Up, Down, Left, Right;
	public boolean q, space;
	private int current = -1;
	private int backup = -1;
	private int keysPressed = 0;
	public static boolean isMoving = false;
	private boolean flag = false;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void tick() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		Up = keys[KeyEvent.VK_UP];
		Down = keys[KeyEvent.VK_DOWN];
		Left = keys[KeyEvent.VK_LEFT];
		Right = keys[KeyEvent.VK_RIGHT];
		q = keys[KeyEvent.VK_Q];
		space = keys[KeyEvent.VK_SPACE];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(current != e.getKeyCode() && current != -1) { //has current, move current to backup
			backup = current;
			keys[backup] = false;
		}
		current = e.getKeyCode();
		keys[current] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(backup != -1) { //if you are pressing more than one key
			
			if(e.getKeyCode() != backup) {  //release current, backup becomes current
				keys[current] = false;
				keys[backup] = true;
				current = backup;
			}
			backup = -1;
		} else {
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
				//while(!checkGridMovementX()) {
					keys[keyCode] = true;
					//System.out.println("Player's current xPosition: " + Creature.xPosition);
						//if(Creature.collided) {
						//	break;
					//	}
				//}
			} else if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
				//while(!checkGridMovementY()) {
					keys[keyCode] = true;
						//System.out.println("Player's current yPosition: " + Creature.yPosition);
						//if(Creature.collided) {
							//break;
						//}
				//}
			}
			keys[keyCode] = false;
			current = -1;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	/*protected boolean checkGridMovementX() {
		if(Creature.xPosition % 64 == 0) {
			return true;
		}
		Creature.xPosition += Creature.DEFAULT_SPEED;
		return false;
	}
	*/
	
	/*protected boolean checkGridMovementY() {
		if(Creature.yPosition % 64 == 0) {
			return true;
		}
		Creature.yPosition += Creature.DEFAULT_SPEED;
		return false;
	} */

}
