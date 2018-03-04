package creation;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
//public class Display extends JFrame implements MouseListener {
public class Display extends JFrame {

  static int width;
  static int height;

  private Display() {
    width = 2000;
    height = 1350;
  }

  public static void main(String args[]) {
    Display frame = new Display();
    Color space = new Color(7, 0, 16);
    frame.initWindow(width, height, space);
    creation.Drawer d = new Drawer(frame);
    JButton resetBtn = d.resetStar();
    JButton pauseBtn = d.pause();
    frame.add(resetBtn);
    frame.add(pauseBtn);
    frame.add(d);
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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
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

  private void initWindow(int width, int height, Color backgroundColor) {
    setSize(width, height);
    getContentPane().setBackground(backgroundColor);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}
