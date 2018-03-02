package creation;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

@SuppressWarnings("serial")
public class StarInformationPanel extends JFrame {
	
	int x;
	int y;
	int width;
	int height;
	
	public StarInformationPanel(int xPos, int yPos, int w, int h) {
		x = xPos;
		y = yPos;
		width = w;
		height = h;
		initWindow(width, height);
	}
	
	public void initWindow(int width, int height) {
		setSize(width, height);
		getContentPane().setBackground(new Color(255,255,255));
		setVisible(true);
		setAlwaysOnTop(true);
		getContentPane().setLayout(null);
	}
	
	public void setInfo(Star s) {
		int labelOffset = 18;
		Font lblFont = new Font("Helvetica", Font.BOLD, 15);
		JLabel name = new JLabel("System Name: " + s.nameOfStar);
		JLabel starDiameter = new JLabel("Star Diameter (pixels): " + Integer.toString(s.getDiameter()));		
		JLabel starTemp = new JLabel("Star Temperature: " + Integer.toString(s.getTemperature()));
		JLabel starChildren = new JLabel("Planets: " + s.planetList.size());
		
		starDiameter.setFont(lblFont);
		name.setFont(lblFont);
		starTemp.setFont(lblFont);
		starChildren.setFont(lblFont);
		
		for(int i = 0; i < s.planetList.size(); i++) {
			int y = i*labelOffset;
			JLabel planetName;
			if(s.planetList.get(i).hasMoon) {
				planetName = new JLabel(s.planetList.get(i).nameOfPlanet + " (Moon)");
			} else {
				planetName = new JLabel(s.planetList.get(i).nameOfPlanet);
			}
			planetName.setBounds(30, 85+y, 400, 23);
			planetName.setFont(lblFont);
			this.add(planetName);
		}
		
		name.setBounds(151, 19, 400, 23);
		starDiameter.setBounds(10, 40, 400, 23);
		starTemp.setBounds(10, 55, 400, 23);
		starChildren.setBounds(10, 70, 400, 23);
		
		this.add(name);
		this.add(starDiameter);
		this.add(starTemp);
		this.add(starChildren);
	}
}
