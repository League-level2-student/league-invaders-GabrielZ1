import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	

	public boolean movingUp = false;
	public boolean movingDown = false;
	public boolean movingLeft = false;
	public boolean movingRight = false;
	public boolean isShooting = false;
	
	ObjectManager objectManager;

	public Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;

		if (needImage) {
			loadImage ("rocket.png");
		}

	}

	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
		super.update();
	}

	public void up() {
		y -= speed;
	}

	public void down() {
		y += speed;
	}

	public void left() {
		x -= speed;
	}

	public void right() {
		x += speed;
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

	public Projectile getProjectile() {
		return new Projectile(x+width/2, y, 10, 10);
	}

	public void updatePosition() {
		if(movingUp && y > 0) {
			up();
		}
		if(movingDown && y < LeagueInvaders.HEIGHT - height) {
			down();
		}
		if(movingLeft && x > 0) {
			left();
		}
		if(movingRight && x < LeagueInvaders.WIDTH - width) {
			right();
		}

	}

	public void updateShooting() {
		if(isShooting && objectManager != null) {
			objectManager.addProjectile(getProjectile());
		}
	}
	
	public void setObjectManager(ObjectManager obj) {
		this.objectManager = obj;
	}
	
}
