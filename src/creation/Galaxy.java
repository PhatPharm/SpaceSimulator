package creation;

import java.util.ArrayList;
import java.util.Random;

public class Galaxy {

  private Star star;
  private ArrayList<Star> stars = new ArrayList<>();
  Random random = new Random();

  private int numStars = 1;//random.nextInt(12) + 2; 		//ERROR: for some reason, number of stars displayed is always numStars/2.... :(

  public ArrayList<Star> createGalaxy() {
    for (int i = 0; i <= numStars; i++) {
      star = new Star();
      star.createName();
      if (this.checkIfOff()) {
        int starOffset = 40;
        //checking for overlap
        for (Star other : stars) {
          if (star != other) {

            //this gets the orbit diameter of the farthest planet from each star.
            //for(int j = 0; j < star.planetList.size(); j++) {
            int starFarthestPlanet = star.planetList.size() - 1;
            int otherFarthestPlanet = other.planetList.size() - 1;
            int starPlanetRadius = star.planetList.get(starFarthestPlanet).orbitDiameter
                / 2;
            int otherPlanetRadius = other.planetList.get(otherFarthestPlanet).orbitDiameter
                / 2;
            int distance = (int) Math.sqrt(
                Math.pow((starPlanetRadius - otherPlanetRadius), 2) + Math
                    .pow((starPlanetRadius - otherPlanetRadius), 2));
            if (distance < (starPlanetRadius + otherPlanetRadius) + starOffset) {
              System.out.println("Overlapping");
              stars.remove(star);
              break;
            }
          }
        }
      }
    }
    return stars;
  }

  //extra check if offscreen method, not sure if i'll use it.

  private boolean checkIfOff() {
    boolean valid = false;
    if ((star.centerX - star.convertToRadius(star.sizeDiameter) < 0) || (
        star.centerX + star.convertToRadius(star.sizeDiameter) > Display.width) || (
        star.centerY - star.convertToRadius(star.sizeDiameter) < 0) || (
        star.centerY + star.convertToRadius(star.sizeDiameter) > Display.height)) {
      valid = true;
      stars.add(star);
    }
    return valid;
  }

}
