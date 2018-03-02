package creation;

import java.util.*;

import javax.swing.JLabel;

import java.awt.*;

public class Star extends CelestialBody {
	
	Random r = new Random();
	public enum SpectralType{ O, B, A, F, G, K, M };
	int spec = r.nextInt(SpectralType.values().length);			//pick a random
	SpectralType spectralType = SpectralType.values()[spec];    //spectral type
	double age;
	int ageMultiplier = r.nextInt(1000000);
	int children;
	int temperature;
	Graphics2D g;
	Color starColor;
	int starNumber;
	int orbitDiameter;			
	int oX = this.getX();			//returns center x of orbit path
	int oY = this.getY();			//returns center y of orbit path
	Star other;
	ArrayList<Planet> planetList;
	String nameOfStar;
	
	Star() {
		mass = r.nextInt(10000);
		gravity = mass/20;
		sizeDiameter = r.nextInt((int)Display.width/15 - 100) + 100; //between 50 and width
		age = r.nextDouble()* ageMultiplier;
		children = r.nextInt(15);
		orbitDiameter = (int) (sizeDiameter * 1.7);
		planetList = createSystem();
		nameOfStar = createName();
	}
	
	public boolean checkOverlap(CelestialBody A, CelestialBody B) {
		 boolean isOverlapping = true;
			if((int) Math.sqrt(Math.pow((A.getX()-B.getX()), 2) + Math.pow((A.getY()-B.getY()), 2)) < (A.convertToRadius(A.getDiameter() + B.convertToRadius(B.getDiameter())))) {
				return isOverlapping;
			}
			else{
				isOverlapping = false;
				return isOverlapping;
			}
		}

	public void createCircle(Graphics2D g2d, int sD, Color c, String str) {
		g2d.setColor(c);
		
		//calculate box x and y coords of star
		int drawXOfStar = convertToDraw(oX, sD);
		int drawYOfStar = convertToDraw(oY, sD);

		//draw star
		g2d.fillOval(drawXOfStar, drawYOfStar, sD, sD);
		Color outline = new Color(169,169,169,127);
		g2d.setColor(outline);
		g2d.drawOval(drawXOfStar, drawYOfStar, sD, sD);
		
		//calculate box x and y coords of orbit path
			
		//draw orbit circle
		//g2d.drawOval(drawXOfOrbit, drawYOfOrbit, oD, oD);
		//g2d.setColor(starColor);
		g2d.setColor(starColor);
		g2d.setFont(new Font("Helvetica", Font.PLAIN, 15));
		g2d.drawString(str, drawXOfStar, drawYOfStar-10);
	}
	
	public SpectralType getSpectralType() {
		return spectralType;
	}
	
	public double getAge() {
		return age;
	}
	
	public int getChildren() {
		return children;
	}
	
	public int determineTemp() {
		if(spectralType == SpectralType.O) {
			temperature = r.nextInt(60000-30000) + 30000; //between 30,000 and 60,000
		}
		if(spectralType == SpectralType.B) {
			temperature = r.nextInt(30000-10000) + 10000; //between 10000 and 30000
		}
		if(spectralType == SpectralType.A) {
			temperature = r.nextInt(10000-7500) + 7500; //between 7500 and 10000
		}
		if(spectralType == SpectralType.F) {
			temperature = r.nextInt(7500-6000) + 6000; //between 6000 and 7500
		}
		if(spectralType == SpectralType.G) {
			temperature = r.nextInt(6000-5000) + 5000; //between 5000 and 6000
		}
		if(spectralType == SpectralType.K) {
			temperature = r.nextInt(5000-3500) + 3500; //between 3500 and 5000
		}
		if(spectralType == SpectralType.M) {
			temperature = r.nextInt(3500); //under 3500
		}
		return temperature;
	}
	
	public int getTemperature() {
		temperature = determineTemp();
		return temperature;
	}
	
	public Color determineColor() {
		if(spectralType == SpectralType.O) {
			starColor = new Color(255, 255, 255, 127); //White
		}
		if(spectralType == SpectralType.B) {
			starColor = new Color(0, 165, 251, 127); //Light blue
		}
		if(spectralType == SpectralType.A) {
			starColor = new Color(200, 0, 150, 127); //Pinkish-Purple
		}
		if(spectralType == SpectralType.F) {
			starColor = new Color(0, 128, 0, 127); //Green
		}
		if(spectralType == SpectralType.G) {
			starColor = new Color(255, 255, 0, 127); //Yellow
		}
		if(spectralType == SpectralType.K) {
			starColor = new Color(255, 165, 0, 127); //Orange
		}
		if(spectralType == SpectralType.M) {
			starColor = new Color(255, 0, 0, 127); //Red
		}
		return starColor;
	}
	
	public Color getColor() {
		starColor = determineColor();
		return starColor;
	}
	
	public int getOrbitDiameter() {
		return orbitDiameter;
	}
	
	public ArrayList<Planet> createSystem() {
		StarSystem sys = new StarSystem();
		ArrayList<Planet> planetList = sys.createSystem();
		for(int i = 0; i < planetList.size(); i++) {
			int planetRealNum = i+1;
			planetList.get(i).orbitDiameter = (int)(this.sizeDiameter + (planetRealNum * this.orbitDiameter)/1.5);
			if(i == 0) {
				planetList.get(i).trueSpeed = planetList.get(i).speed;
			} else {
				planetList.get(i).trueSpeed = planetList.get(i).speed/(i);
			}	
		}
		return planetList;
	}
	
	
	public String createName() {

		String[] s = new String[6];
		String[] vowel = {"a", "e", "i", "o", "u"};
		String[] consonant = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t","v", "w", "x", "z"};
		for(int i = 0; i < s.length; i++) {
			Random vr = new Random();
			Random cr = new Random();
			int vowelIndex = vr.nextInt(vowel.length);
			int consonantIndex = cr.nextInt(consonant.length); 					//C,V,C,C,V,C
			if(i == 0) {
				s[i] = consonant[consonantIndex];
				s[i].toUpperCase();
			} else if(i == 1) {
				s[i] = vowel[vowelIndex];
			} else if(i == 2) {
				s[i] = consonant[consonantIndex];
				
			} else if(i == 3) {
				s[i] = consonant[consonantIndex];
			} else if(i == 4) {
				s[4] = vowel[vowelIndex];
			} else if(i == 5) {
				s[5] = consonant[consonantIndex];
				if(s[5] == "y" || s[5] == "q") {
					int newConsonantIndex = cr.nextInt(20);
					s[5] = consonant[newConsonantIndex];
				}
			}
		}
		String name = "";
		
		for(int j = 0; j < s.length; j++) {
			name += s[j];
		}
		String starName = name.substring(0, 1).toUpperCase() + name.substring(1);
		return starName;
	}
	
	public void getInfo() {
		getX();
		getY();
		getGravity();
		getMass();
		getDiameter();
		getSpectralType();
		getTemperature();
		getAge();
		getChildren();
	}
}
