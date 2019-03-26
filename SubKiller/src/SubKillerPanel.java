import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class SubKillerPanel extends JPanel { 

	private Boat boat;         
	private Bomb bomb;         
	private Submarine sub;
	private int subSpeed = 1;
	private ScorePanel scorePanel;
	
	public SubKillerPanel(ScorePanel s) {
		setBackground(new Color(0, 200, 0));
		scorePanel = s;
		repaint();
	}

	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 

		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (boat == null) {
			boat = new Boat(this);
			sub = new Submarine(this);
			bomb = new Bomb(this, boat, sub);
		}

		if (hasFocus())
			g.setColor(Color.CYAN);
		else {
			g.setColor(Color.BLACK);
			g.drawString("CLICK TO ACTIVATE", 20, 30);
			g.setColor(Color.GRAY);
		}
		g.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);  
		g.drawRect(1,1,this.getWidth()-3,this.getHeight()-3);
		g.drawRect(2,2,this.getWidth()-5,this.getHeight()-5);

		boat.draw(g); 
		sub.draw(g);
		for(int i = 1; i < subSpeed; ++i) {
			sub.updateForNewFrame();
			sub.draw(g);
		}				
		bomb.draw(g);
	}

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	public Submarine getSub() {
		return sub;
	}

	public void setSub(Submarine sub) {
		this.sub = sub;
	}
	
	public void setSubSpeed(int speed) {
		subSpeed = speed;
	}

	public void incrementScore() {

		scorePanel.setScore(scorePanel.getScore() + 1);
		scorePanel.getScoreLabel().setText("Score: " + scorePanel.getScore());
		scorePanel.repaint();
	}
	
	public void restart() {
		boat = null;
		bomb = null;
		sub = null;
		subSpeed = 1;
		scorePanel.setScore(0);
		scorePanel.getScoreLabel().setText("Score: 0");
		scorePanel.getSlider().setValue(1);
	}

}
