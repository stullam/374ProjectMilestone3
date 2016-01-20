package problem.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

import problem.asm.ClassAssociationArrow;
import problem.asm.ClassDataContainer;
import problem.asm.ClassDeclarationVisitor;
import problem.asm.ClassFieldVisitor;
import problem.asm.ClassImplementsArrow;
import problem.asm.ClassInheritanceArrow;
import problem.asm.ClassMethodVisitor;
import problem.asm.ClassUsesArrow;
import problem.asm.DesignParser;

public class UsesArrowTest {
	FileInputStream generatedFile;
	FileInputStream comparatorFile;
	boolean compareFlag = true;
	File manualGenFile;
	File computGenFile;
	
	@Test
	public void ArrowClass() throws IOException {
		//fail("Not yet implemented");
		//f1.getAbsolutePath();
		
		DesignParser dParse = new DesignParser("problem.asm.ReturnsClass");
		dParse.singleClassTester(dParse.getClassName());
		
		
		computGenFile = new File("TESTCODE.txt");
		manualGenFile = new File("UsesManual.txt");
		
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
