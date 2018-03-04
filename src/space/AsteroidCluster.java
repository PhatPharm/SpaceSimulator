package space;

import java.util.ArrayList;
import java.util.Random;

public class AsteroidCluster extends CelestialBody {

  private int clusterX;
  private int clusterY;
  private int amount;
  private ArrayList<Asteroid> asteroids = new ArrayList<>();

  public AsteroidCluster() {
    Random speed = new Random();
    int velocity = speed.nextInt(20 + 1 - 5) + 5;
    x = 0;
    y = 0;
    Random size = new Random();
    amount = size.nextInt(3 + 1 - 1) + 1;
  }

  public ArrayList<Asteroid> createCluster() {
    for (int i = 0; i < amount; i++) {
      Asteroid asteroid = new Asteroid();
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
