package dv_invaders;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 30, 150, 100, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 30, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 30, 350, 100, 50);
	
	
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		
		
		Font font1 = new Font("arial", Font.BOLD, 50);
		g.setFont(font1);
		g.setColor(Color.orange);
		g.drawString("Dragvoll Invaders", 200, 100);
	
		//Font font2 = new Font("arial", Font.BOLD,30);
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
	
	
	}
	
	
	
}
