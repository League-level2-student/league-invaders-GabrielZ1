import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

	public GamePanel() {
		titleFont = new Font ("Arial", Font.PLAIN, 48);
		subTextFont = new Font ("Arial", Font.PLAIN, 20);

		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
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
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
	}
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);	
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 125, 150);

		g.setFont(subTextFont);
		g.drawString("You killed 0 enemies", 155, 350);
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
			}
			else {
				currentState++;
			}
		}   

		if(currentState == GAME) {
			if (arg0.getKeyCode()==KeyEvent.VK_UP) {
				System.out.println("UP");
			}
			if (arg0.getKeyCode()==KeyEvent.VK_DOWN) {
				System.out.println("DOWN");
			}
			if (arg0.getKeyCode()==KeyEvent.VK_LEFT) {
				System.out.println("LEFT");
			}
			if (arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
				System.out.println("RIGHT");
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

}
