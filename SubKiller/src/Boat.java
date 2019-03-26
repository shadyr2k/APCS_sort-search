import java.awt.Color;
import java.awt.Graphics;

public class Boat {
	
	private int centerX, centerY;  
	
	private SubKillerPanel gamePanel;
		
    Boat(SubKillerPanel gamePanel) { 
        this.gamePanel = gamePanel;
    	centerX = gamePanel.getWidth()/2;
        centerY = 80;
    }
    void updateForNewFrame() { 
        if (centerX < 0)
            centerX = 0;
        else if (centerX > gamePanel.getWidth())
            centerX = gamePanel.getWidth();
    }
    void draw(Graphics g) {  
        g.setColor(Color.BLUE);
        g.fillRoundRect(centerX - 40, centerY - 20, 80, 40, 20, 20);
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
}
