package problem.asm;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

public class SDEParser {
	
	public static ArrayList<SDEDataContainer> classData = new ArrayList<SDEDataContainer>();
	private String CLASSNAME;
	
	public static void main(String[] args) throws IOException {
		
		//Use class decorators, add one for uses and one for association to get a better design
		
		int a;
	    float b;
	    String className;
	 
	    Scanner in = new Scanner(System.in);
	 
	    System.out.println("Enter a classname");
	    className = in.nextLine();
	 
	    System.out.println("Enter a depth");
	    a = in.nextInt();
		
		PrintWriter outputStream = new PrintWriter("SDETextFile.txt");
		Charset charset = Charset.forName("US-ASCII");
		
		CallForClass(className, outputStream);
		
		//for(String className: args) {
		
//			ClassReader reader = new ClassReader(className);
//			System.out.println("classname: " + className);
//
//			SDEClassDeclarationVisitor declVisitor = new SDEClassDeclarationVisitor(Opcodes.ASM5,className);
//			SDEClassFieldVisitor fieldVisitor = new SDEClassFieldVisitor(Opcodes.ASM5, declVisitor);
//			SDEClassMethodVisitor methodVisitor = new SDEClassMethodVisitor(Opcodes.ASM5, fieldVisitor, className);
//			
//			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
//			
//			String classNameShort = declVisitor.nameGlobal.substring(declVisitor.nameGlobal.length()-6,declVisitor.nameGlobal.length()-2);
//			outputStream.println(classNameShort + ":" + declVisitor.nameGlobal);
//			//System.out.println(declVisitor.nameGlobal.substring(8, 11) + ":" + declVisitor.nameGlobal);
//			SDEDataContainer newClassData = new SDEDataContainer(outputStream, declVisitor, fieldVisitor, methodVisitor, classNameShort);
//			classData.add(newClassData);
			
		//}
		//System.out.println("class data size is: " + classData.size());
		for(int k = 0; k < classData.size();k++) {
			classData.get(k).printInformation();
		}
		
		//outputStream.println("}");
		//System.out.println("Your file has been converted!");
		outputStream.close();
	}
	
	public static void CallForClass(String className, PrintWriter outputStream) throws IOException {
		ClassReader reader = new ClassReader(className);
		System.out.println("classname: " + className);

		SDEClassDeclarationVisitor declVisitor = new SDEClassDeclarationVisitor(Opcodes.ASM5,className);
		SDEClassFieldVisitor fieldVisitor = new SDEClassFieldVisitor(Opcodes.ASM5, declVisitor);
		SDEClassMethodVisitor methodVisitor = new SDEClassMethodVisitor(Opcodes.ASM5, fieldVisitor, className);
		
		reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		
		String classNameShort = declVisitor.nameGlobal.substring(declVisitor.nameGlobal.length()-6,declVisitor.nameGlobal.length()-2);
		outputStream.println(classNameShort + ":" + declVisitor.nameGlobal);
		//System.out.println(declVisitor.nameGlobal.substring(8, 11) + ":" + declVisitor.nameGlobal);
		SDEDataContainer newClassData = new SDEDataContainer(outputStream, declVisitor, fieldVisitor, methodVisitor, classNameShort);
		classData.add(newClassData);
	}

}
