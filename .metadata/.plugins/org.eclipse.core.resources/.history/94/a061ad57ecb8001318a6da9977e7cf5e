package dv_invaders_game_logic;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dv_invaders.Game;

public class ExtraLife extends Powerup {

	BufferedImage img;
	
	public ExtraLife(Game game, Player player){
		this.game = game;
		this.player = player;
		this.x=rand.nextInt(400);
		this.y=rand.nextInt(10);
		this.velY = 6;
		this.velX = (rand.nextInt(3) + 3) * Math.pow((-1), rand.nextInt(2));
		this.hitbox = new Rectangle(20,20);
		this.onScreen = true;
		img = game.getSpriteSheet().grabImage(5, 1, 32, 32);
	}
	
	@Override
	public BufferedImage changeSprite() {
//		return game.getSpriteSheet().grabImage(col, row, width, height);
		return null;
	}

	@Override
	public int activate() {
		return 7;
	}
	public void deactivate(){
		return;
	}

	@Override
	public void render(Graphics g){
		g.drawImage(img, x, y, game);
	}

}
