import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ScorePanel extends JPanel {
	int score = 0;
	JSlider slider;
	JLabel scoreLabel; 
	
	public ScorePanel() {
		this.setLayout(new GridLayout(1,2));
		
		JPanel difficultyPanel = new JPanel();
		JLabel difficultyLabel = new JLabel("Difficulty: ");
		slider = new JSlider(1, 5, 1);
		slider.setMajorTickSpacing(2);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		
		difficultyPanel.add(difficultyLabel);
		difficultyPanel.add(slider);
		
		scoreLabel = new JLabel("Score: " + score);
		
		this.add(difficultyPanel);
		this.add(scoreLabel);
	}
	
	public JSlider getSlider() {
		return slider;
	}

	public void setScore(int s) {
		score = s;
	}
	
	public int getScore() {
		return score;
	}
	
	public JLabel getScoreLabel() {
		return scoreLabel;
	}
}	
