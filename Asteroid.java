package creation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class Asteroid extends CelestialBody {

	Random r = new Random();
	Random sizeR = new Random();
	int radius;
	int velocity = r.nextInt(25 + 1 - 5) + 5;
	int x;
	int y;
	int size;
	
	public Asteroid() {
		int sideChance = r.nextInt(10);
		//if(sideChance < 5) {
		x = 0;
		//} else {
			//x = Display.width;
		//}
		y = r.nextInt(Display.height);
		size = sizeR.nextInt(35 + 1 - 10) + 10; //min = 10, max = 35;

	}
	
	public void createAsteroid(Graphics2D g2d) {
			Color color = new Color(169,169,169,127);
			g2d.setColor(color);
			g2d.fillOval(x, y, size, size);
	}
	
	public void move() {
		if (x < Display.width || y < Display.height) {
			x += velocity;
			y += velocity;
		}
	}
}
