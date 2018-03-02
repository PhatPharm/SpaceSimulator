package creation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
//public class Display extends JFrame implements MouseListener {
public class Display extends JFrame {
	static int width;
	static int height;
	static Graphics2D g;
	static boolean Running = true;
	static JButton btn;
	
	public Display() {
		width = 2000;
		height = 1350;
	}
	
	public void initWindow(int width, int height, boolean vis, Color backgroundColor) {
		setSize(width, height);
		getContentPane().setBackground(backgroundColor);
		setVisible(vis);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {
		Display frame = new Display();
		Color space = new Color(7, 0, 16);
		frame.initWindow(width, height, true, space);
		Drawer d = new Drawer(frame);
		JButton resetBtn = d.resetStar();
		JButton pauseBtn = d.pause();
		frame.add(resetBtn);
		frame.add(pauseBtn);
		frame.add(d);
		JLabel mouseStatus = new JLabel();
		frame.addMouseListener(new MouseAdapter() {
			
		    public void mouseClicked(MouseEvent e) {
		    	System.out.println(d.clickedStar(e.getX(), e.getY()));		    	
		    }
		});
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					d.togglePause();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		frame.validate();
	}
}
