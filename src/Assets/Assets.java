package Assets;

import java.awt.image.BufferedImage;

import ImageStuff.ImageLoader;
import ImageStuff.SpriteSheet;

public class Assets {

	private static final int width = 16; //Size of each tile in sprite sheet
	private static final int height = 16;
	
	private static final int pWidth = 12;
	private static final int pHeight = 20;
	
	public static BufferedImage grass, rock, ledgeMiddleLeft, tree, bush, leftCornerLedge, ledge, bottomMiddleLedge, bottomRightLedge,
	ledgeMiddleRight, ledgeTopLeft, ledgeTopMiddle, ledgeTopRight, ledgeMiddleMiddle, grassLedge, ledgeCornerTopRight, ledgeCornerTopLeft, bar,
	critBar, barGrass, grassPlatform, enemyDescription, enemyHealthBar, slash, monsterLevel, monsterBar, playerDescription,
	greySquare, barOutline, coin, xp; //images from sprite sheet
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] buttonStart, trees, attackButton, escapeButton, alphabet, numbers, slashAnimation, attackBar, damageNumbers,
	miniAlphabet, statsButton, redNumbers, arrow, moneyNumbers, xpNumbers, monsters;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Tiles.png"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Player.png"));
		SpriteSheet ledgeSheet = new SpriteSheet(ImageLoader.loadImage("/textures/LedgeTiles2.png"));
		SpriteSheet treeSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Trees.png"));
		SpriteSheet barSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Bar.png"));
		SpriteSheet monsterSheet = new SpriteSheet(ImageLoader.loadImage("/textures/Monsters.png"));
		
		buttonStart = new BufferedImage[2];
		buttonStart[0] = sheet.crop(0, height, width * 2, height);
		buttonStart[1] = sheet.crop(0, height * 2, width * 2, height);
		
		attackButton = new BufferedImage[2];
		attackButton[0] = sheet.crop(40, 0, 49, 17);
		attackButton[1] = sheet.crop(89, 0, 49, 17);
		
		statsButton = new BufferedImage[2];
		statsButton[0] = sheet.crop(40, 17, 49, 17);
		statsButton[1] = sheet.crop(89, 17, 49, 17);
		
		
		
		escapeButton = new BufferedImage[2];
		escapeButton[0] = sheet.crop(40, 34, 49, 17);
		escapeButton[1] = sheet.crop(89, 34, 49, 17);
		
		attackBar = new BufferedImage[2];
		attackBar[0] = sheet.crop(32, 0, 4, 34);
		attackBar[1] = sheet.crop(36, 0, 4, 34);
		
		arrow = new BufferedImage[2];
		arrow[0] = barSheet.crop(235, 76, 12, 12);
		arrow[1] = barSheet.crop(235, 76, 12, 12);
		
		trees = new BufferedImage[4];
		trees[0] = treeSheet.crop(0, 0, width * 3, height * 3);
		trees[1] = treeSheet.crop(width * 3, 0, width * 3, height * 3);
		trees[2] = treeSheet.crop(0, height * 3 + 10, width * 3, height * 3);
		trees[3] = treeSheet.crop(width * 3, height * 3 + 10, width * 3, height * 3);
		
		monsters = new BufferedImage[1];
		monsters[0] = monsterSheet.crop(0, 0, 38, 27);
		//monsters[1] = monsterSheet.crop(0, 27, 23, 41);
		
		player_down = new BufferedImage[4];
		player_up = new BufferedImage[4];
		player_left = new BufferedImage[4];
		player_right = new BufferedImage[4];
		
		player_down[0] = playerSheet.crop(0, 0, pWidth, pHeight);
		player_down[1] = playerSheet.crop(pWidth, 0, pWidth, pHeight);
		player_down[2] = playerSheet.crop(pWidth * 2, 0, pWidth, pHeight);
		player_down[3] = playerSheet.crop(pWidth * 3, 0, pWidth, pHeight);
		
		player_up[0] = playerSheet.crop(0, pHeight * 2, pWidth, pHeight);
		player_up[1] = playerSheet.crop(pWidth, pHeight * 2, pWidth, pHeight);
		player_up[2] = playerSheet.crop(pWidth * 2, pHeight * 2, pWidth, pHeight);
		player_up[3] = playerSheet.crop(pWidth * 3, pHeight * 2, pWidth, pHeight);
		
		player_left[0] = playerSheet.crop(0, pHeight, pWidth, pHeight);
		player_left[1] = playerSheet.crop(pWidth, pHeight, pWidth, pHeight);
		player_left[2] = playerSheet.crop(pWidth * 2, pHeight, pWidth, pHeight);
		player_left[3] = playerSheet.crop(pWidth * 3, pHeight, pWidth, pHeight);
		
		player_right[0] = playerSheet.crop(0, pHeight * 3, pWidth, pHeight);
		player_right[1] = playerSheet.crop(pWidth, pHeight * 3, pWidth, pHeight);
		player_right[2] = playerSheet.crop(pWidth * 2, pHeight * 3, pWidth, pHeight);
		player_right[3] = playerSheet.crop(pWidth * 3, pHeight * 3, pWidth, pHeight);
		
		slashAnimation = new BufferedImage[9];
		
		for(int i = 0; i < 9; i++) {
			slashAnimation[i] = barSheet.crop(187 + i * 6, 52, 6, 25);
		}
		
		alphabet = new BufferedImage[28];
		for(int i = 0; i < 28; i++) {
			alphabet[i] = barSheet.crop(101 + i * 6, 32, 5, 8);
		}
		
		miniAlphabet = new BufferedImage[28];
		int count = 0;
		for(int i = 0; i < 28; i++) {
			if(i == 12) {
				count++;
				miniAlphabet[i] = barSheet.crop(101 + i * 5, 25, 5, 6);
			} else if(i == 22) {
				count++;
				miniAlphabet[i] = barSheet.crop(101 + i * 5 + 1, 25, 5, 6);
			} else if(i == 27) {
				miniAlphabet[i] = barSheet.crop(101 + i * 5, 25, 3, 6);
			} else {
				miniAlphabet[i] = barSheet.crop(101 + i * 5 + count, 25, 4, 6);
			}
		}
		
		numbers = new BufferedImage[11];
		for(int i = 0; i < 10; i++) {
			numbers[i] = barSheet.crop(246 + i * 5, 25, 4, 6);
		}
		numbers[10] = barSheet.crop(296, 25, 4, 6);
		
		damageNumbers = new BufferedImage[10];
		for(int i = 0; i < 10; i++) {
			damageNumbers[i] = barSheet.crop(187 + i * 7, 40, 7, 8);
		}
		
		redNumbers = new BufferedImage[11];
		for(int i = 0; i < 11; i++) {
			redNumbers[i] = barSheet.crop(234 + i * 5, 49, 4, 6);
		}
		
		moneyNumbers = new BufferedImage[11];
		for(int i = 0; i < 11; i++) {
			moneyNumbers[i] = barSheet.crop(235 + i * 6, 58, 5, 8);
		}
		
		xpNumbers = new BufferedImage[11];
		for(int i = 0; i < 11; i++) {
			xpNumbers[i] = barSheet.crop(235 + i * 6, 67, 5, 8);
		}
		
		grass = sheet.crop(width, 0, width, height);
		ledgeMiddleLeft = ledgeSheet.crop(0, height, width, height);
		ledgeMiddleRight = ledgeSheet.crop(width * 2, height, width, height);
		bush = sheet.crop(0, height * 3, width, height);
		leftCornerLedge = ledgeSheet.crop(0, height * 2, width, height);
		ledge = ledgeSheet.crop(0, height * 3, width, height);
		grassLedge = ledgeSheet.crop(0, height * 4, width, height);
		bottomMiddleLedge = ledgeSheet.crop(width, height * 2, width, height);
		bottomRightLedge = ledgeSheet.crop(width * 2, height * 2, width, height);
		ledgeTopLeft = ledgeSheet.crop(0, 0, width, height);
		ledgeTopMiddle = ledgeSheet.crop(width, 0, width, height);
		ledgeTopRight = ledgeSheet.crop(width * 2, 0, width, height);
		ledgeMiddleMiddle = ledgeSheet.crop(width, height, width, height);
		ledgeCornerTopRight = ledgeSheet.crop(width * 3, 0, width, height);
		ledgeCornerTopLeft = ledgeSheet.crop(width * 4, 0, width, height);
		
		//bar = barSheet.crop(0, 0, 300, 25);
		critBar = barSheet.crop(299, 0, 6, 25);
		barGrass = barSheet.crop(7, 25, 94, 85);
		grassPlatform = barSheet.crop(101, 40, 86, 37);
		enemyDescription = barSheet.crop(102, 77, 102, 33);
		enemyHealthBar = barSheet.crop(204, 88, 77, 10);
		slash = barSheet.crop(187, 57, 6, 20);
		monsterLevel = barSheet.crop(204, 77, 23, 10);
		monsterBar = barSheet.crop(0, 110, 20, 26);
		playerDescription = barSheet.crop(0, 110, 105, 38);
		greySquare = barSheet.crop(105, 110, 185, 55);
		bar = barSheet.crop(105, 165, 150, 13);
		coin = barSheet.crop(204, 98, 8, 8);
		xp = barSheet.crop(212, 98, 10, 8);
		
		
		
	}
	
	
	
	
	
}
