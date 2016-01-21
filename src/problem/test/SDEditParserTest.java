package problem.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import problem.asm.SDEParser;

public class SDEditParserTest {

	@Test(expected=IOException.class)
	public void checkIfClassExists1() throws IOException {
		SDEParser.run("THIS CLASS DOES NOT EXIST", 4);
	}
	
	@Test
	public void checkIfClassExist2()
	{
		try {
			SDEParser.run("problem.asm.DesignParser", 4);
		} catch (IOException e) {
			fail();
		}
	}
	

}
