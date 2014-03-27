package dv_invaders;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LevelMap {
	
	Game game;
	BufferedImage img;
	BufferedImage img2;
	private int y = 0;
	private int y2 = -1200;
	
	public LevelMap(Game game, int lvl){
		this.game = game;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			img = loader.loadImage("/lvl1.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		img2 = img;
	}
	
	public void render(Graphics g){
		g.drawImage(img2, 0, y2,game);
		g.drawImage(img, 0, y, game);
		
	}
	
	public void tick(){
		if(y == 1200){
			y = -1200;
		}
		y += 5;
		if(y2 == 1200){
			y2 = -1200;
		}
		y2 += 5;
		
		
	}

}
