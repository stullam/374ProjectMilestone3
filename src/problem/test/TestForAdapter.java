package problem.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import problem.asm.DesignParser;

public class TestForAdapter {
	
	public void check() throws IOException
	{
		DesignParser myParser = new DesignParser("problem.client.IteratorToEnumerationAdapter");
		myParser.run("problem.client.IteratorToEnumerationAdapter");
	}

	@Test
	public void testForAdapterInIterator() throws IOException
	{
		//Creates a DesignParser and runs it
		DesignParser myParser = new DesignParser("problem.client.IteratorToEnumerationAdapter");
		myParser.run("problem.client.IteratorToEnumerationAdapter");
		
		//Checks the file where the run method creates the Umlet TXT file
		File file = new File("TESTCODE.txt");
		
		//creates a boolean value that only gets set to true iff blue is contained in the line
		boolean thing = false;
		
		try {
			//scans every line
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("purple")) { 
		        	thing = true;
		        }
		    }
		    assertEquals(true, thing);
		} catch(FileNotFoundException e) { 
			fail();
		    //handle this
		}
	}
	
	@Test
	public void testForAdapterInEnumeration() throws IOException
	{
		//Creates a DesignParser and runs it
		DesignParser myParser = new DesignParser("java.util.Enumeration");
		myParser.run("java.util.Enumeration");
		
		//Checks the file where the run method creates the Umlet TXT file
		File file = new File("TESTCODE.txt");
		
		//creates a boolean value that only gets set to true iff blue is contained in the line
		boolean thing = false;
		
		try {
			//scans every line
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("purple")) { 
		        	thing = true;
		        }
		    }
		    assertEquals(false, thing);
		} catch(FileNotFoundException e) { 
			fail();
		    //handle this
		}
	}
	
	@Test
	public void testForAdapterInProblemClient() throws IOException
	{
		//Creates a DesignParser and runs it
		DesignParser myParser = new DesignParser("");
		myParser.run("problem.client.IteratorToEnumerationAdapter java.util.Enumeration");
		
		//Checks the file where the run method creates the Umlet TXT file
		File file = new File("TESTCODE.txt");
		
		//creates a boolean value that only gets set to true iff blue is contained in the line
		int count = 0;
		
		try {
			//scans every line
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("purple")) { 
		        	count++;
		        }
		    }
		    assertEquals(2, count);
		} catch(FileNotFoundException e) { 
			fail();
		    //handle this
		}
	}
}
