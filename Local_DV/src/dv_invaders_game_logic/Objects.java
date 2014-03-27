package dv_invaders_game_logic;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import dv_invaders.Game;

public class Objects {

	Random rand;
	LinkedList<Obstacle> obstacles;
	Game game;
	public Objects(Game game){
		this.game=game;
		obstacles = new LinkedList<Obstacle>();
		rand = new Random();
	}

	public Obstacle addObstacles(){
		Obstacle o = new Obstacle(game);
		obstacles.add(o);
		return o;
	}

	public void render(Graphics g){
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).render(g, game.obstacleSprite);
		}
	}

	public void tick(){
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).tick();
			if ((game.getPlayer().hasCrashed(obstacles.get(i)))){
				if (game.getHasPowerup() !=2) {
					game.isFinished();
				}
			}
			else if (game.hasShot()){
				if 	(game.getShot().hasCrashed(obstacles.get(i))){
					obstacles.remove(obstacles.get(i));
				}
			}
		}
	}
	public void slow(){
		for (int j = 0; j < obstacles.size(); j++) {
			obstacles.get(j).slowDown();
		}
	}
	public void speedUp(){
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).speedUp();
		}
	}

}