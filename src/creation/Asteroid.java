package creation;

import java.util.Random;


public class Asteroid extends CelestialBody {

  private Random r = new Random();
  private int velocity = r.nextInt(21) + 5;
  int x;
  int y;
  int size;

  public Asteroid() {
    x = 0;
    y = r.nextInt(Display.height);
    Random sizeR = new Random();
    size = sizeR.nextInt(35 + 1 - 10) + 10; //min = 10, max = 35;

  }

  public void move() {
    if (x < Display.width || y < Display.height) {
      x += velocity;
      y += velocity;
    }
  }
}
