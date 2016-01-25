package problem.asm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

public class SDEParser {
	
	public ArrayList<SDEDataContainer> classData = new ArrayList<SDEDataContainer>();
	public ClassDeclarationVisitor declVisitor;
	public ClassFieldVisitor fieldVisitor;
	public SDEClassMethodVisitor methodVisitor;
	public String classNameShort;
	public String methodName;
	
	public String callerShortName;
	public SDEC globalContainer;
	
	public int DEPTH;
	
	
	public void run() throws IOException {
		
		//Use class decorators, add one for uses and one for association to get a better design	

	    @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
	    System.out.println("Enter a method");
	    String enteredText = in.nextLine().toString();
	    
	    if(enteredText.charAt(enteredText.length() - 1)!=')') {
	    	System.out.println("You did not enter a method to be called. Please rerun me");
	    	return;
	    }
	    
	    String[] pa = enteredText.split("\\.");
	    String className = pa[0];
	    for(int i = 1; i < pa.length - 1; i++) {
	    	className = className + "." + pa[i];
	    }	    
	    
	    methodName = pa[pa.length-1];
	    methodName = methodName.substring(0, methodName.length()-2);
	    System.out.println("Method Name: " + methodName);
	    
	    System.out.println("Enter a depth");
	    int a = in.nextInt();
	    
	    this.DEPTH = a;
		
		PrintWriter outputStream = new PrintWriter("SDETextFile.txt");
		
		this.globalContainer = new SDEC();
		this.callerShortName = className.substring(0, className.length() - 2);
		globalContainer.addInitializers(this.callerShortName + ":" + className);
		CallForClass(className, outputStream);

//		for(int k = 0; k < classData.size();k++) {
//			classData.get(k).printInformation();
//		}
		
		globalContainer.printInfo(outputStream);

		outputStream.close();
	}
	
	public void run(String aClassName, int aDepth) throws IOException {
		
		//Use class decorators, add one for uses and one for association to get a better design
		
		String[] pa = aClassName.split("\\.");
	    String className = pa[0];
	    for(int i = 1; i < pa.length - 1; i++) {
	    	className = className + "." + pa[i];
	    }	    
	    
	    methodName = pa[pa.length-1];
	    methodName = methodName.substring(0, methodName.length()-2);
	    System.out.println("Method Name: " + methodName);
	    int a = aDepth;
	    this.DEPTH = a;
		
		PrintWriter outputStream = new PrintWriter("SDETextFile2323.txt");
		this.globalContainer = new SDEC();
		this.callerShortName = className.substring(0, className.length() - 2);
		globalContainer.addInitializers(this.callerShortName + ":" + className);
		CallForClass(className, outputStream);
		

//		for(int k = 0; k < classData.size();k++) {
//			classData.get(k).printInformation();
//		}
		
		globalContainer.printInfo(outputStream);

		outputStream.close();
	}
	
	public void CallForClass(String className, PrintWriter outputStream) throws IOException {
		ClassReader reader = new ClassReader(className);
		System.out.println("classname: " + className);

		declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5,className);
		fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
		methodVisitor = new SDEClassMethodVisitor(Opcodes.ASM5, fieldVisitor, className, 
				methodName, globalContainer, callerShortName, reader, this.DEPTH);
		
		reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		
		classNameShort = declVisitor.nameGlobal.substring(declVisitor.nameGlobal.length()-6,declVisitor.nameGlobal.length()-2);
		//outputStream.println(classNameShort + ":" + declVisitor.nameGlobal.replace("404", "."));
		SDEDataContainer newClassData = new SDEDataContainer(outputStream, declVisitor, fieldVisitor, methodVisitor, classNameShort);
		classData.add(newClassData);
	}

}
