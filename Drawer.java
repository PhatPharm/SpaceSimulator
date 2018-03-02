package creation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;

import java.util.Random;
import java.awt.geom.AffineTransform;

@SuppressWarnings("serial")
public class Drawer extends JComponent {

	int xAnc;
	int yAnc;
	Graphics g;
	
	
	boolean starClicked = false;
	
	int galRealNum = 0;
	int starRealNum = 0;
	int planetRealNum = 0;
	
	int threadTime = 30;
	int cumulativeTime = 0;
	
	Thread calendarThread = new Thread();
	
	ShipFleet fleet;
	
	Random rShipTarget;
	boolean isOverlapping;
	boolean Running = true;
	ArrayList<Galaxy> universe;// = createUniverse();
	ArrayList<Star> stars;// = createGalaxy();
	JLabel starName;
	JButton resetBtn;
	JButton pauseBtn;
	boolean isPaused = false;
	String planetStr;
	public boolean justStarted = true;
	double oTheta = 0;
    double x = 0;
    double y = 0;
    double asteroidChance = 0.3;
    Random asteroid = new Random();
    boolean asteroidsExist = false;
    Asteroid ast1 = new Asteroid();
	Asteroid ast2 = new Asteroid();
	Asteroid ast3 = new Asteroid();
	
	Calendar calendar = new Calendar();
	JLabel lblDate = new JLabel();
	
	Display display;
	
	AffineTransform rotationTransform;
	AffineTransform transform;
	AffineTransform translateTransform;
	AffineTransform init;
	double rotation = 0;
	int mouseX;
	int mouseY;

//    ArrayList<Asteroid> asteroids;
	
	int dynDiam;
	//int planetAngle = getInitPlanetAngle();
	ArrayList<Asteroid> cluster;//= new AsteroidCluster();
	
    public Drawer(Display d) {
    	display = d;
    	universe = createUniverse();
		stars = createGalaxy();
		setNameLabel(stars.get(0));
		cluster = createCluster();
		transform = new AffineTransform();
        rotationTransform = new AffineTransform();
        fleet = createFleet();       
	}
    
