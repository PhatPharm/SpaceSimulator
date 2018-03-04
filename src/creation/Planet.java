package creation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

public class Planet extends CelestialBody {

  int orbitDiameter;
  double speed = 0.01;
  double trueSpeed = 0.01;
  String nameOfPlanet;
  int chanceOfMoon;
  Moon moon;
  int pX;
  int pY;
  boolean hasMoon;
  private Random r = new Random();
  private int type = r.nextInt(PlanetType
      .values().length);    //pick a random									//D: planetoids (asteroids/small moons)
  private PlanetType planetType = PlanetType
      .values()[type];    //planet type										//EFG: proto-Earth sized planets
  private double age;                                                //H: harsh desert world
  private Color planetColor;                                            //M: Earth-like planet
  private double angle;

  //N: barren, rocky, high temp
  Planet() {                                                //O: ocean planet
    mass = r.nextInt(1000);                                        //P: water-ice planet
    gravity = mass
        / 20;                                          //Q: continually changing environments (peculiar orbit due to variable star)
    sizeDiameter = r.nextInt((int) Display.width / 60 - 20)
        + 20;                      //R: rogue planet body, does not orbit around anything
    int ageMultiplier = r
        .nextInt(10000);
    age = r.nextDouble()
        * ageMultiplier;                                //S: gas giant size between J and T
    angle = r.nextInt(360);                                        //T: largest size gas giant
    nameOfPlanet = createName();
    moon = new Moon();
    Random moonChance = new Random();
    chanceOfMoon = moonChance.nextInt(9);

    int orbitDirection = r.nextInt(10);
    if (orbitDirection > 5) {
      speed *= -1;
    }

  }

  public void createCircle(Graphics2D g2d, int orbitD, double oX, double oY, int planetD, Color c,
      Color s, String str, boolean hasMoon) {

    pX = calculateAngleXcoor(orbitD, (int) oX);
    pY = calculateAngleYcoor(orbitD, (int) oY);

    //allows for rotation//
    angle += speed;
    if (angle > 360) {
      angle = 0;
    }

    int drawX = convertToDraw(pX, planetD);
    int drawY = convertToDraw(pY, planetD);

    //orbits
    int drawXOfOrbit = (int) (oX - convertToRadius(orbitD));
    int drawYOfOrbit = (int) (oY - convertToRadius(orbitD));

    //draw orbit circle
    //Set the color of orbit to star color: g2d.setColor(s);
    g2d.setColor(s);
    g2d.drawOval(drawXOfOrbit, drawYOfOrbit, orbitD, orbitD);

    g2d.setColor(c);
    g2d.fillOval(drawX, drawY, planetD, planetD);

    //gray outline of the planet
    Color outline = new Color(169, 169, 169, 127);
    g2d.setColor(outline);
    g2d.drawOval(drawX, drawY, planetD, planetD);

    //set the color and position of the body label
    g2d.setColor(planetColor);
    g2d.setFont(new Font("Helvetica", Font.BOLD, 20));

    if (hasMoon) {
      g2d.drawString(str, drawX - 5, drawY - 20);
    } else {
      g2d.drawString(str, drawX - 5, drawY - 10);
    }

  }

  private int calculateAngleXcoor(int orbitD, int oX) {
    return (int) (Math.cos(angle) * convertToRadius(orbitD) + oX);
  }

  private int calculateAngleYcoor(int orbitD, int oY) {
    return (int) (Math.sin(angle) * convertToRadius(orbitD) + oY);
  }

  public PlanetType getPlanetType() {
    return planetType;
  }

  private void getAge() {
  }

  private void determineTemp() {
    //fahrenheit
    r.nextInt(100);
  }

  private void getTemperature() {
    determineTemp();
  }

  private Color determineColor() {
    if (planetType == PlanetType.A || planetType == PlanetType.B || planetType == PlanetType.C) {
      planetColor = new Color(255, 255, 255); //White
    }
    if (planetType == PlanetType.D) {
      planetColor = new Color(0, 165, 251); //Light blue
    }
    if (planetType == PlanetType.E || planetType == PlanetType.F || planetType == PlanetType.G) {
      planetColor = new Color(128, 0, 128); //Purple
    }
    if (planetType == PlanetType.H) {
      planetColor = new Color(255, 255, 0); //Yellow
    }
    if (planetType == PlanetType.I) {
      planetColor = new Color(0, 128, 0); //Green
    }
    if (planetType == PlanetType.J) {
      planetColor = new Color(255, 165, 0); //Orange
    }
    if (planetType == PlanetType.K) {
      planetColor = new Color(255, 0, 0); //Red
    }
    if (planetType == PlanetType.L) {
      planetColor = new Color(0, 153, 153); //sea green
    }
    if (planetType == PlanetType.M) {
      planetColor = new Color(128, 128, 128); //grey
    }
    if (planetType == PlanetType.N) {
      planetColor = new Color(255, 204, 229); //pink
    }
    if (planetType == PlanetType.O) {
      planetColor = new Color(128, 128, 0); //olive
    }
    if (planetType == PlanetType.P) {
      planetColor = new Color(139, 69, 19); //brown
    }
    if (planetType == PlanetType.Q) {
      planetColor = new Color(245, 222, 179); //wheat
    }
    if (planetType == PlanetType.R) {
      planetColor = new Color(21, 255, 186); //aqua
    }
    if (planetType == PlanetType.S) {
      planetColor = new Color(255, 0, 127); //dark pink
    }
    if (planetType == PlanetType.T) {
      planetColor = new Color(204, 225, 209); //
    }
    if (planetType == PlanetType.X || planetType == PlanetType.Y || planetType == PlanetType.Z) {
      planetColor = new Color(0, 255, 0); //lime
    }
    return planetColor;
  }

  public Color getColor() {
    planetColor = determineColor();
    return planetColor;
  }

  private String createName() {
    //String[] s = new String[nameLength];
    String[] s = new String[4];
    String[] vowel = {"a", "e", "i", "o", "u"};
    String[] consonant = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s",
        "t", "v", "w", "x", "z"};
    for (int i = 0; i < s.length; i++) {
      Random vr = new Random();
      Random cr = new Random();
      int vowelIndex = vr.nextInt(vowel.length);
      int consonantIndex = cr.nextInt(consonant.length);          //C,V,C,V
      if (i == 0) {
        s[i] = consonant[consonantIndex];
        s[i].toUpperCase();
      } else if (i == 1) {
        s[i] = vowel[vowelIndex];
      } else if (i == 2) {
        s[i] = consonant[consonantIndex];

      } else if (i == 3) {
        s[i] = vowel[vowelIndex];
      }
    }
    StringBuilder name = new StringBuilder();
    for (String value : s) {
      name.append(value);
    }
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  public void createMoon() {
    hasMoon = true;
  }

  public void getInfo() {
    getX();
    getY();
    getGravity();
    getMass();
    getDiameter();
    getPlanetType();
    getTemperature();
    getAge();
  }

  public enum PlanetType {A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X, Y, Z}

}	
