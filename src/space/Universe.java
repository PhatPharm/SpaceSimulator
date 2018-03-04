package space;

import java.util.ArrayList;

public class Universe {

  public ArrayList<Galaxy> createUniverse() {
    ArrayList<Galaxy> galaxies = new ArrayList<>();
    int numGalaxies = 1;
    for (int i = 0; i < numGalaxies; i++) {
      Galaxy galaxy = new Galaxy();
      galaxies.add(galaxy);
    }
    return galaxies;
  }
}
