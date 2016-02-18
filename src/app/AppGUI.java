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

	public void prepareGUI(String classes, String dotField) throws IOException
	{
		//makes mainframe and adds converter to grab GraphViz image
		JFrame mainFrame = new JFrame("UML Analyzer");	
		JPanel buttonPanel = new JPanel();
		
		final ImagePanel[] imageReplacer = new ImagePanel[1];
		imageReplacer[0] = new ImagePanel("myGraph.png");
		
		//makes configure button & label
		JButton config= new JButton("Configure");
		config.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if(!(classes.equals("")))
	     		  {
	     			DesignParser parser;
					try {
						parser = new DesignParser();
						parser.run(classes);
						GraphVizImageConverter converter = new GraphVizImageConverter(dotField);
						converter.convertGraphVizText("myGraph","TESTCODE.txt");
						ImagePanel myImage = new ImagePanel("myGraph.png");
						System.out.println("THIS COMPONENT: " +mainFrame.getComponent(0).toString());
						mainFrame.remove(imageReplacer[0]);
						mainFrame.add(myImage);
						imageReplacer[0] = myImage;
						mainFrame.revalidate();
						mainFrame.repaint();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	     			
	     		  }
	            System.out.println("THE BUTTON WORKS!");
	         }          
	      });
		buttonPanel.add(config, BorderLayout.SOUTH);
		
		//sets the mainframe and adds all panels
		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(buttonPanel, BorderLayout.WEST);
		
		GraphVizImageConverter converter = new GraphVizImageConverter(dotField);
		converter.convertGraphVizText("myGraph","TESTCODE.txt");
		mainFrame.add(imageReplacer[0]);
		
		mainFrame.setVisible(true);
		mainFrame.revalidate();
		mainFrame.repaint();
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
