package space;

import java.util.ArrayList;
import java.util.Random;

public class StarSystem {

  Random random = new Random();
  private int numPlanets = random.nextInt(10) + 2;

  public ArrayList<Planet> createSystem() {
    ArrayList<Planet> planets = new ArrayList<>();
    for (int i = 0; i < numPlanets; i++) {
      Planet planet = new Planet();
      int planetOffset = 10;
      planets.add(planet);
      for (Planet other : planets) {
        if (planet != other) {
          int distance = (int) Math.sqrt(Math.pow((planet.getX() - other.getX()), 2) + Math
              .pow((planet.getY() - other.getY()), 2));
          if (distance < (planet.convertToRadius(planet.getDiameter()) + other
              .convertToRadius(other.getDiameter())) + planetOffset) {
            //planets.remove(planet);
            //System.out.println("Planet removed");
            break;
          }
        }
      }
      //planets.add(planet);
    }
    return planets;
  }

}
