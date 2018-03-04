package creation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Star extends CelestialBody {

  Graphics2D g;
  Color starColor;
  int oX = this.getX();      //returns center x of orbit path
  int oY = this.getY();      //returns center y of orbit path
  ArrayList<Planet> planetList;
  String nameOfStar;
  private Random r = new Random();
  private int spec = r.nextInt(SpectralType.values().length);      //pick a random
  private SpectralType spectralType = SpectralType.values()[spec];    //spectral type
  private double age;
  private int children;
  private int temperature;
  private int orbitDiameter;

  Star() {
    mass = r.nextInt(10000);
    gravity = mass / 20;
    sizeDiameter = r.nextInt(Display.width / 15 - 100) + 100; //between 50 and width
    int ageMultiplier = r.nextInt(1000000);
    age = r.nextDouble() * ageMultiplier;
    children = r.nextInt(15);
    orbitDiameter = (int) (sizeDiameter * 1.7);
    planetList = createSystem();
    nameOfStar = createName();
  }

  public void createCircle(Graphics2D g2d, int sD, Color c, String str) {
    g2d.setColor(c);

    //calculate box x and y coords of star
    int drawXOfStar = convertToDraw(oX, sD);
    int drawYOfStar = convertToDraw(oY, sD);

    //draw star
    g2d.fillOval(drawXOfStar, drawYOfStar, sD, sD);
    Color outline = new Color(169, 169, 169, 127);
    g2d.setColor(outline);
    g2d.drawOval(drawXOfStar, drawYOfStar, sD, sD);

    //calculate box x and y coords of orbit path

    //draw orbit circle
    //g2d.drawOval(drawXOfOrbit, drawYOfOrbit, oD, oD);
    //g2d.setColor(starColor);
    g2d.setColor(starColor);
    g2d.setFont(new Font("Helvetica", Font.PLAIN, 15));
    g2d.drawString(str, drawXOfStar, drawYOfStar - 10);
  }

  private void getSpectralType() {
  }

  private void getAge() {
  }

  private void getChildren() {
  }

  private int determineTemp() {
    if (spectralType == SpectralType.O) {
      temperature = r.nextInt(60000 - 30000) + 30000; //between 30,000 and 60,000
    }
    if (spectralType == SpectralType.B) {
      temperature = r.nextInt(30000 - 10000) + 10000; //between 10000 and 30000
    }
    if (spectralType == SpectralType.A) {
      temperature = r.nextInt(10000 - 7500) + 7500; //between 7500 and 10000
    }
    if (spectralType == SpectralType.F) {
      temperature = r.nextInt(7500 - 6000) + 6000; //between 6000 and 7500
    }
    if (spectralType == SpectralType.G) {
      temperature = r.nextInt(6000 - 5000) + 5000; //between 5000 and 6000
    }
    if (spectralType == SpectralType.K) {
      temperature = r.nextInt(5000 - 3500) + 3500; //between 3500 and 5000
    }
    if (spectralType == SpectralType.M) {
      temperature = r.nextInt(3500); //under 3500
    }
    return temperature;
  }

  public int getTemperature() {
    temperature = determineTemp();
    return temperature;
  }

  private Color determineColor() {
    if (spectralType == SpectralType.O) {
      starColor = new Color(255, 255, 255, 127); //White
    }
    if (spectralType == SpectralType.B) {
      starColor = new Color(0, 165, 251, 127); //Light blue
    }
    if (spectralType == SpectralType.A) {
      starColor = new Color(200, 0, 150, 127); //Pinkish-Purple
    }
    if (spectralType == SpectralType.F) {
      starColor = new Color(0, 128, 0, 127); //Green
    }
    if (spectralType == SpectralType.G) {
      starColor = new Color(255, 255, 0, 127); //Yellow
    }
    if (spectralType == SpectralType.K) {
      starColor = new Color(255, 165, 0, 127); //Orange
    }
    if (spectralType == SpectralType.M) {
      starColor = new Color(255, 0, 0, 127); //Red
    }
    return starColor;
  }

  public Color getColor() {
    starColor = determineColor();
    return starColor;
  }

  public ArrayList<Planet> createSystem() {
    StarSystem sys = new StarSystem();
    ArrayList<Planet> planetList = sys.createSystem();
    for (int i = 0; i < planetList.size(); i++) {
      int planetRealNum = i + 1;
      planetList.get(i).orbitDiameter = (int) (this.sizeDiameter
          + (planetRealNum * this.orbitDiameter) / 1.5);
      if (i == 0) {
        planetList.get(i).trueSpeed = planetList.get(i).speed;
      } else {
        planetList.get(i).trueSpeed = planetList.get(i).speed / (i);
      }
    }
    return planetList;
  }

  public String createName() {

    String[] s = new String[6];
    String[] vowel = {"a", "e", "i", "o", "u"};
    String[] consonant = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s",
        "t", "v", "w", "x", "z"};
    for (int i = 0; i < s.length; i++) {
      Random vr = new Random();
      Random cr = new Random();
      int vowelIndex = vr.nextInt(vowel.length);
      int consonantIndex = cr.nextInt(consonant.length);          //C,V,C,C,V,C
      if (i == 0) {
        s[i] = consonant[consonantIndex];
        s[i].toUpperCase();
      } else if (i == 1) {
        s[i] = vowel[vowelIndex];
      } else if (i == 2) {
        s[i] = consonant[consonantIndex];

      } else if (i == 3) {
        s[i] = consonant[consonantIndex];
      } else if (i == 4) {
        s[4] = vowel[vowelIndex];
      } else if (i == 5) {
        s[5] = consonant[consonantIndex];
        if (s[5].equals("y") || s[5].equals("q")) {
          int newConsonantIndex = cr.nextInt(20);
          s[5] = consonant[newConsonantIndex];
        }
      }
    }
    StringBuilder name = new StringBuilder();

    for (String value : s) {
      name.append(value);
    }
    return name.substring(0, 1).toUpperCase() + name.substring(1);
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

  public enum SpectralType {O, B, A, F, G, K, M}
}
