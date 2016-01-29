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
	public void testForDecoratorInApp() throws IOException
	{
		//Creates a DesignParser and runs it
		DesignParser myParser = new DesignParser("problem.client.App");
		myParser.run("problem.client.App");
		
		//Checks the file where the run method creates the Umlet TXT file
		File file = new File("TESTCODE.txt");
		
		//creates a boolean value that only gets set to true iff blue is contained in the line
		boolean thing = false;
		
		try {
			//scans every line
		    Scanner scanner = new Scanner(file);

		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        if(line.contains("red")) { 
		        	thing = true;
		        }
		    }
		    assertEquals(true, thing);
		} catch(FileNotFoundException e) { 
			fail();
		    //handle this
		}
	}
	

}
