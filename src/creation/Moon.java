package creation;

import java.awt.Color;
import java.awt.Graphics2D;

public class Moon extends CelestialBody {

  private double angle = Math.random() * Math.PI * 2;
  int sizeDiameter;

  double speed = 0.1;

  Color moonColor;

  Moon() {
    sizeDiameter = 15;
    moonColor = new Color(169, 169, 169);
    String[] str = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "p",
        "q", "r", "s", "t", "u", "v", "w", "x", "z"};
    r.nextInt(str.length);

    int orbitDirection = r.nextInt(10);
    if (orbitDirection > 5) {
      speed *= -1;
    }
  }

  public void createCircle(Graphics2D g2d, int orbitD, int oX, int oY, int moonD, Color m,
      Color p) {

    int pX = calculateAngleXcoor(orbitD, oX);
    int pY = calculateAngleYcoor(orbitD, oY);

    angle += speed;

    int drawX = convertToDraw(pX, moonD);
    int drawY = convertToDraw(pY, moonD);

    //orbits
    int drawXOfOrbit = oX - convertToRadius(orbitD);
    int drawYOfOrbit = oY - convertToRadius(orbitD);

    //draw orbit circle
    //Set the color of orbit to star color: g2d.setColor(s);
    g2d.setColor(p);
    g2d.drawOval(drawXOfOrbit, drawYOfOrbit, orbitD, orbitD);

    g2d.setColor(m);
    g2d.fillOval(drawX, drawY, moonD, moonD);

    //label
//		g2d.setFont(new Font("Helvetica", Font.BOLD, 20));
//		g2d.drawString(str[randLet], pX+15, pY+15);
  }

  private int calculateAngleXcoor(int orbitD, int oX) {
    return (int) (Math.cos(angle) * convertToRadius(orbitD) + oX);
  }

  private int calculateAngleYcoor(int orbitD, int oY) {
    return (int) (Math.sin(angle) * convertToRadius(orbitD) + oY);
  }

}
