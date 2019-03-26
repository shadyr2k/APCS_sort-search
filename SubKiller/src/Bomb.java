import java.awt.Color;
import java.awt.Graphics;

public class Bomb {

	private int centerX, centerY;
	private boolean isFalling;    
	private SubKillerPanel gamePanel;
	private Boat boat; 
	private Submarine sub;

	Bomb(SubKillerPanel gamePanel, Boat boat, Submarine sub) { 
		this.gamePanel = gamePanel;
		this.boat = boat;
		this.sub = sub;	
		isFalling = false;
	}
	void updateForNewFrame() { 
		if (isFalling) {
			if (centerY > gamePanel.getHeight()) {

				isFalling = false;
			}
			else if (Math.abs(centerX - sub.getCenterX()) <= 36 &&
					Math.abs(centerY - sub.getCenterY()) <= 21) {
				// Bomb has hit the submarine.  The submarine
				// enters the "isExploding" state.
				sub.setExploding(true);
				gamePanel.incrementScore();
				sub.setExplosionFrameNumber(1);
				isFalling = false;  // Bomb reappears on the boat.
			}
			else {
				// If the bomb has not fallen off the panel or hit the
				// sub, then it is moved down 10 pixels.
				centerY += 10;
			}
		}
	}
	void draw(Graphics g) { // Draw the bomb.
		if ( ! isFalling ) {  // If not falling, set centerX and centerY
			// to show the bomb on the bottom of the boat.
			centerX = boat.getCenterX();
			centerY = boat.getCenterY() + 23;
		}
		g.setColor(Color.RED);
		g.fillOval(centerX - 8, centerY - 8, 16, 16);
	}

	public int getCenterX() {
		return centerX;
	}
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}
	public int getCenterY() {
		return centerY;
	}
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	public boolean isFalling() {
		return isFalling;
	}
	public void setFalling(boolean isFalling) {
		this.isFalling = isFalling;
	}
	public Boat getBoat() {
		return boat;
	}
	public void setBoat(Boat boat) {
		this.boat = boat;
	}
	public Submarine getSub() {
		return sub;
	}
	public void setSub(Submarine sub) {
		this.sub = sub;
	}
}
