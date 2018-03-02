package creation;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class AsteroidCluster extends CelestialBody {
	
	Asteroid asteroid;
	Random speed = new Random();
	int velocity;
	int clusterX;
	int clusterY;
	Random size = new Random();
	int amount;
	Graphics2D g2d;
	ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	
	public AsteroidCluster() {
		velocity = speed.nextInt(20 + 1 - 5) + 5;
		x = 0;
		y = 0;
		amount = size.nextInt(3 + 1 - 1) + 1;
	}
	
	public ArrayList<Asteroid> createCluster() {
		for(int i = 0; i < amount; i++) {
			asteroid = new Asteroid();
			if(i % 2 == 0) {					//just a metric
				asteroid.x = clusterX + asteroid.size;
				asteroid.y = clusterY + asteroid.size;
			}
			else {
				asteroid.x = clusterX - asteroid.size;
				asteroid.y = clusterY - asteroid.size;
			}
			asteroids.add(asteroid);
		}
		return asteroids;
	}
	
	public void move() {
		for(int asteroid = 0; asteroid < asteroids.size(); asteroid++) {
			if (asteroids.get(asteroid).x < Display.width || asteroids.get(asteroid).y < Display.height) {
				asteroids.get(asteroid).x += velocity;
				asteroids.get(asteroid).y += velocity;
			}
		}
	}
}
