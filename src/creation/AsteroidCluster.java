package creation;

import java.util.ArrayList;
import java.util.Random;

public class AsteroidCluster extends CelestialBody {

  Random size = new Random();
  private Asteroid asteroid;
  private Random speed = new Random();
  private int velocity;
  private int clusterX;
  private int clusterY;
  private int amount;
  private ArrayList<Asteroid> asteroids = new ArrayList<>();

  AsteroidCluster() {
    velocity = speed.nextInt(20 + 1 - 5) + 5;
    x = 0;
    y = 0;
    amount = size.nextInt(3 + 1 - 1) + 1;
  }

  public ArrayList<Asteroid> createCluster() {
    for (int i = 0; i < amount; i++) {
      asteroid = new Asteroid();
      if (i % 2 == 0) {          //just a metric
        asteroid.x = clusterX + asteroid.size;
        asteroid.y = clusterY + asteroid.size;
      } else {
        asteroid.x = clusterX - asteroid.size;
        asteroid.y = clusterY - asteroid.size;
      }
      asteroids.add(asteroid);
    }
    return asteroids;
  }

}
