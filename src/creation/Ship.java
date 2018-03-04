package creation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;


public class Ship {

  //using Graphics2D Polygon class, which takes an array of x coordinates, an array of y coordinates, and a number of total points.

  private int[] xcoors = new int[3];
  private int[] ycoors = new int[3];
  int speed = 10;

  private int totalPoints;
  Planet target;

  private boolean reachedPlanet = false;

  private String shipName;
  private String targetName;
  JLabel lblStatus;

  private Random r = new Random();

  private int ColorR = r.nextInt(255);
  private int ColorG = r.nextInt(255);
  private int ColorB = r.nextInt(255);

  //boolean moveRight = true;

  Ship() {
    initShip();
  }

  private void initShip() {

    //tip of triangle: randomNum, randomNumber)
    //randomNum+5
    //other sides (10, 35) (20, 35)

    xcoors[0] = r.nextInt(2000);
    xcoors[1] = xcoors[0] + 5;
    xcoors[2] = xcoors[1] + 5;

    ycoors[0] = r.nextInt(1350);
    ycoors[1] = ycoors[0] - 15;  //change - to + to make the ship face down
    ycoors[2] = ycoors[0];

    totalPoints = 3;

  }

  public void setStatusLabel(Planet planet) {

    //fleet handles the position of the label on screen
    lblStatus = new JLabel(
        "Ship " + shipName + ": " + planet.nameOfPlanet + " Arrived: " + checkReachedPlanet());

  }

  public void visitPlanets(ArrayList<Planet> planets) {

    target = planets.get(0);

    if (xcoors[1] < target.pX) {
      xcoors[0] += speed;
      xcoors[1] += speed;
      xcoors[2] += speed;
    }

    if (xcoors[1] > target.pX) {
      xcoors[0] -= speed;
      xcoors[1] -= speed;
      xcoors[2] -= speed;
    }

    if (ycoors[1] < target.pY) {
      ycoors[0] += speed;
      ycoors[1] += speed;
      ycoors[2] += speed;
    }

    if (ycoors[1] > target.pY) {
      ycoors[0] -= speed;
      ycoors[1] -= speed;
      ycoors[2] -= speed;
    }

    if (xcoors[1] == target.pX && ycoors[1] == target.pY) {
      reachedPlanet = true;
    }
  }

  public void visitPlanets(Planet planet) {
    //targetName = target.nameOfPlanet;
    //	for(int i = 0; i < planets.size(); i++) {
    target = planet;

    if (xcoors[1] < target.pX) {
      xcoors[0] += speed;
      xcoors[1] += speed;
      xcoors[2] += speed;
    }

    if (xcoors[1] > target.pX) {
      xcoors[0] -= speed;
      xcoors[1] -= speed;
      xcoors[2] -= speed;
    }

    if (ycoors[1] < target.pY) {
      ycoors[0] += speed;
      ycoors[1] += speed;
      ycoors[2] += speed;
    }

    if (ycoors[1] > target.pY) {
      ycoors[0] -= speed;
      ycoors[1] -= speed;
      ycoors[2] -= speed;
    }

    //checking if the ship reached the planet.
    setReachedPlanet();
    if (checkReachedPlanet()) {
      xcoors[1] = target.pX;
      ycoors[1] = target.pY;
      setLandedCoordinates(xcoors[1], ycoors[1]);
    }
  }

  private void setReachedPlanet() {
    if ((xcoors[1] > (target.pX - target.sizeDiameter / 2) && xcoors[1] < (target.pX
        + target.sizeDiameter / 2)) && (ycoors[1] > (target.pY - target.sizeDiameter / 2)
        && ycoors[1] < (target.pY + target.sizeDiameter / 2))) {
      reachedPlanet = true;
    }
  }


  //keep the shape the same size//
  private void setLandedCoordinates(int xcoor, int ycoor) {
    xcoors[0] = xcoor - 10;
    xcoors[2] = xcoor + 5;

    ycoors[0] = ycoor - 15;
    ycoors[2] = ycoor;

  }

  public boolean checkReachedPlanet() {
    setReachedPlanet();
    return reachedPlanet;
  }

  public String getTargetPlanetName() {
    return targetName;
  }

  public void drawShip(Graphics2D g2d) {

    Color shipColor = new Color(ColorR, ColorG, ColorB);

    g2d.setColor(shipColor);
    Polygon triangle = new Polygon(xcoors, ycoors, totalPoints);
    g2d.fill(triangle);
    g2d.setColor(Color.BLACK);
    g2d.draw(triangle);

    g2d.setFont(new Font("Helvetica", Font.PLAIN, 15));
    //g2d.drawString(getTargetPlanetName(), xcoors[0]-15, ycoors[0]+15);

  }

}
