package problem.asm;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.junit.Test;

import junit.framework.Assert;

public class AutomatedUMLTester {
	FileInputStream generatedFile;
	FileInputStream comparatorFile;
	boolean compareFlag = true;
	//Class ExtendsClass;
	
//	File manualGenFile = new File("Stankfile.txt");
//	File computGenFile = new File("StupidFile.txt");
	
	File manualGenFile;
	File computGenFile;

	@Test
	public void test() throws IOException {
		//fail("Not yet implemented");
		//f1.getAbsolutePath();
		
		DesignParser dParse = new DesignParser("problem.asm.ExtendsClass");
		dParse.singleClassTester(dParse.getClassName());
		
		
		computGenFile = new File("TESTCODE.txt");
		manualGenFile = new File("ExtendsClass1Manual.txt");
		
		generatedFile = new FileInputStream(manualGenFile.getAbsolutePath());
		comparatorFile = new FileInputStream(computGenFile.getAbsolutePath());
		
		System.out.println(generatedFile);
		
		DataInputStream di1=new DataInputStream(generatedFile);
		BufferedReader br1=new BufferedReader(new InputStreamReader(di1));
		DataInputStream di2=new DataInputStream(comparatorFile);
		BufferedReader br2=new BufferedReader(new InputStreamReader(di2));
		String s1, s2;
		
		//while ((s1=br1.readLine())!=null && (s2=br2.toString())!=null) {
		while ((s1=br1.readLine())!=null && (s2=br2.readLine())!=null) {	
			System.out.println("firstString:  " + s1);
			System.out.println("secondString: " + s2);
			if(!s1.equals(s2)){
				compareFlag = false;
			}
		}
		
		System.out.println(compareFlag);
		assertTrue(compareFlag);
	}

}
