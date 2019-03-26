import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class SimplePaint extends JPanel {

	private static final long serialVersionUID = 1L;

    public static Color currentColor = Color.WHITE;  
    public static ArrayList<Line> lines = new ArrayList<Line>();
    public static int w = 0;
    public static int h = 0;
    public static JButton[] jbuttons = new JButton[]{new JButton("White"), new JButton("Red"), new JButton("Green"), new JButton("Blue"), new JButton("Cyan"),
    		new JButton("Magenta"), new JButton("Yellow"), new JButton("Custom"), new JButton("Clear")};
    public static JMenuItem exit = new JMenuItem("Exit");
    public static JMenuItem undo = new JMenuItem("Undo");

    // *** Let's make a nested class to define a new data type that will be stored in a data structure
    // e.g. (not a Rectangle[] rectangles)   
    
    

    /**
     * Constructor for SimplePaintPanel class sets the background color to be
     * white and sets it to listen for mouse events on itself.
     */
    SimplePaint() {
        this.setBackground(Color.BLACK);
        SimplePaintListener listener = new SimplePaintListener();
        
        setFocusable(true);
        requestFocusInWindow();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        addKeyListener(listener);
        
        for(int i = 0; i < jbuttons.length; ++i) {
        	jbuttons[i].addActionListener(listener);
        }
        
        exit.addActionListener(listener);
        undo.addActionListener(listener);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Fill with background color (white).
        getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
        
        w = getWidth();
        h = getHeight();
        
        // *** Re-draw all of the information, based on the state of our data structure
        for(int i = 0; i < lines.size(); ++i) {
        	g.setColor(lines.get(i).colorCode);
        	g.drawLine(lines.get(i).x1, lines.get(i).y1, lines.get(i).x2, lines.get(i).y2);
        }
        repaint();
    } // end paintComponent()


} // end class SimplePaint
