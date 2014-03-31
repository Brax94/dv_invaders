package dv_invaders;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 50, 150, 100, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 - 50, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 - 50, 350, 100, 50);
	
	
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		
		
		Font font1 = new Font("helvetica", Font.BOLD, 40);
		g.setFont(font1);
		g.setColor(Color.YELLOW);
		
		
		g.fillRect(Game.WIDTH / 2 - 50, 150, 100, 50);
		g.fillRect(Game.WIDTH / 2 - 50, 250, 100, 50);
		g.fillRect(Game.WIDTH / 2 - 50, 350, 100, 50);
		
		g.setColor(Color.white);
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
		
		g.setColor(Color.YELLOW);
		
		g.drawString("Dragvoll Invaders", Game.WIDTH/2 - 170, 100);
	
		Font font2 = new Font("arial", Font.BOLD,30);
		g.setFont(font2);
		
		g.setColor(Color.BLACK);
		g.drawString("Play", playButton.x + 20, playButton.y + 35);
		g.drawString("Help", helpButton.x + 20, helpButton.y + 35);
		
		Font font3 = new Font("arial", Font.BOLD, 25);
		g.setFont(font3);
		g.drawString("Options", quitButton.x + 3 , quitButton.y + 32);
		
		
	
	
	}
	
	
	
}
