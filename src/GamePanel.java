import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;

	int currentState = MENU;

	Font titleFont;
	Font subTextFont;

	Timer frameDraw;

	Rocketship rocketship = new Rocketship(250,700,50,50);

	ObjectManager objectManager = new ObjectManager(rocketship);

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	Timer alienSpawn;


	public GamePanel() {
		titleFont = new Font ("Arial", Font.PLAIN, 48);
		subTextFont = new Font ("Arial", Font.PLAIN, 20);

		frameDraw = new Timer(1000/60, this);
		frameDraw.start();

		if (needImage) {
			loadImage ("space.png");
		}

	}

	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
			drawMenuState(g);
		}
		else if(currentState == GAME){
			drawGameState(g);
		}
		else if(currentState == END){
			drawEndState(g);
		}
	}


	void updateMenuState() {

	}
	void updateGameState() {
		objectManager.update();
		if(rocketship.isActive == false) {
			currentState = END;
		}

	}
	void updateEndState() {

	}


	void drawMenuState(Graphics g) {	
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 25, 150);

		g.setFont(subTextFont);
		g.drawString("Press ENTER to start", 150, 350);
		g.drawString("Press SPACE for instructions", 115, 500);

	}
	void drawGameState(Graphics g) {

		if (gotImage) {
			g.drawImage(image, 0, 0, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}

		objectManager.draw(g);

	}
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);	
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 125, 150);

		g.setFont(subTextFont);
		if(objectManager.getScore() == 1) {
			g.drawString("You killed " + objectManager.getScore() +  " enemy", 155, 350);
		}
		else {
			g.drawString("You killed " + objectManager.getScore() +  " enemies", 155, 350);
		}
		g.drawString("Press ENTER to restart", 140, 500);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
			updateMenuState();
		}
		else if(currentState == GAME){
			updateGameState();
		}
		else if(currentState == END){
			updateEndState();
		}

		repaint();

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
				rocketship = new Rocketship(250,700,50,50);
				objectManager = new ObjectManager(rocketship);
			}
			else if (currentState == MENU){
				currentState = GAME;
				startGame();
			}
			else if (currentState == GAME) {
				currentState = END;
				alienSpawn.stop();
			}
		}   

		if(currentState == MENU) {
			if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
				JOptionPane.showMessageDialog (null, "Use arrow keys to move. Press SPACE to fire. Try not to die");
			}
		}

		if(currentState == GAME) {
			if (arg0.getKeyCode()==KeyEvent.VK_UP && rocketship.y > 0) {
				rocketship.up();
			}
			if (arg0.getKeyCode()==KeyEvent.VK_DOWN && rocketship.y < LeagueInvaders.HEIGHT - rocketship.height) {
				rocketship.down();
			}
			if (arg0.getKeyCode()==KeyEvent.VK_LEFT && rocketship.x > 0) {
				rocketship.left();
			}
			if (arg0.getKeyCode()==KeyEvent.VK_RIGHT && rocketship.x < LeagueInvaders.WIDTH - rocketship.width) {
				rocketship.right();
			}


			if (arg0.getKeyCode()==KeyEvent.VK_SPACE) {
				objectManager.addProjectile(rocketship.getProjectile());
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

	public void startGame() {
		alienSpawn = new Timer(1000, objectManager);
		alienSpawn.start();
	}

}
