package creation;

import java.util.*;

public class CelestialBody {
	int x;
	int y;
	int sizeDiameter;
	double mass;
	double gravity;
	Random r = new Random();
	int centerX = this.getX();
	int centerY = this.getY();
	//boolean isOffscreen = false;
	int maxX = Display.width - 150;
	int minX = 150;
	int maxY = Display.height - 150;
	int minY = 150;
	int moveOffset = 5;
	int velocity;
	
	CelestialBody() {
		x = Display.width/2;//r.nextInt(maxX - minX) + minX;
		y = Display.height/2 - 25;//r.nextInt(Display.height);//r.nextInt(maxY - minY) + minY;
	}
	
	public int convertToRadius(int d) {
		//takes a diameter and converts it to radius
		int r = d/2;
		return r;
	}
	
	public int convertToDiameter(int r) {
		//takes a radius and converts it to diameter
		int d = r * 2;
		return d;
	}
	
	public int convertToDraw(int centerCoordinate, int diameter) {
		//takes the x coordinate of the oval and convert x coordinate to center
		//size is in diameter
		int drawCoordinate = centerCoordinate - convertToRadius(diameter);
		return drawCoordinate;
	}
	
	public int convertToCenter(int drawCoordinate, int diameter) {
		int centerCoordinate = drawCoordinate + convertToRadius(diameter);
		return centerCoordinate;
	}
	
	public int setNewX(int centerX) {
//		centerX = r.nextInt(Display.width);
		return centerX;
	}
	
	public int setNewY() {
		centerY = r.nextInt(Display.height);
		return centerY;
	}
	
	public int getX() {
		return convertToCenter(x, this.getDiameter());
	}
	
	public int getY() {
		return convertToCenter(y, this.getDiameter());
	}
	
	public double getGravity() {
		return gravity;
	}
	
	public double getMass() {
		return mass;
	}
	
	public int getDiameter() {
		return sizeDiameter;
	}
	
}
