package creation;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ShipFleet {

	ArrayList<Ship> shipList;
	boolean allReached = false;
	
	
	public ShipFleet() {
		shipList = new ArrayList<Ship>();
	}
	
	public void addShips(int size) {
		for(int i = 0; i < size; i++) {
			Ship ship = new Ship();
			shipList.add(ship);
		}
	}
	
	public void drawStatusLabels(Graphics2D g2d, int size) {
		for(int i = 0; i < size; i++) {
			g2d.drawString(shipList.get(i).lblStatus.getText(), 15, 1200);			
		}
	}
	
	public boolean allReachedPlanets() {		
		int counter = 0;
		for(int i = 0; i < shipList.size(); i++) {
			if(shipList.get(i).checkReachedPlanet()) {
				counter += 1;
			}
		}
		
		if(counter == shipList.size()) {
			allReached = true;
			return allReached;
		} else {
			return allReached;
		}
	}
}
