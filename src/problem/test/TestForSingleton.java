package problem.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import problem.asm.DesignParser;

public class TestForSingleton {
	
	@Test
	public void testForNoSingleton() throws IOException
	{
		//Creates a DesignParser and runs it
		DesignParser myParser = new DesignParser("problem.asm.Interface1");
		myParser.run("problem.asm.Interface1");
		
		//Checks the file where the run method creates the Umlet TXT file
		File file = new File("SINGLETON_TEST.txt");
		
		//creates a boolean value that only gets set to true iff blue is contained in the line
		boolean thing = false;
		
		try {
			//scans every line
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("blue")) { 
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
	public void testForSingletonInDesktop() throws IOException
	{
		DesignParser myParser = new DesignParser("java.awt.Desktop");
		myParser.run("java.awt.Desktop");
		File file = new File("SINGLETON_TEST.txt");
		boolean thing = false;
		
		try {
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        //find the word blue in the current line
		        if(line.contains("blue")) { 
		        	//sets it true as a singleton because our file creates it as blue
		        	thing = true;
		        }
		    }
		    assertEquals(true, thing);
		} catch(FileNotFoundException e) { 
			fail("There's No File!");
		}
	}
	
	@Test
	public void testForSingletonInRuntime() throws IOException
	{
		DesignParser myParser = new DesignParser("java.lang.Runtime");
		myParser.run("java.lang.Runtime");
		File file = new File("SINGLETON_TEST.txt");
		boolean thing = false;
		
		try {
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("blue")) { 
		        	thing = true;
		        }
		    }
		    assertEquals(true, thing);
		} catch(FileNotFoundException e) { 
			fail("There's No File!");
		    //handle this
		}
	}
	
	//TODO: Make test for problem.asm.testNode
	@Test
	public void testForSingletonInTestNode() throws IOException
	{
		DesignParser myParser = new DesignParser("java.awt.Desktop");
		myParser.run("java.awt.Desktop");
		File file = new File("SINGLETON_TEST.txt");
		boolean thing = false;
		
		try {
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        //find the word blue in the current line
		        if(line.contains("blue")) { 
		        	//sets it true as a singleton because our file creates it as blue
		        	thing = true;
		        }
		    }
		    assertEquals(true, thing);
		} catch(FileNotFoundException e) { 
			fail("There's No File!");
		}
	}


}