    public Drawer(String filename) {
    	
    }
    
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		//This displays all stars in the galaxy
		if(justStarted) {			//only goes once
			initPaint(g2d);
			justStarted = false;
		}
		initAst(g2d);
		update(g2d);
	}
	
	public void determineShipTargets() {
		rShipTarget = new Random();
		for(int i = 0; i < fleet.shipList.size(); i++) {
			int planetIndex = rShipTarget.nextInt(stars.get(0).planetList.size()-1);
			fleet.shipList.get(i).target = stars.get(0).planetList.get(planetIndex);
			fleet.shipList.get(i).setStatusLabel(stars.get(0).planetList.get(planetIndex));
		}
	}
	
	
	public void displayShips(Graphics2D g2d) {
		for(int i = 0; i < fleet.shipList.size(); i++) {
			fleet.shipList.get(i).drawShip(g2d);
		}
	}
	
	public void initAst(Graphics2D g2d) {
//		ast1.createAsteroid(g2d);
//		ast2.createAsteroid(g2d);
//		ast3.createAsteroid(g2d);
//		ast1.move();
//		ast2.move();
//		ast3.move();
	}
	
	
	public void initPaint(Graphics2D g2d) {
//		lblDate.setBounds(50, 1000, 400, 40);
		for(int galaxy = 0; galaxy < universe.size(); galaxy++) {
			for(int star = 0; star < stars.size(); star++) {
				stars.get(star).createSystem();
				String starStr = stars.get(star).nameOfStar + " | Planets: " + stars.get(star).planetList.size();
				stars.get(star).createCircle(g2d, stars.get(star).getDiameter(), stars.get(star).getColor(), starStr);
				xAnc = stars.get(star).centerX;
				yAnc = stars.get(star).centerY;
				for(int planet = 0; planet < stars.get(star).planetList.size(); planet++) {
					planetStr = stars.get(star).planetList.get(planet).nameOfPlanet;
					
					if(stars.get(star).planetList.get(planet).chanceOfMoon < 4) {
						stars.get(star).planetList.get(planet).createCircle(g2d, stars.get(star).planetList.get(planet).orbitDiameter, stars.get(star).oX, stars.get(star).oY, stars.get(star).planetList.get(planet).getDiameter(), stars.get(star).planetList.get(planet).getColor(), stars.get(star).starColor, planetStr + ": " + stars.get(star).planetList.get(planet).getPlanetType(), true);
						stars.get(star).planetList.get(planet).createMoon();
						stars.get(star).planetList.get(planet).moon.createCircle(g2d, stars.get(star).planetList.get(planet).getDiameter() * 2, stars.get(star).planetList.get(planet).pX, stars.get(star).planetList.get(planet).pY, stars.get(star).planetList.get(planet).moon.sizeDiameter, stars.get(star).planetList.get(planet).moon.moonColor, stars.get(star).planetList.get(planet).getColor());
					} else {
						stars.get(star).planetList.get(planet).createCircle(g2d, stars.get(star).planetList.get(planet).orbitDiameter, stars.get(star).oX, stars.get(star).oY, stars.get(star).planetList.get(planet).getDiameter(), stars.get(star).planetList.get(planet).getColor(), stars.get(star).starColor, planetStr + ": " + stars.get(star).planetList.get(planet).getPlanetType(), false);

					}
				}
			}
		}
		//fleet.drawStatusLabels(g2d, stars.get(0).planetList.size()-1);
		determineShipTargets();
		displayShips(g2d);
		
		if(isPaused == true) {
			pauseBtn.setText("Resume");
		} else {
			pauseBtn.setText("Pause");
		}
		g2d.dispose();
	}
	
	public ShipFleet createFleet() {
		ShipFleet fleet = new ShipFleet();
		fleet.addShips(stars.get(0).planetList.size());
		return fleet;
	}
	
	public ArrayList<Galaxy> createUniverse() {
		Universe uni = new Universe();
		ArrayList<Galaxy> universe = uni.createUniverse();
		return universe;
	}
	public ArrayList<Star> createGalaxy() {
		Galaxy gal = new Galaxy();
		ArrayList<Star> galaxy = gal.createGalaxy();
		return galaxy;
	}
	
	public ArrayList<Asteroid> createCluster() {
		AsteroidCluster cluster = new AsteroidCluster();
		ArrayList<Asteroid> asteroids = cluster.createCluster();	
		return asteroids;
	}
	
	public void moveCluster(ArrayList<Asteroid> asteroids) {
		for(int asteroid = 0; asteroid < asteroids.size(); asteroid++) {
			asteroids.get(asteroid).move();
		}
	}
	
	public JLabel setNameLabel() {
		int fontsize = 30;
		Color c = stars.get(0).getColor();
		for(int star = 0; star < stars.size(); star++) {
			
			//every time you hit the button, add the system to the Galaxy, then change this JLabel to the next star's name.
			
			starName = new JLabel("The " + stars.get(star).nameOfStar + " System", JLabel.CENTER);
		}
		starName.setBounds(50, 100, 400, fontsize+20);
		starName.setFont(new Font("Helvetica", Font.PLAIN, fontsize));
		starName.setForeground(c);
		display.add(starName);
		return starName;
	}
	
	public void setDateLabel(String day, int week, int year) {
		String lblDateText = day + ", Week: " + week + " Year: " + year;
		lblDate.setText(lblDateText);
		lblDate.setLocation(50, 1000);
//		lblDate.setBounds(50, 1000, 400, 40);
		lblDate.setFont(new Font("Helvetica", Font.BOLD, 20));
		lblDate.setForeground(Color.WHITE);
		lblDate.setVisible(true);
		display.add(lblDate);
	}
	
	public void clearDateLabel() {
		lblDate.setText("");
	}
	
	public JLabel setNameLabel(Star newStar) {
		int fontsize = 30;
		Color c = newStar.getColor();
		for(int star = 0; star < stars.size(); star++) {
			
			//every time you hit the button, add the system to the Galaxy, then change this JLabel to the next star's name.
			
			starName = new JLabel("The " + planetStr + " System", JLabel.CENTER);
		}
		starName.setBounds(50, 100, 400, fontsize+20);
		starName.setFont(new Font("Helvetica", Font.PLAIN, fontsize));
		starName.setVisible(true);
		starName.setForeground(c);
		return starName;
	}
	
	public JButton resetStar() {
		//Graphics2D g2d = (Graphics2D) g;
		resetBtn = new JButton();
		resetBtn.setFont(new Font("Helvetica", Font.BOLD, 20));
		resetBtn.setText("New System");
		resetBtn.setBounds(1700, 1100, 200, 100);
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				universe = createUniverse();
				stars = createGalaxy();
//				starName = setNameLabel(stars.get(0));
				starName.setVisible(true);
				fleet = createFleet();
			}
		});
		return resetBtn;
	}
	
	public JButton pause() {
		pauseBtn = new JButton();
		pauseBtn.setFont(new Font("Helvetica", Font.BOLD, 20));
		pauseBtn.setText("Pause");
		pauseBtn.setBounds(1490, 1100, 200, 100);
		pauseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPaused = !isPaused;
			}
		});
		return pauseBtn;
	}
	
	public boolean clickedStar(int x, int y) {
		
		int leftEdge = Display.width/2 - stars.get(0).getDiameter()/2;
		int rightEdge = Display.width/2 + stars.get(0).getDiameter()/2;
		int topEdge = Display.height/2 + stars.get(0).getDiameter()/2;
		int bottomEdge = Display.height/2 - stars.get(0).getDiameter()/2;
		if(x < rightEdge && x > leftEdge && y < topEdge && y > bottomEdge) { 
			starClicked = true;
			StarInformationPanel sPanel = new StarInformationPanel(2000/2, 1350/2, 500, 500);
			sPanel.setInfo(stars.get(0));
			sPanel.setAlwaysOnTop(true);
			sPanel.setVisible(true);
		}
		return starClicked;
	}
	
	
	public void updateTransform() {
		rotation += 10;
		rotationTransform.setToRotation(Math.toRadians(rotation), 1050, 720);
		transform.setToIdentity();
//		transform.concatenate(rotationTransform);
	}
	
	public void togglePause() {
		isPaused = !isPaused;
	}
	
	public void update(Graphics2D g2d) {
//		lblDate.setBounds(50, 1000, 400, 40);
		lblDate.setLocation(50, 1000);
		clearDateLabel();
		setNameLabel();
		if(isPaused) {
			
			//change speed of ships to ZERO
			for(int i = 0; i < fleet.shipList.size(); i++) {
				fleet.shipList.get(i).speed = 0;
			}
			
			//change speed of planets and moons to ZERO
			for(int star = 0; star < stars.size(); star++) {
				for(int planet = 0; planet < stars.get(star).planetList.size(); planet++) {
					if(stars.get(star).planetList.get(planet).chanceOfMoon < 4) {
						stars.get(star).planetList.get(planet).speed = 0;
						stars.get(star).planetList.get(planet).moon.speed = 0;
					} else {
						stars.get(star).planetList.get(planet).speed = 0;
					}
				}
			}
		} else {
			//slow down calendar movement (this will update advanceTime every sec
			cumulativeTime += threadTime;
			if(cumulativeTime > 300) {
				cumulativeTime = 0;
				calendar.advanceTime(); //need to update the jlabel ;)
				System.out.println(calendar.getCurrentDay()+ " Weeks: " +calendar.getCurrentWeek()+ " Years: " + calendar.getCurrentYear());
			}
			
			//change speed of ships back to original
			for(int i = 0; i < fleet.shipList.size(); i++) {
				fleet.shipList.get(i).speed = 10;
			}
			
			//change speed of planets and moons back to original
			for(int star = 0; star < stars.size(); star++) {
				for(int planet = 0; planet < stars.get(star).planetList.size(); planet++) {
					if(stars.get(star).planetList.get(planet).chanceOfMoon < 4) {
						stars.get(star).planetList.get(planet).speed = 0.01;					//not gonna work because the planets have different speeds depending
						stars.get(star).planetList.get(planet).moon.speed = 0.1;				//on their order in the system.....
					} else {
						stars.get(star).planetList.get(planet).speed = 0.01;
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
		
		for(int star = 0; star < stars.size(); star++) {
//			if(starClicked) {
//				g2d.setColor(Color.YELLOW);
//				g2d.drawOval(stars.get(0).getX(), stars.get(0).getY(), stars.get(0).getDiameter(), stars.get(0).getDiameter());
//			}
			String starStr = stars.get(star).nameOfStar + " | Planets: " + stars.get(star).planetList.size();
			stars.get(star).createCircle(g2d, stars.get(star).getDiameter(), stars.get(star).getColor(), starStr);
			for(int planet = 0; planet < stars.get(star).planetList.size(); planet++) {
				String planetStr = stars.get(star).planetList.get(planet).nameOfPlanet;

				if(stars.get(star).planetList.get(planet).chanceOfMoon < 4) {
					stars.get(star).planetList.get(planet).createCircle(g2d, stars.get(star).planetList.get(planet).orbitDiameter, stars.get(star).oX, stars.get(star).oY, stars.get(star).planetList.get(planet).getDiameter(), stars.get(star).planetList.get(planet).getColor(), stars.get(star).starColor, planetStr + ": " + stars.get(star).planetList.get(planet).getPlanetType(), true);
					stars.get(star).planetList.get(planet).createMoon();
					stars.get(star).planetList.get(planet).moon.createCircle(g2d, stars.get(star).planetList.get(planet).getDiameter() * 2, stars.get(star).planetList.get(planet).pX, stars.get(star).planetList.get(planet).pY, stars.get(star).planetList.get(planet).moon.sizeDiameter, stars.get(star).planetList.get(planet).moon.moonColor, stars.get(star).planetList.get(planet).getColor());
				} else {
					stars.get(star).planetList.get(planet).createCircle(g2d, stars.get(star).planetList.get(planet).orbitDiameter, stars.get(star).oX, stars.get(star).oY, stars.get(star).planetList.get(planet).getDiameter(), stars.get(star).planetList.get(planet).getColor(), stars.get(star).starColor, planetStr + ": " + stars.get(star).planetList.get(planet).getPlanetType(), false);
				}
			}
		}
		displayShips(g2d);
		
		for(int i = 0; i < fleet.shipList.size(); i++) {
			
			//this will actually set the targets to each planet going down the list, not a random planet.
			
			fleet.shipList.get(i).visitPlanets(stars.get(0).planetList.get(i));
//				System.out.println("Ships all reached target and got new targets");
		}
		
		if(fleet.allReachedPlanets()) {
			determineShipTargets();
		}
		
		if(isPaused == true) {
			pauseBtn.setText("Resume");
		} else {
			pauseBtn.setText("Pause");
		}
		setDateLabel(calendar.getCurrentDay(), calendar.getCurrentWeek(), calendar.getCurrentYear());
		repaint();
	}
	
}
