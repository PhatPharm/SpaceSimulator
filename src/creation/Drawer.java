package creation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import space.Asteroid;
import space.AsteroidCluster;
import space.Galaxy;
import space.ShipFleet;
import space.Star;
import space.Universe;

@SuppressWarnings("serial")
public class Drawer extends JComponent {

  private boolean justStarted = true;
  private boolean starClicked = false;
  private int cumulativeTime = 0;
  private ShipFleet fleet;
  private ArrayList<Galaxy> universe;// = createUniverse();
  private ArrayList<Star> stars;// = createGalaxy();
  private JLabel starName;
  private JButton pauseBtn;
  private boolean isPaused = false;
  private String planetStr;

  private Calendar calendar = new Calendar();
  private JLabel lblDate = new JLabel();

  private Display display;

  Drawer(Display d) {
    display = d;
    universe = createUniverse();
    stars = createGalaxy();
    setNameLabel(stars.get(0));
    ArrayList<Asteroid> cluster = createCluster();
    AffineTransform transform = new AffineTransform();
    AffineTransform rotationTransform = new AffineTransform();
    fleet = createFleet();
  }

  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    //This displays all stars in the galaxy
    if (justStarted) {      //only goes once
      initPaint(g2d);
      justStarted = false;
    }
    initAst();
    update(g2d);
  }

  private void determineShipTargets() {
    Random rShipTarget = new Random();
    for (int i = 0; i < fleet.shipList.size(); i++) {
      int planetIndex = rShipTarget.nextInt(stars.get(0).planetList.size() - 1);
      fleet.shipList.get(i).target = stars.get(0).planetList.get(planetIndex);
      fleet.shipList.get(i).setStatusLabel(stars.get(0).planetList.get(planetIndex));
    }
  }


  private void displayShips(Graphics2D g2d) {
    for (int i = 0; i < fleet.shipList.size(); i++) {
      fleet.shipList.get(i).drawShip(g2d);
    }
  }

  private void initAst() {

  }


  private void initPaint(Graphics2D g2d) {
//		lblDate.setBounds(50, 1000, 400, 40);
    for (int galaxy = 0; galaxy < universe.size(); galaxy++) {
      for (Star star1 : stars) {
        star1.createSystem();
        String starStr =
            star1.nameOfStar + " | Planets: " + star1.planetList.size();
        star1
            .createCircle(g2d, star1.getDiameter(), star1.getColor(), starStr);
        for (int planet = 0; planet < star1.planetList.size(); planet++) {
          planetStr = star1.planetList.get(planet).nameOfPlanet;

          doSomeMoonThing(g2d, star1, planet, planetStr);
        }
      }
    }
    //fleet.drawStatusLabels(g2d, stars.get(0).planetList.size()-1);
    determineShipTargets();
    displayShips(g2d);

    if (isPaused) {
      pauseBtn.setText("Resume");
    } else {
      pauseBtn.setText("Pause");
    }
    g2d.dispose();
  }

  private ShipFleet createFleet() {
    ShipFleet fleet = new ShipFleet();
    fleet.addShips(stars.get(0).planetList.size());
    return fleet;
  }

  private ArrayList<Galaxy> createUniverse() {
    Universe uni = new Universe();
    return uni.createUniverse();
  }

  private ArrayList<Star> createGalaxy() {
    Galaxy gal = new Galaxy();
    return gal.createGalaxy();
  }

  private ArrayList<Asteroid> createCluster() {
    AsteroidCluster cluster = new AsteroidCluster();
    return cluster.createCluster();
  }

  private void setNameLabel() {
    int fontsize = 30;
    Color c = stars.get(0).getColor();
    for (Star star1 : stars) {

      //every time you hit the button, add the system to the Galaxy, then change this JLabel to the next star's name.

      starName = new JLabel("The " + star1.nameOfStar + " System", JLabel.CENTER);
    }
    starName.setBounds(50, 100, 400, fontsize + 20);
    starName.setFont(new Font("Helvetica", Font.PLAIN, fontsize));
    starName.setForeground(c);
    display.add(starName);
  }

  private void setDateLabel(String day, int week, int year) {
    String lblDateText = day + ", Week: " + week + " Year: " + year;
    lblDate.setText(lblDateText);
    lblDate.setLocation(50, 1000);
//		lblDate.setBounds(50, 1000, 400, 40);
    lblDate.setFont(new Font("Helvetica", Font.BOLD, 20));
    lblDate.setForeground(Color.WHITE);
    lblDate.setVisible(true);
    display.add(lblDate);
  }

  private void clearDateLabel() {
    lblDate.setText("");
  }

  private void setNameLabel(Star newStar) {
    int fontsize = 30;
    Color c = newStar.getColor();
    for (int star = 0; star < stars.size(); star++) {

      //every time you hit the button, add the system to the Galaxy, then change this JLabel to the next star's name.

      starName = new JLabel("The " + planetStr + " System", JLabel.CENTER);
    }
    starName.setBounds(50, 100, 400, fontsize + 20);
    starName.setFont(new Font("Helvetica", Font.PLAIN, fontsize));
    starName.setVisible(true);
    starName.setForeground(c);
  }

  public JButton resetStar() {
    //Graphics2D g2d = (Graphics2D) g;
    JButton resetBtn = new JButton();
    resetBtn.setFont(new Font("Helvetica", Font.BOLD, 20));
    resetBtn.setText("New System");
    resetBtn.setBounds(1700, 1100, 200, 100);
    resetBtn.addActionListener(e -> {
      universe = createUniverse();
      stars = createGalaxy();
//				starName = setNameLabel(stars.get(0));
      starName.setVisible(true);
      fleet = createFleet();
    });
    return resetBtn;
  }

  public JButton pause() {
    pauseBtn = new JButton();
    pauseBtn.setFont(new Font("Helvetica", Font.BOLD, 20));
    pauseBtn.setText("Pause");
    pauseBtn.setBounds(1490, 1100, 200, 100);
    pauseBtn.addActionListener(e -> isPaused = !isPaused);
    return pauseBtn;
  }

  public boolean clickedStar(int x, int y) {

    int leftEdge = Display.width / 2 - stars.get(0).getDiameter() / 2;
    int rightEdge = Display.width / 2 + stars.get(0).getDiameter() / 2;
    int topEdge = Display.height / 2 + stars.get(0).getDiameter() / 2;
    int bottomEdge = Display.height / 2 - stars.get(0).getDiameter() / 2;
    if (x < rightEdge && x > leftEdge && y < topEdge && y > bottomEdge) {
      starClicked = true;
      StarInformationPanel sPanel = new StarInformationPanel(2000 / 2, 500, 500);
      sPanel.setInfo(stars.get(0));
      sPanel.setAlwaysOnTop(true);
      sPanel.setVisible(true);
    }
    return starClicked;
  }


  public void togglePause() {
    isPaused = !isPaused;
  }

  private void update(Graphics2D g2d) {
//		lblDate.setBounds(50, 1000, 400, 40);
    lblDate.setLocation(50, 1000);
    clearDateLabel();
    setNameLabel();
    if (isPaused) {

      //change speed of ships to ZERO
      for (int i = 0; i < fleet.shipList.size(); i++) {
        fleet.shipList.get(i).speed = 0;
      }

      //change speed of planets and moons to ZERO
      for (Star star1 : stars) {
        for (int planet = 0; planet < star1.planetList.size(); planet++) {
          if (star1.planetList.get(planet).chanceOfMoon < 4) {
            star1.planetList.get(planet).speed = 0;
            star1.planetList.get(planet).moon.speed = 0;
          } else {
            star1.planetList.get(planet).speed = 0;
          }
        }
      }
    } else {
      //slow down calendar movement (this will update advanceTime every sec
      int threadTime = 30;
      cumulativeTime += threadTime;
      if (cumulativeTime > 300) {
        cumulativeTime = 0;
        calendar.advanceTime(); //need to update the jlabel ;)
        System.out.println(
            calendar.getCurrentDay() + " Weeks: " + calendar.getCurrentWeek() + " Years: "
                + calendar.getCurrentYear());
      }

      //change speed of ships back to original
      for (int i = 0; i < fleet.shipList.size(); i++) {
        fleet.shipList.get(i).speed = 10;
      }

      //change speed of planets and moons back to original
      for (Star star1 : stars) {
        for (int planet = 0; planet < star1.planetList.size(); planet++) {
          if (star1.planetList.get(planet).chanceOfMoon < 4) {
            star1.planetList.get(
                planet).speed = 0.01;          //not gonna work because the planets have different speeds depending
            star1.planetList
                .get(planet).moon.speed = 0.1;        //on their order in the system.....
          } else {
            star1.planetList.get(planet).speed = 0.01;
          }
        }
      }
    }

    try {
//			Thread.sleep(threadTime);
      Thread.sleep(1);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    //add the ship names (numbers to bottom left of screen);
    //put their reachedPlanet boolean next to it

    //fleet.drawStatusLabels(g2d, stars.get(0).planetList.size());

    for (Star star1 : stars) {
//			if(starClicked) {
//				g2d.setColor(Color.YELLOW);
//				g2d.drawOval(stars.get(0).getX(), stars.get(0).getY(), stars.get(0).getDiameter(), stars.get(0).getDiameter());
//			}
      String starStr =
          star1.nameOfStar + " | Planets: " + star1.planetList.size();
      star1
          .createCircle(g2d, star1.getDiameter(), star1.getColor(), starStr);
      for (int planet = 0; planet < star1.planetList.size(); planet++) {
        String planetStr = star1.planetList.get(planet).nameOfPlanet;

        doSomeMoonThing(g2d, star1, planet, planetStr);
      }
    }
    displayShips(g2d);

    for (int i = 0; i < fleet.shipList.size(); i++) {

      //this will actually set the targets to each planet going down the list, not a random planet.

      fleet.shipList.get(i).visitPlanets(stars.get(0).planetList.get(i));
//				System.out.println("Ships all reached target and got new targets");
    }

    if (fleet.allReachedPlanets()) {
      determineShipTargets();
    }

    if (isPaused) {
      pauseBtn.setText("Resume");
    } else {
      pauseBtn.setText("Pause");
    }
    setDateLabel(calendar.getCurrentDay(), calendar.getCurrentWeek(), calendar.getCurrentYear());
    repaint();
  }

  /**
   * Extracted duplicate code from multiple methods I actually don't know what this does
   */
  private void doSomeMoonThing(Graphics2D g2d, Star star1, int planet, String planetStr) {
    if (star1.planetList.get(planet).chanceOfMoon < 4) {
      star1.planetList.get(planet)
          .createCircle(g2d, star1.planetList.get(planet).orbitDiameter,
              star1.oX, star1.oY,
              star1.planetList.get(planet).getDiameter(),
              star1.planetList.get(planet).getColor(), star1.starColor,
              planetStr + ": " + star1.planetList.get(planet).getPlanetType(), true);
      star1.planetList.get(planet).createMoon();
      star1.planetList.get(planet).moon
          .createCircle(g2d, star1.planetList.get(planet).getDiameter() * 2,
              star1.planetList.get(planet).pX,
              star1.planetList.get(planet).pY,
              star1.planetList.get(planet).moon.sizeDiameter,
              star1.planetList.get(planet).moon.moonColor,
              star1.planetList.get(planet).getColor());
    } else {
      star1.planetList.get(planet)
          .createCircle(g2d, star1.planetList.get(planet).orbitDiameter,
              star1.oX, star1.oY,
              star1.planetList.get(planet).getDiameter(),
              star1.planetList.get(planet).getColor(), star1.starColor,
              planetStr + ": " + star1.planetList.get(planet).getPlanetType(), false);
    }
  }

}
