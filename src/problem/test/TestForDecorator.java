package problem.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import problem.asm.DesignParser;

public class TestForDecorator {

	@Test
	public void testForDecoratorInStarbuzz() throws IOException
	{
		//Creates a DesignParser with one of each type of Starbuzz component
		DesignParser myParser = new DesignParser();
		myParser.run("headfirst.decorator.starbuzz.Beverage headfirst.decorator.starbuzz.CondimentDecorator headfirst.decorator.starbuzz.DarkRoast headfirst.decorator.starbuzz.Soy");
		
		//Checks the file where the run method creates the Umlet TXT file
		File file = new File("TESTCODE.txt");
		
		//creates an integer value that only increments iff green is contained in the line
		int count = 0;
		
		try {
			//scans every line
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("green")) { 
		        	count++;
		        }
		    }
		    //We EXPECT 3 Green shades 
		    assertEquals(3, count);
		} catch(FileNotFoundException e) { 
			fail();
		    //handle this
		}
	}
	
	@Test
	public void testForDecoratorInBeverage() throws IOException
	{
		//Creates a DesignParser and runs it
		DesignParser myParser = new DesignParser();
		myParser.run("headfirst.decorator.starbuzz.Beverage");
		
		//Checks the file where the run method creates the Umlet TXT file
		File file = new File("TESTCODE.txt");
		
		//creates an integer value that only increments iff green is contained in the line
		int count = 0;
		
		try {
			//scans every line
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("green")) { 
		        	count++;
		        }
		    }
		    //We EXPECT NO Green shades 
		    assertEquals(0, count);
		} catch(FileNotFoundException e) { 
			fail();
		    //handle this
		}
	}
	
	@Test
	public void testForDecoratorInCondimentDecorator() throws IOException
	{
		//Creates a DesignParser and runs it
		DesignParser myParser = new DesignParser();
		myParser.run("headfirst.decorator.starbuzz.CondimentDecorator");
		
		//Checks the file where the run method creates the Umlet TXT file
		File file = new File("TESTCODE.txt");
		
		//creates an integer value that only increments iff green is contained in the line
		int count = 0;
		
		try {
			//scans every line
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("green")) { 
		        	count++;
		        }
		    }
		    //We EXPECT NO Green shades 
		    assertEquals(0, count);
		} catch(FileNotFoundException e) { 
			fail();
		    //handle this
		}
	}
	
	@Test
	public void testForDecoratorInSoy() throws IOException
	{
		//Creates a DesignParser and runs it
		DesignParser myParser = new DesignParser();
		myParser.run("headfirst.decorator.starbuzz.Soy");
		
		//Checks the file where the run method creates the Umlet TXT file
		File file = new File("TESTCODE.txt");
		
		//creates an integer value that only increments iff green is contained in the line
		int count = 0;
		
		try {
			//scans every line
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("green")) { 
		        	count++;
		        }
		    }
		    //We EXPECT 1 Green shade 
		    assertEquals(1, count);
		} catch(FileNotFoundException e) { 
			fail();
		    //handle this
		}
	}
	
	
	

}
