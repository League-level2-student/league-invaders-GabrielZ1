import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	GamePanel gp;

	public static void main(String[] args) {
		LeagueInvaders leagueInvaders = new LeagueInvaders();
		leagueInvaders.setup();
	}


	public LeagueInvaders() {
		frame = new JFrame();
		gp = new GamePanel();
	}


	void setup() {
		frame.add(gp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.addKeyListener(gp);

	}

}
