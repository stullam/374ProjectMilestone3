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
		DesignParser myParser = new DesignParser("problem.asm.Interface1");
		File file = new File("ManualAssociationFromImplementationClass.txt");
		boolean thing = false;
		
		try {
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
		File file = new File("ManualAssociationFromImplementationClass.txt");
		boolean thing = false;
		
		try {
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("blue")) { 
		        	thing = true;
		        }
		        assertEquals(false, thing);
		    }
		} catch(FileNotFoundException e) { 
			fail("There's No File!");
		    //handle this
		}
	}
	
	@Test
	public void testForSingletonInRuntime() throws IOException
	{
		DesignParser myParser = new DesignParser("java.lang.Runtime");
		File file = new File("ManualAssociationFromImplementationClass.txt");
		boolean thing = false;
		
		try {
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("blue")) { 
		        	thing = true;
		        }
		    }
		    assertEquals(false, thing);
		} catch(FileNotFoundException e) { 
			fail("There's No File!");
		    //handle this
		}
	}
	
	@Test
	public void testForSingletonInCalendar() throws IOException
	{
		DesignParser myParser = new DesignParser("java.util.Calendar");
		File file = new File("ManualAssociationFromImplementationClass.txt");
		boolean thing = false;
		
		try {
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("blue")) { 
		        	thing = true;
		        }
		    }
		    assertEquals(false, thing);
		} catch(FileNotFoundException e) { 
			fail("There's No File!");
		    //handle this
		}
	}

}
