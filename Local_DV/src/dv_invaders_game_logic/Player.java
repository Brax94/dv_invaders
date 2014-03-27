package dv_invaders_game_logic;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import dv_invaders.Game;
import dv_invaders.SpriteSheet;

public class Player {

	Rectangle2D hitbox;

	Game game;
	private int x;
	private int y;

	private double velX;
	private double velY;


	public Player(Game game){
		this.game = game;
		x = 184;
		y = 530;
		velX=0;	
		velY=0;
		hitbox = new Rectangle(16,22);
	}


	public void tick(){
		x+=velX;
		y+=velY;
		if (x>378) x = 378;
		if (x<0) x = 0;
		if (y>600) y = 600;
		if (y<0) y = 0;
		hitbox.setFrame(x, y, 16,22);
	}

	public void render(Graphics g, BufferedImage img){
		g.drawImage(img, x, y, game);

	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public Rectangle2D getHitbox() {
		return hitbox;
	}

	public boolean hasCrashed(Crash o){
		return hitbox.intersects(o.getHitbox());
	}
}