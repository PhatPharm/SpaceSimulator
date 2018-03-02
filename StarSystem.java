package creation;

import java.util.ArrayList;
import java.util.Random;

public class StarSystem {

	Planet planet;
	Random random = new Random();
	int numPlanets = random.nextInt(10) + 2;
		
	public ArrayList<Planet> createSystem() {
		ArrayList<Planet> planets = new ArrayList<Planet>();
		for(int i = 0; i < numPlanets; i++) {
			planet = new Planet();
			boolean overlap = false;
			int planetOffset = 10;
			planets.add(planet);
			for(Planet other : planets) {
				if(planet != other) {
					int distance = (int) Math.sqrt(Math.pow((planet.getX()-other.getX()), 2) + Math.pow((planet.getY()-other.getY()), 2));
					if(distance < (planet.convertToRadius(planet.getDiameter()) + other.convertToRadius(other.getDiameter())) + planetOffset) {
						overlap = true;
						if(overlap) {
							//planets.remove(planet);
							//System.out.println("Planet removed");
							break;
						}
					}
				} 
			}
			//planets.add(planet);
		}
		return planets;
	}
	
	public int getNumOfPlanets() {
		return this.numPlanets;
	}
}
