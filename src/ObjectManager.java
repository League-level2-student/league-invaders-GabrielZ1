import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class ObjectManager implements ActionListener {
	Rocketship rocketship;
	ArrayList<Projectile> projectiles = new ArrayList<>();
	ArrayList<Alien> aliens = new ArrayList<>();
	Random random = new Random();

	int score = 0;

	Timer alienSpawn;
	Timer shootingTimer;

	public ObjectManager(Rocketship rocketship) {
		this.rocketship = rocketship;
	}

	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}

	public void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}

	public void update() {
		for(int i = 0; i<aliens.size(); i++) {

			aliens.get(i).update();

			if(aliens.get(i).y < 0 || aliens.get(i).y > LeagueInvaders.HEIGHT) {
				aliens.get(i).isActive = false;
			}
		}	

		for(int i = 0; i<projectiles.size(); i++) {

			projectiles.get(i).update();

			if(projectiles.get(i).y < 0 || projectiles.get(i).y > LeagueInvaders.HEIGHT) {
				projectiles.get(i).isActive = false;
			}
		}	

		if(rocketship.isActive == true) {
			checkCollision();
			purgeObjects();
		}

		rocketship.updatePosition();
	}

	public void draw (Graphics g) {
		rocketship.draw(g);

		for(int i = 0; i<aliens.size(); i++) {

			aliens.get(i).draw(g);

		}

		for(int i = 0; i<projectiles.size(); i++) {

			projectiles.get(i).draw(g);

		}

	}

	public void purgeObjects() {

		for(int i = 0; i<aliens.size(); i++) {
			if(aliens.get(i).isActive == false) {
				aliens.remove(i);	
			}	
		}

		for(int i = 0; i<projectiles.size(); i++) {
			if(projectiles.get(i).isActive == false) {
				projectiles.remove(i);	
			}	
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == alienSpawn) {
			addAlien();
		}

		if(arg0.getSource() == shootingTimer) {
			rocketship.updateShooting();
		}
	}

	public void checkCollision() {
		for(int i = 0; i<aliens.size(); i++) {

			if(rocketship.collisionBox.intersects(aliens.get(i).collisionBox)) {
				aliens.get(i).isActive = false;
				rocketship.isActive = false;
			}

			for(int j = 0; j<projectiles.size(); j++) {
				if(projectiles.get(j).collisionBox.intersects(aliens.get(i).collisionBox)) {
					aliens.get(i).isActive = false;
					projectiles.get(j).isActive = false;
					score ++;
				}
			}


		}
	}

	public int getScore() {
		return score;
	}

	public void setAlienTimer(Timer alienSpawn) {
		this.alienSpawn = alienSpawn;
	}

	public void setShootingTimer(Timer shootingTimer) {
		this.shootingTimer = shootingTimer;
	}



}
