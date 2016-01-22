package problem.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import problem.asm.SDEParser;

public class SDEditParserTest {

	@Test(expected=IOException.class)
	public void checkIfClassExists1() throws IOException {
		SDEParser myParser = new SDEParser();
		myParser.run("THIS CLASS DOES NOT EXIST", 4);
	}
	
	@Test
	public void checkIfClassExist2()
	{
		try {
			SDEParser myParser = new SDEParser();
			myParser.run("problem.asm.DesignParser", 4);
		} catch (IOException e) {
			fail();
		}
	}
	

}
