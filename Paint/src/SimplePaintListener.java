import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class SimplePaintListener extends JPanel implements MouseListener, MouseMotionListener, KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private int prevX, prevY;     
	private boolean dragging;   

	public SimplePaintListener() {
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == SimplePaint.jbuttons[0]) {
			SimplePaint.currentColor = Color.WHITE;
		} else if(e.getSource() == SimplePaint.jbuttons[1]) {
			SimplePaint.currentColor = Color.RED;
		} else if(e.getSource() == SimplePaint.jbuttons[2]) {
			SimplePaint.currentColor = Color.GREEN;
		} else if(e.getSource() == SimplePaint.jbuttons[3]) {
			SimplePaint.currentColor = Color.BLUE;
		} else if(e.getSource() == SimplePaint.jbuttons[4]) {
			SimplePaint.currentColor = Color.CYAN;
		} else if(e.getSource() == SimplePaint.jbuttons[5]) {
			SimplePaint.currentColor = Color.MAGENTA;
		} else if(e.getSource() == SimplePaint.jbuttons[6]) {
			SimplePaint.currentColor = Color.YELLOW;
		} else if(e.getSource() == SimplePaint.jbuttons[7]) {
			SimplePaint.currentColor = JColorChooser.showDialog(this, "Please select a color", Color.WHITE);
		} else if(e.getSource() == SimplePaint.jbuttons[8]) {
			SimplePaint.lines.clear();
		} else if(e.getSource() == SimplePaint.exit) {
			System.exit(0);
		} else if(e.getSource() == SimplePaint.undo) {
			SimplePaint.lines.remove(SimplePaint.lines.size() - 1);
		}
	}			

	public void mouseReleased(MouseEvent evt) {
		if (dragging == false)
			return; 
		dragging = false;
	}

	public void mouseDragged(MouseEvent evt) {
		if (dragging == false)
			return;  

		int x = evt.getX();  
		int y = evt.getY();   

		if (x < 3)                       
			x = 3;                         
		if (x > SimplePaint.w - 57)      
			x = SimplePaint.w - 57;

		if (y < 3)                    
			y = 3;                          
		if (y > SimplePaint.h - 4)       
			y = SimplePaint.h - 4;
		
		SimplePaint.lines.add(new Line(x, y, prevX, prevY, SimplePaint.currentColor));
		prevX = x;
		prevY = y;
	} 

	public void mousePressed(MouseEvent evt) {
		int x = evt.getX();   
		int y = evt.getY();   
		
		if (dragging == true)  
			return;           

		else if (x > 3 && x < SimplePaint.w - 56 && y > 3 && y < SimplePaint.h - 3) {

			prevX = x;
			prevY = y;
			dragging = true;
		}
	}

	public void mouseEntered(MouseEvent evt) { }   
	public void mouseExited(MouseEvent evt) { }   
	public void mouseClicked(MouseEvent evt) { }   
	public void mouseMoved(MouseEvent evt) { }
	public void keyTyped(KeyEvent arg0) { }    
	
	Set<Integer> keyCode = new HashSet<Integer>();
	
	public void keyPressed(KeyEvent e) {
		keyCode.add(e.getKeyCode());
		if(keyCode.size() > 1) {
			if((int) keyCode.toArray()[0] == KeyEvent.VK_CONTROL && (int) keyCode.toArray()[1] == KeyEvent.VK_Z && !SimplePaint.lines.isEmpty())
				SimplePaint.lines.remove(SimplePaint.lines.size() - 1);
		}
	}

	public void keyReleased(KeyEvent e) {
		keyCode.remove(e.getKeyCode());
	}
}
