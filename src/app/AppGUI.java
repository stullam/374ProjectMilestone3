package app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import problem.asm.ClassContainer;


public class AppGUI {
	
	private final ImagePanel[] imageReplacer = new ImagePanel[1];

	

	public void prepareGUI(String location, String phaseList) throws IOException
	{
		//makes mainframe and adds converter to grab GraphViz image
		ClassContainer cc = new ClassContainer();
		JFrame mainFrame = new JFrame("UML Analyzer");	
		JPanel buttonPanel = new JPanel();
		String phases[] = phaseList.split(" ");
		ArrayList<JCheckBox> boxes = new ArrayList<JCheckBox>();
		if(!(phases[0].equals("")))
		{
			for(int i=0; i<phases.length; i++)
			{
				if(cc.patterns.containsKey(phases[i]))
				{
					JCheckBox box = new JCheckBox(phases[i]);
					//STULL: ADD YOUR WORK HERE. IF BOX IS SELECTED
					// THEN ONLY MAKE THE GRAPH FOR THAT TYPE
					// FOR EXAMPLE: IF phases[i] IS ADAPTER, THEN ONLY MAKE A GRAPH
					// WITH PURPLE BOXES IN THEM
					//here's some sudo code
					//if (box.isSelected()) {
					 
					    // do something...
					 
					//} else {
					 
					    // do something else...
					
					
					//don't remove the line below!
					boxes.add(box);
					}
					
				}
				
			
			for(int j=0; j< boxes.size(); j++)
			{
				buttonPanel.add(boxes.get(j));
			}
		}
		
		imageReplacer[0] = new ImagePanel("./" + "myGraph.png");

		
		
		//TODO: buttonPanel.add(, BorderLayout.SOUTH);
		
		//sets the mainframe and adds all panels
		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(buttonPanel, BorderLayout.WEST);
		mainFrame.add(imageReplacer[0], BorderLayout.CENTER);
		mainFrame.setVisible(true);
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public ImagePanel getImagePanel()
	{
		return imageReplacer[0];
	}
	
}
