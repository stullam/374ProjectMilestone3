package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import problem.asm.DesignParser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigGUI 
{
	
	public void prepareGUI()
	{
		JFrame frame = new JFrame("Configure Your UML");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel label = new JLabel("Classes to be Converted");
		JLabel label2 = new JLabel("Location of GraphViz Dot Command");
		JTextField field1 = new JTextField(100);
		JTextField field2 = new JTextField(100);
		panel1.add(label);
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
					AppGUI gui = new AppGUI();
					gui.prepareGUI();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	         }
		});
	
		frame.setLayout(new BorderLayout());
		frame.add(panel1, BorderLayout.PAGE_START);
		frame.add(panel2, BorderLayout.CENTER);
		frame.add(config, BorderLayout.PAGE_END);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint(); 
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

}
