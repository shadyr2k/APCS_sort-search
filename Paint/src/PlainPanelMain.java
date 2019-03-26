import java.awt.BorderLayout;

import javax.swing.JFrame;

public class PlainPanelMain {
	public static void main(String[] args) {
        JFrame window = new JFrame("Simple Paint");
        window.setContentPane(new PlainPanel());
     //   window.setLayout(new BorderLayout());
        window.setSize(700,380);
        window.setLocation(100,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setJMenuBar(PlainPanel.menuBar);
        window.setVisible(true);
    }
}
