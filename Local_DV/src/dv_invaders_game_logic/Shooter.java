package dv_invaders_game_logic;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dv_invaders.Game;

public class Shooter extends Powerup {

	private BufferedImage img;

	
	public Shooter(Game game, Player player){
		this.game = game;
		this.player = player;
		this.x=0;
		this.y=rand.nextInt(10);
		this.velY = rand.nextInt(5) + 2;
		this.velX = (rand.nextInt(3) + 3) * Math.pow((-1), rand.nextInt(2));
		this.hitbox = new Rectangle(20,20);
		this.onScreen = true;
		img = game.getSpriteSheet().grabImage(3, 1, 32, 32);
	}
	
	
	public Shot shoot(){
		return new Shot(game,player);
	
	}

	@Override
	public BufferedImage changeSprite() {
//		return game.getSpriteSheet().grabImage(col, row, width, height);
		return null;
	}

	@Override
	public int activate(){
		return 1;
	}


	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics g){
		g.drawImage(img, x, y, game);
	}

}
