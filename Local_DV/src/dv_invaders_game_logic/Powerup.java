package dv_invaders_game_logic;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Graphics;

import dv_invaders.Game;

public abstract class Powerup implements Crash{
	protected Random rand = new Random();
	
	//merk at disse må settes i alle subklasser!!
	protected Game game;
	protected Player player;
	
	protected Rectangle2D hitbox;
	protected int x;
	protected int y;
	protected double velY;
	protected double velX;
	protected boolean onScreen;
	
	
	public abstract BufferedImage changeSprite();
	public abstract int activate();
	public abstract void deactivate();
	public abstract void render(Graphics g);
	
	
	public boolean hasCrashed(Player p){
		return hitbox.intersects(p.getHitbox());
	}
	
	public void tick(){
		y += velY;
		
		if (x < 0){
			velX = -velX;
		}
		else if (x > 400){
			velX = -velX;
		}
		
		else if (y>650){
			onScreen = false;
		}
		
		hitbox.setFrame(x,y,20,20);
		if (player.hasCrashed(this)){
			game.activatePowerup(this);
		}
	}
	
	
	
	public Rectangle2D getHitbox() {
		return hitbox;
	}
	
	public boolean onScreen(){
		return onScreen;
	}
}