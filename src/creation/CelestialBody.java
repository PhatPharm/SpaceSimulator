package creation;

import java.util.Random;

public class CelestialBody {

  int x;
  int y;
  int sizeDiameter;
  double mass;
  double gravity;
  Random r = new Random();
  int centerX = this.getX();
  int centerY = this.getY();
  int velocity;

  CelestialBody() {
    x = Display.width / 2;//r.nextInt(maxX - minX) + minX;
    y = Display.height / 2 - 25;//r.nextInt(Display.height);//r.nextInt(maxY - minY) + minY;
  }

  public int convertToRadius(int d) {
    //takes a diameter and converts it to radius
    return d / 2;
  }

  int convertToDraw(int centerCoordinate, int diameter) {
    //takes the x coordinate of the oval and convert x coordinate to center
    //size is in diameter
    return centerCoordinate - convertToRadius(diameter);
  }

  private int convertToCenter(int drawCoordinate, int diameter) {
    return drawCoordinate + convertToRadius(diameter);
  }

  public int getX() {
    return convertToCenter(x, this.getDiameter());
  }

  public int getY() {
    return convertToCenter(y, this.getDiameter());
  }

  double getGravity() {
    return gravity;
  }

  double getMass() {
    return mass;
  }

  public int getDiameter() {
    return sizeDiameter;
  }

}
