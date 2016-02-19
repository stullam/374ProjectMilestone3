package app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import problem.asm.ClassContainer;
import problem.asm.ClassDeclarationVisitor;
import problem.asm.DesignParser;


public class AppGUI {
	
	private final ImagePanel[] imageReplacer = new ImagePanel[1];
	public ArrayList<String> checkBoxes = new ArrayList<String>();
	public String field1 = null;
	public String field2 = null;
	public String field4 = null;
	JFrame mainFrame = new JFrame("UML Analyzer");
	JPanel buttonPanel = new JPanel();

	

	public AppGUI(String text, String text2, String string) {
		// TODO Auto-generated constructor stub
		this.field1 = text;
		this.field2 = text2;
		this.field4 = string;
	}

	public void prepareGUI(String location, String phaseList) throws IOException
	{
		//makes mainframe and adds converter to grab GraphViz image
		ClassContainer cc = new ClassContainer();
		ClassDeclarationVisitor CDL = new ClassDeclarationVisitor();
		//JFrame mainFrame = new JFrame("UML Analyzer");	
		//JPanel buttonPanel = new JPanel();
		String phases[] = phaseList.split(" ");
		ArrayList<JCheckBox> boxes = new ArrayList<JCheckBox>();
		if(!(phases[0].equals("")))
		{
			for(int i=0; i<phases.length; i++)
			{
				if(CDL.patternMap.containsKey(phases[i]))
				{
					JCheckBox box = new JCheckBox(phases[i]);
					box.setName(phases[i]);
					//JButton config = new JButton("Configure");
					box.addActionListener(new ActionListener() {
				         public void actionPerformed(ActionEvent e) {
				     		System.out.println(box.getName());
				     		if(checkBoxes.contains(box.getName())){
				     			checkBoxes.remove(box.getName());
				     		}
				     		else {
				     			checkBoxes.add(box.getName());
				     		}
				     		
				     		System.out.println(checkBoxes.toString());
				     		updateUI();
				     		
				         }
					});
//				if(cc.patterns.containsKey(phases[i]))
//				{
//					JCheckBox box = new JCheckBox(phases[i]);
					//STULL: ADD YOUR WORK HERE. IF BOX IS SELECTED
					// THEN ONLY MAKE THE GRAPH FOR THAT TYPE
					// FOR EXAMPLE: IF phases[i] IS ADAPTER, THEN ONLY MAKE A GRAPH
					// WITH PURPLE BOXES IN THEM
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
	
	public void updateUI() {
		DesignParser parser;
		try {
			parser = new DesignParser();
			parser.runComparison(field1, this.checkBoxes);
			GraphVizImageConverter converter = new GraphVizImageConverter(field2);
			converter.convertGraphVizText("myGraph","TESTCODE.txt");
			
			//mainFrame.remove(imageReplacer[0]);
			mainFrame.remove(imageReplacer[0]);
			System.out.println("I Removed imageReplacer");
			imageReplacer[0] = new ImagePanel("./" + "myGraph.png");
			//mainFrame.setLayout(new BorderLayout());
			//mainFrame.add(buttonPanel, BorderLayout.WEST);
			mainFrame.add(imageReplacer[0], BorderLayout.CENTER);
			mainFrame.setVisible(true);
			mainFrame.revalidate();
			mainFrame.repaint();
			mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        System.out.println("I Repainted");
			
			if(!(field4.equals("")))
	        {
	        	File outputfile = new File(field4+"\\", "myGraph.jpg");
		        ImageIO.write(this.getImagePanel().getImage(), "jpg", outputfile);
	        }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ImagePanel getImagePanel()
	{
		return imageReplacer[0];
	}
	
}
