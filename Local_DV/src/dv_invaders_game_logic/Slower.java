package dv_invaders_game_logic;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dv_invaders.Game;

public class Slower extends Powerup {

	BufferedImage img;
	
	public Slower(Game game, Player player){
		this.game = game;
		this.player = player;
		this.x=0;
		this.y=rand.nextInt(10);
		this.velY = rand.nextInt(5) + 2;
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
		game.objects.slow();
		return 3;
	}
	public void deactivate(){
		game.objects.speedUp();
	}

	@Override
	public void render(Graphics g){
		g.drawImage(img, x, y, game);
	}

}
