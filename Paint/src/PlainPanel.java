import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class PlainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static JMenuBar menuBar = 
			
			new JMenuBar();
	
	public PlainPanel() {
		
		setLayout(new BorderLayout());
		
		JPanel layout = new JPanel();
		JPanel paint = new SimplePaint();
		JPanel buttons = new JPanel(new GridLayout(0,1));
		
		layout.setLayout(new BorderLayout());
		
		for(int i = 0; i < SimplePaint.jbuttons.length; ++i) {
			SimplePaint.jbuttons[i].setFocusable(false);
			buttons.add(SimplePaint.jbuttons[i]);
		}
		
		JMenu simplePaint = new JMenu("Simple Paint");
		JMenu edit = new JMenu("Edit");

		simplePaint.add(SimplePaint.exit); edit.add(SimplePaint.undo);
		menuBar.add(simplePaint); menuBar.add(edit);
		
		layout.add(buttons, BorderLayout.EAST);
		layout.add(paint, BorderLayout.CENTER);
		
		add(layout, BorderLayout.CENTER);
	}
}
