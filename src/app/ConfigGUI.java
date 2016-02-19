package app;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import problem.asm.DesignParser;


public class ConfigGUI 
{
	
	public void prepareGUI()
	{
		JFrame frame = new JFrame("Configure Your UML");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JLabel label3 = new JLabel("Input-Folder");
		JLabel label4 = new JLabel("Output-Directory");
		JLabel label1 = new JLabel("Input-Classes");
		JLabel label2 = new JLabel("Dot-Path");
		JLabel label5 = new JLabel("Phases");
		JTextField field1 = new JTextField(100);
		JTextField field2 = new JTextField(100);
		JTextField field3 = new JTextField(100);
		JTextField field4 = new JTextField(100);
		JTextField field5 = new JTextField(100);
		panel3.add(label3);
		panel3.add(field3);
		panel5.add(label5);
		panel5.add(field5);
		panel4.add(label4);
		panel4.add(field4);
		panel1.add(label1);
		panel1.add(field1);
		panel2.add(label2);
		panel2.add(field2);
		JButton config = new JButton("Configure");
		config.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	     		try {
					DesignParser parser = new DesignParser();
					parser.run(field1.getText());
					GraphVizImageConverter converter = new GraphVizImageConverter(field2.getText());
					converter.convertGraphVizText("myGraph","TESTCODE.txt");
					AppGUI gui = new AppGUI(field1.getText(), field2.getText(), field4.getText());
					gui.prepareGUI(field2.getText(),  field5.getText());

			        if(!(field4.getText().equals("")))
			        {
			        	File outputfile = new File(field4.getText()+"\\", "myGraph.jpg");
				        ImageIO.write(gui.getImagePanel().getImage(), "jpg", outputfile);
			        }
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	         }
		});
		frame.setLayout(new FlowLayout());
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.add(panel4);
		frame.add(panel5);
		frame.add(config);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint(); 
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

}
