package problem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import problem.asm.DesignParser;

public class TestForComposite {

	@Test
	public void testForCompositeInJComponent() throws IOException
	{

		DesignParser myParser = new DesignParser();
		myParser.run("java.awt.Container java.awt.Component javax.swing.JComponent");
		
		//Checks the file where the run method creates the Umlet TXT file
		File file = new File("TESTCODE.txt");
		
		//creates an integer value that only increments iff green is contained in the line
		int count = 0;
		
		try {
			//scans every line
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("yellow")) { 
		        	count++;
		        }
		    }
		    //We EXPECT 3 Green shades 
		    assertEquals(2, count);
		} catch(FileNotFoundException e) { 
			fail();
		    //handle this
		}
	}
	
	@Test
	public void testForCompositeInTextbox() throws IOException
	{

		DesignParser myParser = new DesignParser();
		myParser.run("java.awt.Container java.awt.Component java.awt.Button");
		
		//Checks the file where the run method creates the Umlet TXT file
		File file = new File("TESTCODE.txt");
		
		//creates an integer value that only increments iff green is contained in the line
		int count = 0;
		
		try {
			//scans every line
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("yellow")) { 
		        	count++;
		        }
		    }
		    //We EXPECT 3 Green shades 
		    assertEquals(2, count);
		} catch(FileNotFoundException e) { 
			fail();
		    //handle this
		}
	}
	
	@Test
	public void testForCompositeInMenu() throws IOException
	{

		DesignParser myParser = new DesignParser();
		myParser.run("headfirst.composite.menu.Menu headfirst.composite.menu.MenuComponent headfirst.composite.menu.MenuItem headfirst.composite.menu.MenuTestDrive headfirst.composite.menu.Waitress");
		
		//Checks the file where the run method creates the Umlet TXT file
		File file = new File("TESTCODE.txt");
		
		//creates an integer value that only increments iff green is contained in the line
		int count = 0;
		
		try {
			//scans every line
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("yellow")) { 
		        	count++;
		        }
		    }
		    //We EXPECT 3 Green shades 
		    assertEquals(0, count);
		} catch(FileNotFoundException e) { 
			fail();
		    //handle this
		}
	}
	
	
	@Test
	public void testForCompositeInMenuIterator() throws IOException
	{

		DesignParser myParser = new DesignParser();
		myParser.run("headfirst.composite.menuiterator.Menu headfirst.composite.menuiterator.MenuComponent headfirst.composite.menuiterator.MenuItem headfirst.composite.menuiterator.MenuTestDrive headfirst.composite.menuiterator.NullIterator headfirst.composite.menuiterator.CompositeIterator headfirst.composite.menuiterator.Waitress");
		
		//Checks the file where the run method creates the Umlet TXT file
		File file = new File("TESTCODE.txt");
		
		//creates an integer value that only increments iff green is contained in the line
		int count = 0;
		
		try {
			//scans every line
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("yellow")) { 
		        	count++;
		        }
		    }
		    //We EXPECT 3 Green shades 
		    assertEquals(2, count);
		} catch(FileNotFoundException e) { 
			fail();
		    //handle this
		}
	}
		
		@Test
		public void testForCompositeInDinerMergerCafe() throws IOException
		{

			DesignParser myParser = new DesignParser();
			myParser.run("headfirst.iterator.dinermergercafe.AlternatingDinerMenuIterator headfirst.iterator.dinermergercafe.CafeMenu headfirst.iterator.dinermergercafe.DinerMenu headfirst.iterator.dinermergercafe.DinerMenuIterator headfirst.iterator.dinermergercafe.Menu headfirst.iterator.dinermergercafe.MenuItem headfirst.iterator.dinermergercafe.MenuTestDrive headfirst.iterator.dinermergercafe.PancakeHouseMenu headfirst.iterator.dinermergercafe.Waitress");
			//Checks the file where the run method creates the Umlet TXT file
			File file = new File("TESTCODE.txt");
			
			//creates an integer value that only increments iff green is contained in the line
			int count = 0;
			
			try {
				//scans every line
			    Scanner scanner = new Scanner(file);

			    while (scanner.hasNextLine()) {
			        String line = scanner.nextLine();
			        if(line.contains("yellow")) { 
			        	count++;
			        }
			    }
			    //We EXPECT 3 Green shades 
			    assertEquals(6, count);
			} catch(FileNotFoundException e) { 
				fail();
			    //handle this
			}
		
	}
}
