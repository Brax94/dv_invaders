package dv_invaders_game_logic;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import dv_invaders.Game;

public class Shot {
	
	
	private Game game;
	private int x;
	private int y;
	private Rectangle2D hitbox;
	private double velY;
	private BufferedImage img;
	private boolean hasShot;
	
	public Shot(Game game, Player player){
		this.game = game;
		x = player.getX() + 10;
		y = player.getY() + 10;
		velY = 5;
		hitbox = new Rectangle(20,20);
		img = game.getSpriteSheet().grabImage(3, 1, 20, 20);
		hasShot = true;
	}
	
	public void tick(){
		y -= velY;
		
		if (y<0){
			hasShot = false;
		}
		hitbox.setFrame(x,y,20,20); 
	}
	
	public void render(Graphics g){
		g.drawImage(img, x, y, game);
	}
	
	public boolean hasShot(){
		return hasShot;
	}
	
	public boolean hasCrashed(Crash o){
		return hitbox.intersects(o.getHitbox());
	}
	public Rectangle2D getHitbox() {
		return hitbox;
	}

}
