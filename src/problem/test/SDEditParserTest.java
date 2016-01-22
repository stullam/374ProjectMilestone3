package problem.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

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
	
	@Test
	public void checkIfOutputIsCorrect() throws IOException
	{
		SDEParser myParser = new SDEParser();
		myParser.run("pizzaaf.NYPizzaIngredientFactory", 4);
		File file1 = new File("SDETextFile.txt");
		System.out.println(file1.getAbsolutePath());
		File file2 = new File("docs/SDEParserTestFile.txt");
		assertEquals(true, (file1.exists()));
		assertEquals(true, (file2.exists()));
		byte[] f1 = Files.readAllBytes(Paths.get(file1.getAbsolutePath()));
		System.out.println(f1.toString());
		byte[] f2 = Files.readAllBytes(Paths.get(file2.getAbsolutePath()));
		System.out.println(f2.toString());
		assertEquals(true, Arrays.equals(f1, f2));
	}
	

}
