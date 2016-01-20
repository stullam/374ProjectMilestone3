package problem.test;

import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;
import org.objectweb.asm.ClassReader;


public class ClassDataTest {
	
	@Test(expected=IOException.class)
	public void ChecksIfClassExists() throws IOException {
		PrintWriter outputStream = new PrintWriter("Stankfile.txt");
		String className = "this does not exist!";
		ClassReader reader = new ClassReader(className);
		
	}

}
