package Assets;

import java.awt.Graphics;

public class Text {
	private String s;
	private int x;
	private int y;
	private int multiplier;
	private int type;
	private int start;
	private int letterCount;
	private boolean show;
	
	public Text(String s, int x, int y, int multiplier, int type) {
		this.s = s;
		this.x = x;
		this.y = y;
		this.multiplier = multiplier;
		this.type = type;
	}
	
	private int count;
	public void render(Graphics g) {
		if(type == -1) {
			start = x;
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == ' ') {
					count -= multiplier;
					start += 5 * multiplier + count;
					g.drawImage(Assets.miniAlphabet[26], start, y, 3 * multiplier, 6 * multiplier, null);
				} else if(s.toLowerCase().charAt(i) == 'm' || s.toLowerCase().charAt(i) == 'w'){
					count += multiplier;
					start += 5 * multiplier;
					g.drawImage(Assets.miniAlphabet[(s.toLowerCase().charAt(i) - 97)], start, y, 5 * multiplier, 6 * multiplier, null);
				} else if(s.charAt(i) == '!') {
					start += 5 * multiplier + count;
					g.drawImage(Assets.miniAlphabet[27], start, y, 3 * multiplier, 6 * multiplier, null);
				} else {
					start += 5 * multiplier + count;
					g.drawImage(Assets.miniAlphabet[(s.toLowerCase().charAt(i) - 97)], start, y, 4 * multiplier, 6 * multiplier, null);
					count = 0;
				}
			}
		} else if(type == 0) {
			String delims = "[ ]+";
			String[] tokens = s.split(delims);
			int shift = 0;
			int xShift = 0;
			letterCount = 0;
			for(int i = 0; i < tokens.length; i++) {
				if(tokens[i].length() + letterCount > 29) {
					f = true;
					letterCount = 0;
					shift += 60;
				}
				xShift = 400 - ((maxLetters - 1) * multiplier * 3);
				alphabetRender(g, tokens[i], i, xShift, y + shift, 0);
			}
			show = true;
			f = true;
			
		} else if(type == 1) {
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '/') {
					g.drawImage(Assets.numbers[10], x + i * 5 * multiplier, y, 4 * multiplier, 6 * multiplier, null);
				} else {
					if(s.charAt(i) != '-') {
						g.drawImage(Assets.numbers[s.charAt(i) - 48], x + i * 5 * multiplier, y, 4 * multiplier, 6 * multiplier, null);
					}
				}
			}
		} else if(type == 2) {
			for(int i = 0; i < s.length(); i++) {
				g.drawImage(Assets.damageNumbers[s.charAt(i) - 48], x + i * 7 * multiplier, y, 7 * multiplier, 8 * multiplier, null);
			}
		} else if(type == 3) {
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '-') {
					g.drawImage(Assets.redNumbers[0], x + i * 5 * multiplier, y, 4 * multiplier, 6 * multiplier, null);
				} else {
					g.drawImage(Assets.redNumbers[(s.charAt(i) - 48) + 1], x + i * 5 * multiplier, y, 4 * multiplier, 6 * multiplier, null);
				}
			}
		} else if(type == 4) {
			for(int i = 0; i < s.length(); i++) {
				g.drawImage(Assets.moneyNumbers[(s.charAt(i) - 48)], x + i * 5 * multiplier, y, 4 * multiplier, 6 * multiplier, null);
			}
		} else if(type == 5) {
			String delims = "[ ]+";
			String[] tokens = s.split(delims);
			int shift = 0;
			int xShift = 0;
			letterCount = 0;
			for(int i = 0; i < tokens.length; i++) {
				if(tokens[i].length() + letterCount > 28) {
					f = true;
					letterCount = 0;
					shift += 60;
				}
				xShift = 400 - ((maxLetters - 1) * multiplier * 3);
				alphabetRender(g, tokens[i], i, xShift, y + shift, 1);
			}
			show = true;
			f = true;
			
		} 
	}
	
	public void setHealth(int health, int baseHealth) {
		s = health + "/" + baseHealth;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	private int maxLetters;
	private boolean f;
	private void alphabetRender(Graphics g, String s, int j, int x, int y, int type) {
		if(!f) {
			maxLetters++;
		}
		for(int i = 0; i < s.length(); i++) {
			if(!f) {
				maxLetters++;
			}
			if(show) {
				if(s.charAt(i) == ' ') {
					g.drawImage(Assets.alphabet[26], x + letterCount * 6 * multiplier, y, 5 * multiplier, 8 * multiplier, null);
				} else if(s.charAt(i) == '!') {
					g.drawImage(Assets.alphabet[27], x + letterCount * 6 * multiplier, y, 5 * multiplier, 8 * multiplier, null);
				} else {
					if(s.charAt(i) == '/') {
						g.drawImage(Assets.numbers[10], x + i * 5 * multiplier, y, 4 * multiplier, 6 * multiplier, null);
					} else if(s.charAt(i) != '-' && (s.charAt(i) == '1' ||  s.charAt(i) == '2' || s.charAt(i) == '3' || s.charAt(i) == '4' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7' || s.charAt(i) == '8' || s.charAt(i) == '9' || s.charAt(i) == '0')) {
						if(type == 0) {
							g.drawImage(Assets.moneyNumbers[s.charAt(i) - 48], x + letterCount * 6 * multiplier, y, 5 * multiplier, 8 * multiplier, null);
						} else if(type == 1) {
							g.drawImage(Assets.xpNumbers[s.charAt(i) - 48], x + letterCount * 6 * multiplier, y, 5 * multiplier, 8 * multiplier, null);
						}
					} else {
						g.drawImage(Assets.alphabet[(s.toLowerCase().charAt(i) - 97)], x + letterCount * 6 * multiplier, y, 5 * multiplier, 8 * multiplier, null);
					}
				}
			}
			letterCount++;
		}
		g.drawImage(Assets.alphabet[26], x + letterCount * 6 * multiplier, y, 5 * multiplier, 8 * multiplier, null);
		letterCount++;
	}
	
	
	
	
	
	
	
	
	
	
}
