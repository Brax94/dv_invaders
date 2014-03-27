package dv_invaders;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dv_invaders_game_logic.Objects;
import dv_invaders_game_logic.Obstacle;
import dv_invaders_game_logic.Player;
import dv_invaders_game_logic.Powerup;
import dv_invaders_game_logic.Shield;
import dv_invaders_game_logic.Shooter;
import dv_invaders_game_logic.Shot;
import dv_invaders_game_logic.Slower;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 600;
	public static final int SCALE = 1;
	public static final String TITLE = "Dragvoll Invaders";
	private int score = 0;


	private Thread thread;
	private boolean running = false;

	private BufferedImage image = new BufferedImage(WIDTH*SCALE, HEIGHT*SCALE, BufferedImage.TYPE_INT_RGB );
	public BufferedImage playerSprite;
	public BufferedImage obstacleSprite;
	LevelMap map = new LevelMap(this, 1);

	Player player = new Player(this);
	Obstacle obstacle = new Obstacle(this);
	public Objects objects = new Objects(this);
	public SpriteSheet ss;
	private Powerup powerup;
	private boolean tempSlow = false;
	private int hasPowerup;
	private boolean isPowerup = false;
	Random random =  new Random();

	private Shot shot;
	private boolean hasShot;


	private Menu menu = new Menu();

	private enum STATE{
		MENU,
		GAME,
		DEATH
	};

	private STATE state = STATE.MENU;



	private synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();

	}

	private synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	private void init() {
		requestFocus();
		start();

		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			ss = new SpriteSheet(loader.loadImage("/dv_spritesheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		addKeyListener(new KeyInput(this));
		playerSprite = ss.grabImage(1, 1, 32, 32);
		obstacleSprite = ss.grabImage(2, 1, 32, 32);

		hasPowerup = 0;


	}

	public void run() {
		init();
		System.out.println("running");

		// Makes sure that game updates 60 tics a secound! (Sett deg inn i denne
		// koden ordentlig);
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}

			render();
			frames++;

			if ((System.currentTimeMillis() - timer) > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, FPS " + frames + " Score: " + score);
				updates = 0;
				frames = 0;
			}

			// End of tick logic;

			// Implement game logic:


			//Gamelogic ends here;
		}
		stop();
	}

	public void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image,0 ,0 ,this);

		//		if(state == STATE.MENU){
		//			menu.render(g);
		//		}
		//		else{
		map.render(g);
		player.render(g, playerSprite);
		obstacle.render(g, obstacleSprite);
		objects.render(g);
		if (isPowerup){
			powerup.render(g);
		}
		if (hasShot){
			shot.render(g);
		}
		//		}

		g.dispose();
		bs.show();

	}

	int i = 0;
	public void tick(){
		map.tick();
		player.tick();
		objects.tick();
		obstacle.tick();
		score ++;
		if (score % 50 == 0 && score < 400){
			objects.addObstacles();								
		}

		if (score % 300 == 0){
//			int temp = random.nextInt(3) + 1;
//			if (temp == 1){
				powerup = new Shooter(this, player);	
//			}
//			else if (temp == 2){
//				powerup = new Shield(this, player);
//			}
//			else if (temp == 3){
//				powerup = new Slower(this, player);
//			}
			isPowerup = powerup.onScreen();
		}	
		if (isPowerup){
			powerup.tick();
			if (!(powerup.onScreen())){
				isPowerup = false;
			}
		}

		if ((hasPowerup!=0)){
			i++;
		}


		if (i == 600 || (i==300 && hasPowerup == 3)){
			hasPowerup = 0;
			i = 0;

			//			playerSprite = ss.grabImage(1, 1, 32, 32);
			deActivatePowerup(powerup);
		}

		if (hasShot){
			shot.tick();
		}
		//Boss her: if score == ettellerannet;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
			player.setVelX(-5);

		}

		else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			player.setVelX(5);

		}
		else if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
			player.setVelY(-5);

		}

		else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
			player.setVelY(5);

		}

		else if (key == KeyEvent.VK_SPACE){
			if (hasPowerup == 1){
				Shooter shooter =(Shooter) powerup;
				shot = shooter.shoot();
				hasShot = shot.hasShot();
			}
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
			player.setVelX(0);

		}

		else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
			player.setVelX(0);

		} 
		else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W){
			player.setVelY(0);

		}

		else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S){
			player.setVelY(0);

		} 

	}
	public void isFinished(){
		running = false;
		//Legg inn mulighet til retry:
		JButton button = new JButton("Click here to try again!");
		JFrame frame = new JFrame();
		button.setBounds(100, 300, 200, 100);
		button.setBackground(new Color(34));
		button.setVisible(true);
	}

	public void activatePowerup(Powerup pp){
		if (pp instanceof Slower && tempSlow) return;
		else if (pp instanceof Slower){
			tempSlow = true;
		}
		//		playerSprite = pp.changeSprite();
		hasPowerup = pp.activate();	
		isPowerup = false;
	}

	public void deActivatePowerup(Powerup pp){
		pp.deactivate();
		if (pp instanceof Slower){
			tempSlow = false;
		}
	}

	public void PowerupOut(){
		this.isPowerup = false;
	}

	public SpriteSheet getSpriteSheet(){
		return ss;
	}

	public Player getPlayer() {
		return player;
	}

	public int getScore() {
		return score;
	}

	public int getHasPowerup() {
		return hasPowerup;
	}
	public boolean hasShot(){
		return hasShot;
	}

	public Shot getShot() {
		return shot;
	}

	public static void main(String[] args) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		@SuppressWarnings("static-access")
		JFrame frame = new JFrame(game.TITLE);
		JPanel panel = new JPanel();
		panel.setSize(WIDTH*SCALE, HEIGHT*SCALE);
		panel.add(game);
		frame.setContentPane(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}
}