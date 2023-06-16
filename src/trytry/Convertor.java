package trytry;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Convertor {
	 
    public static void main(String[] args) {
    	
    	//Creates and displays the main program frame
    
        JFrame frame = new JFrame("Converter");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        MainPanel panel = new MainPanel();
 
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(600, 600));
        frame.setJMenuBar(panel.setupMenu());
    
        frame.getContentPane().add(panel);
        frame.setLocation(400,75);
        frame.pack();
        frame.setVisible(true);
    }
}