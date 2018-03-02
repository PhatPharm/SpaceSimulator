package creation;

import java.util.ArrayList;
import java.util.Random;

public class Universe {
	
	Random r = new Random();
	int numGalaxies = 1;// r.nextInt(6-1) + 1; //between 1 and 6
	
	public ArrayList<Galaxy> createUniverse() {
		ArrayList<Galaxy> galaxies = new ArrayList<Galaxy>();
		for(int i = 0; i < numGalaxies; i++) {
			Galaxy galaxy = new Galaxy();
			galaxies.add(galaxy);
		}
		return galaxies;
	}
}
