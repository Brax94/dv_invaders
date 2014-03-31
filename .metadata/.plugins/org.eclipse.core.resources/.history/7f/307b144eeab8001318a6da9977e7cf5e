package dv_invaders_game_logic;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import dv_invaders.Game;

public class Obstacle implements Crash {
	
	Rectangle2D hitbox;
	
	Random rand = new Random();
	Game game;
	private int x;
	private int y;
	private int velY;
	private double velX;
	
	public Obstacle(Game game){
		this.game=game;
		x = rand.nextInt(400);
		hitbox = new Rectangle(16,22);
		velY = rand.nextInt(5) + 2;
		velX = (rand.nextInt(3) + 3) * Math.pow((-1), rand.nextInt(2));
	}
	
	public void render(Graphics g, BufferedImage img){
		g.drawImage(img, x, y, game);
	}
	
	public void tick(){
		if (y > 600){
			y = 0;
			x = rand.nextInt(400);
		}
		if (x > 400 || x < 0){
			velX = -velX;
		}
		x+=velX;
		y+=velY;
		hitbox.setFrame(x, y, 16,22);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Rectangle2D getHitbox() {
		return hitbox;
	}
	public void slowDown(){
		velX = velX/2;
		velY = velY/2;
	}
	public void speedUp(){
		velX = 2*velX;
		velY = 2*velY;
	}
	
	public boolean hasBeenShot(){
		return game.getShot().getHitbox().intersects(hitbox);
	}
}