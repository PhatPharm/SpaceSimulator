package creation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Moon extends CelestialBody{
	
	double angle = Math.random()*Math.PI*2;
	int orbitDiameter;
	int orbitDirection = r.nextInt(10);
	int sizeDiameter;
	int drawXOfOrbit;
	int drawYOfOrbit;
	int drawX;
	int drawY;
	int pX;
	int pY;
	
	double speed = 0.1;
	
	Color moonColor;
	Color planetColor;
	
	final String[] str = {"a", "b", "c", "d","e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t","u", "v", "w", "x", "z"};
	int randLet;
	
	Moon() {
		sizeDiameter = 15;
		moonColor = new Color(169,169,169);
		orbitDiameter = 50;
		randLet = r.nextInt(str.length);
		
		if(orbitDirection > 5) {
			speed *= -1;
		}
	}

	public void createCircle(Graphics2D g2d, int orbitD, int oX, int oY, int moonD, Color m, Color p) {
		
		pX = calculateAngleXcoor(orbitD, oX);
		pY = calculateAngleYcoor(orbitD, oY);
		
		angle += speed;
		
		drawX = convertToDraw(pX, moonD);
		drawY = convertToDraw(pY, moonD);
		
		//orbits
		drawXOfOrbit = oX - convertToRadius(orbitD);
		drawYOfOrbit = oY - convertToRadius(orbitD);		
		
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
	
	public int calculateAngleXcoor(int orbitD, int oX) {
		int angleX = (int) (Math.cos(angle) * convertToRadius(orbitD) + oX);
		return angleX;
	}
	
	public int calculateAngleYcoor(int orbitD, int oY) {
		int angleY = (int) (Math.sin(angle) * convertToRadius(orbitD) + oY);
		return angleY;
	}
	
	public Color determineMoonColor() {
		
		moonColor = new Color(0,0,0,0);
		return moonColor;
	}
}
