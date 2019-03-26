import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SubKillerListener implements KeyListener, FocusListener, MouseListener, ActionListener, ChangeListener {

	SubKillerPanel gamePanel; 
	Timer timer;

	public SubKillerListener(SubKillerPanel panel, ScorePanel scorePanel) {
		gamePanel = panel;
		gamePanel.addMouseListener(this);
		gamePanel.addKeyListener(this);
		gamePanel.addFocusListener(this);
		timer = new Timer(30, this);
		timer.start();
		scorePanel.getSlider().addChangeListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if(s == null) {
			if (gamePanel.getBomb() != null) {
				gamePanel.getBoat().updateForNewFrame();
				gamePanel.getBomb().updateForNewFrame();
				gamePanel.getSub().updateForNewFrame();
			}
		} else if(s.equals("About")) {
			JOptionPane.showMessageDialog(gamePanel, "nothing to see here lol");
		} else if(s.equals("Quit")) {
			System.exit(0);
		} else if(s.equals("Restart")) {
			gamePanel.restart();
		}
		
		gamePanel.repaint();
	}
	
	public void keyPressed(KeyEvent evt) {
		int code = evt.getKeyCode();  
		if (code == KeyEvent.VK_LEFT) {
			gamePanel.getBoat().setCenterX(gamePanel.getBoat().getCenterX() - 15);
		} else if (code == KeyEvent.VK_RIGHT) {  
			gamePanel.getBoat().setCenterX(gamePanel.getBoat().getCenterX() + 15);
		} else if (code == KeyEvent.VK_DOWN) {
			if (gamePanel.getBomb().isFalling() == false)
				gamePanel.getBomb().setFalling(true);
		}

	}
	
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if(!source.getValueIsAdjusting()) {
			int difficultyLevel = source.getValue();
			gamePanel.setSubSpeed(difficultyLevel);
			gamePanel.requestFocusInWindow();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		gamePanel.requestFocusInWindow();

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		timer.start();
		gamePanel.repaint();
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		timer.stop();
		gamePanel.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	

}
