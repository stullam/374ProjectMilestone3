package problem.asm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

public class SDEParser {
	
	public static ArrayList<SDEDataContainer> classData = new ArrayList<SDEDataContainer>();
	
	public static void run() throws IOException {
		
		//Use class decorators, add one for uses and one for association to get a better design
		
		int a;
	    float b;
	    String enteredText;
	    String className = "";
	    String methodName;
	    
	    Scanner in = new Scanner(System.in);
	    System.out.println("Enter a classname");
	    enteredText = in.nextLine().toString();
	    
	    if(enteredText.charAt(enteredText.length() - 1)!=')') {
	    	System.out.println("You did not enter a method to be called. Please rerun me");
	    	return;
	    }
	    
	    String[] pa = enteredText.split("\\.");
	    methodName = pa[pa.length - 1];
	    className = pa[0];
	    for(int i = 1; i < pa.length - 1; i++) {
	    	className = className + "." + pa[i];
	    }
	    System.out.println("classname: " + className);
	    
	    className = className;
	    
	    System.out.println("Enter a depth");
	    a = in.nextInt();
		
		PrintWriter outputStream = new PrintWriter("SDETextFile.txt");
		CallForClass(className, outputStream);
		

		for(int k = 0; k < classData.size();k++) {
			classData.get(k).printInformation();
		}

		outputStream.close();
	}
	
	public static void run(String aClassName, int aDepth) throws IOException {
		
		//Use class decorators, add one for uses and one for association to get a better design
		
		int a;
	    float b;
	    String className = aClassName;
	    a = aDepth;
		
		PrintWriter outputStream = new PrintWriter("SDETextFile.txt");
		CallForClass(className, outputStream);
		

		for(int k = 0; k < classData.size();k++) {
			classData.get(k).printInformation();
		}

		outputStream.close();
	}
	
	public static void CallForClass(String className, PrintWriter outputStream) throws IOException {
		ClassReader reader = new ClassReader(className);
		System.out.println("classname: " + className);

		ClassDeclarationVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5,className);
		ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
		SDEClassMethodVisitor methodVisitor = new SDEClassMethodVisitor(Opcodes.ASM5, fieldVisitor, className);
		
		reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		
		String classNameShort = declVisitor.nameGlobal.substring(declVisitor.nameGlobal.length()-6,declVisitor.nameGlobal.length()-2);
		outputStream.println(classNameShort + ":" + declVisitor.nameGlobal.replace("404", "."));
		SDEDataContainer newClassData = new SDEDataContainer(outputStream, declVisitor, fieldVisitor, methodVisitor, classNameShort);
		classData.add(newClassData);
	}

}
