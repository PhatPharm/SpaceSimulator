package space;

import java.util.ArrayList;

public class ShipFleet {

  public ArrayList<Ship> shipList;
  private boolean allReached = false;


  public ShipFleet() {
    shipList = new ArrayList<>();
  }

  public void addShips(int size) {
    for (int i = 0; i < size; i++) {
      Ship ship = new Ship();
      shipList.add(ship);
    }
  }

  public boolean allReachedPlanets() {
    int counter = 0;
    for (Ship aShipList : shipList) {
      if (aShipList.checkReachedPlanet()) {
        counter += 1;
      }
    }

    if (counter == shipList.size()) {
      allReached = true;
    }
    return allReached;
  }
}
