package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import problem.asm.DesignParser;

public class AppGUI {
	
	
	public String classes;
	public String dotCommandLocation;
	

	public void prepareGUI() throws IOException
	{
		//makes mainframe and adds converter to grab GraphViz image
		JFrame mainFrame = new JFrame("UML Analyzer");	
		JPanel buttonPanel = new JPanel();
		
		final ImagePanel[] imageReplacer = new ImagePanel[1];
		imageReplacer[0] = new ImagePanel("myGraph.png");

		
		
		//TODO: buttonPanel.add(, BorderLayout.SOUTH);
		
		//sets the mainframe and adds all panels
		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(buttonPanel, BorderLayout.WEST);
		mainFrame.add(imageReplacer[0]);
		mainFrame.setVisible(true);
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
