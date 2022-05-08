package Game;

import java.awt.Graphics;


import Assets.EntityManager;
import Assets.Player;
import Assets.Tree;
import Assets.Tile;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private int half = Tile.TILEWIDTH / 2;
	
	private EntityManager entityManager;

	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX * Tile.TILEWIDTH + 10);
		entityManager.getPlayer().setY(spawnY * Tile.TILEHEIGHT + 16);
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if(tiles[j][i] >= 96 && tiles[j][i] < 100) {
					entityManager.addEntity(new Tree(handler, (int) (j * Tile.TILEWIDTH), (int) (i * Tile.TILEWIDTH) - (int)(Tile.TILEWIDTH * 1.5), 99 - tiles[j][i]));
				}
			}
		} 
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void tick() {
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0,  handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height,  (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int i = yStart; i < yEnd; i++) {
			for(int j = xStart; j < xEnd; j++) {
				getTile(j, i).render(g, (int) (j * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (i * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
				if(getTile(j, i).front()) {
					getTile(-1, -1).render(g, (int) (j * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (i * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
				}
			}
		}
		
		entityManager.render(g);
		
		for(int i = yStart; i < yEnd; i++) {
			for(int j = xStart; j < xEnd; j++) {
				if(getTile(j, i).front()) {
					getTile(j, i).render(g, (int) (j * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (i * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
				} 
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) {
			return Tile.grassTile;
		}
		return t;
	}
	
	private void loadWorld(String path) {
		System.out.println(path);
		String file = Utils.loadFileAsString(path);
		System.out.println(file);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getSpawnX() {
		return spawnX;
	}
	
	public int getSpawnY() {
		return spawnY;
	}
	
	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}
	
	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}
}









